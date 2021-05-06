package com.xwl.config;

import com.xwl.annotation.*;
import com.xwl.controller.DemoController;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;
public class MyDispatcherImpl extends HttpServlet {
    //保存待实例化所有类得路径
    List<String> classUrls = new ArrayList<>();
    //ioc容器
    Map<String, Object> ioc = new HashMap<>();
    //根据url找处理的方法
    Map<String, Method> urlHandlers = new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        System.out.println("路径"+uri);
        String contextPath = req.getContextPath();
        System.out.println("路径二"+uri);
        String path = uri.replace(contextPath, "");
        System.out.println("路径三"+path);
        Method method = (Method) urlHandlers.get(path);
        System.out.println("方法"+method.toString());
        DemoController instance = (DemoController) ioc.get("/" + path.split("/")[1]);
        System.out.println("类"+instance.toString());
       Object[] args= hand(req,resp,method);
        try {
            method.invoke(instance,args);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private static Object[] hand(HttpServletRequest req, HttpServletResponse resp, Method method) {
        //拿到当前待执行方法有哪些参数
        Class<?>[] parameterTypes = method.getParameterTypes();
        System.out.println("参数"+parameterTypes);
        //根据参数个数 new  一个参数数组 将方法里所有参数赋值到args来
        Object[] args = new Object[parameterTypes.length];
        System.out.println("参数个数组"+args);
        int args_i=0;
        int index=0;
        for (Class<?> paramClazz : parameterTypes) {
            System.out.println("参数类"+paramClazz.toString());
            if (ServletRequest.class.isAssignableFrom(paramClazz)){
                System.out.println("里面的"+args.toString());
                args[args_i++]=req;
            }
            if (ServletResponse.class.isAssignableFrom(paramClazz)){
                System.out.println("里面的"+args.toString());
                args[args_i++]=resp;
            }
            System.out.println("外面的"+args.toString());
            //从0-3判断有没有自定义注解 很明显 paramClazz为0和1时  不是
            //当为2和3时, param注解需要解析
            //[@com.xwl.annotation.XwlRequestParam(value = =name)]
            System.out.println("索引"+index);
            Annotation[] paramAns = method.getParameterAnnotations()[index];
            System.out.println("注解"+paramAns.toString());
            if (paramAns.length>0){
                for (Annotation paramAn : paramAns) {
                    if (XwlRequestParam.class.isAssignableFrom(paramAn.getClass())){
                        XwlRequestParam rp = (XwlRequestParam) paramAn;
                        System.out.println("参数值"+rp.value());
                        args[args_i++]=req.getParameter(rp.value());
                    }
                }
            }
            index++;
        }
        return args;
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        doScanPackage("com.xwl");
        doInstance();//类实例化
        doAutowoid();//处理依赖
        doUrlMapping();//拼接路径
    }

    private void doUrlMapping() {
        Iterator<Map.Entry<String, Object>> iterator = ioc.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String, Object> iocMaps = iterator.next();
            Object instance = iocMaps.getValue();
            Class<?> aClass = instance.getClass();
            if (aClass.isAnnotationPresent(XwlController.class)){
                //找到控制类
                XwlRequestMapping xwlRequestMapping = aClass.getAnnotation(XwlRequestMapping.class);
                String controllerPath = xwlRequestMapping.value();
                Method[] methods = aClass.getMethods();
                for (Method method : methods) {
                    if (method.isAnnotationPresent(XwlRequestMapping.class)){
                        XwlRequestMapping methodMapping = method.getAnnotation(XwlRequestMapping.class);
                        String methodPath = methodMapping.value();
                        urlHandlers.put(controllerPath+methodPath,method);
                    }
                }
            }
        }
    }

    //处理依赖
    private void doAutowoid() {
        Iterator<Map.Entry<String, Object>> iterator = ioc.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String, Object> iocMaps = iterator.next();
            Object instance = iocMaps.getValue();
            Class<?> aClass = instance.getClass();
            if (aClass.isAnnotationPresent(XwlController.class)){
                Field[] fields = aClass.getDeclaredFields();
                for (Field field : fields) {
                    if (field.isAnnotationPresent(XwlAutowried.class)){
                        XwlAutowried xwlAutowried = field.getAnnotation(XwlAutowried.class);
                        String xwlAutoworid = xwlAutowried.value();
                        Object autoWoridObject = ioc.get(xwlAutoworid);
                        //私有变量无法随便注入Spring容器 必须开启权限
                        field.setAccessible(true);
                        try {
                            field.set(instance,autoWoridObject);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }
        }
    }

    private void doInstance() {
        for (String classUrl : classUrls) {
            try {
                Class<?> aClass = Class.forName(classUrl);
                if (aClass.isAnnotationPresent(XwlController.class)) {
                    //找到控制类
                    Object instance = aClass.newInstance();
                    XwlRequestMapping mappingClass = aClass.getAnnotation(XwlRequestMapping.class);
                    String controllerKey = mappingClass.value();
                    ioc.put(controllerKey,instance);
                }else if (aClass.isAnnotationPresent(XwlService.class)){
                    //找到控制类
                    XwlService service = aClass.getAnnotation(XwlService.class);
                    Object serviceObject = aClass.newInstance();
                    String serviceKey = service.value();
                    ioc.put(serviceKey,serviceObject);
                }else {
                    continue;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    //扫描包路径,找到所有class类路径
    private void doScanPackage(String packageNames) {
        URL url = this.getClass().getClassLoader().getResource("/" + packageNames.replaceAll("\\.", "/"));
        String fileStr = url.getFile();
        File file = new File(fileStr);
        for (String path : file.list()) {
            File filePath = new File(fileStr + path);
            //如果是文件形式递归
            if (filePath.isDirectory()) {
                //递归读取包
                doScanPackage(packageNames + "." + path);
            } else {
                String className = packageNames + "." + filePath.getName().replace(".class", "");
                //存储url
                classUrls.add(className);
            }
        }

    }
}
