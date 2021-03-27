package com.xwl.service.impl;

import com.xwl.annotation.XwlService;
import com.xwl.service.DemoService;
@XwlService("demoServiceImpl")
public class DemoServiceImpl implements DemoService {
    @Override
    public String query(String name,String age) {
        return "names"+name+"ages"+age;
    }
}
