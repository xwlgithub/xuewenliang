package com.xwl.config;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

public class MyFilter  implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        //1.设置POST请求中文乱码的问题
        request.setCharacterEncoding("utf-8");

        //2.解决GET请求中文乱码的问题
        HttpServletRequest hsr = (HttpServletRequest) request;
        if(hsr.getMethod().equalsIgnoreCase("get")){
            MyRequest myRequest = new MyRequest(hsr);

            //放行请求
            filterChain.doFilter(myRequest,response);
        }else{
            filterChain.doFilter(request,response);
        }
    }
    /**
     * 自定义包装类
     */
    private class MyRequest extends HttpServletRequestWrapper {
        private HttpServletRequest request;
        private boolean isEncoding = false; //是否已经utf-8编码

        public MyRequest(HttpServletRequest request) {
            super(request);
            this.request = request;
        }

        @Override
        public String getParameter(String name) {
            return getParameterMap().get(name)[0];
        }

        @Override
        public Map<String, String[]> getParameterMap() {
            Map<String,String[]> map = request.getParameterMap();

            if(isEncoding == true){
                return map;
            }

            //遍历value， 改成utf-8编码
            for(Map.Entry<String,String[]> entry : map.entrySet())
            {
                //取数组值
                String[] values = entry.getValue();

                for (int i = 0; i < values.length; i++) {
                    try {
                        values[i] = new String(values[i].getBytes("ISO-8859-1"),"utf-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }
            isEncoding = true;
            return map;
        }
    }
    @Override
    public void destroy() {

    }
}
