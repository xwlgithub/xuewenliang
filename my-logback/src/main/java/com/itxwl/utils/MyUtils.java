package com.itxwl.utils;

import org.springframework.stereotype.Component;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author xueWenLiang
 * @date 2021/5/12 14:30
 * @Description 描述信息
 */
@Component
public class MyUtils {

    /**
     * 获取异常的堆栈信息
     *
     * @param t
     * @return
     */
    public static String getStackTrace(Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        try {
            //将异常信息输出在控制台
            t.printStackTrace(pw);
            //将异常信息返回
            return sw.toString();
        } finally {
            pw.close();
        }
    }
}
