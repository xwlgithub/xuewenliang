package com.itxwl.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.itxwl.domain.TfEmail;
import com.itxwl.service.IDataSourceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: 薛
 * @Date: 2020/7/18 15:25
 * @Description:
 */
@RestController
@AllArgsConstructor
@RequestMapping("/test")
public class DataSourceController {
    private IDataSourceService dataSourceService;
    @GetMapping("/findSourceData/{id}")
    public R<TfEmail> findSourceData(@PathVariable("id") Integer id){
        return R.ok(dataSourceService.findSourceData(id));
    }
}
