package com.xwl.controller;

import com.xwl.annotation.XwlAutowried;
import com.xwl.annotation.XwlController;
import com.xwl.annotation.XwlRequestMapping;
import com.xwl.annotation.XwlRequestParam;
import com.xwl.service.DemoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@XwlController
@XwlRequestMapping("/demos")
public class DemoController {
    @XwlAutowried("demoServiceImpl")
    private DemoService demoService;
    @XwlRequestMapping("/getLists")
    public void getList(HttpServletRequest request, HttpServletResponse response,
    @XwlRequestParam("name")String name,@XwlRequestParam("age")String age)
    {
        try {
            response.setContentType("text/html; charset=GBK");
            PrintWriter writer = response.getWriter();
            String demos=demoService.query(name,age);
            writer.write(demos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
