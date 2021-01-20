package com.itxwl.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.itxwl.domain.TfEmail;
import com.itxwl.service.IDataSourceService;
import com.itxwl.util.MyPage;
import com.itxwl.util.Query;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("findAll")
    public R<MyPage<TfEmail>> findAll(Query query){
        return R.ok(dataSourceService.findAll(query));
    }
    @PostMapping("saveOrUpdates")
    public R<Boolean> saveOrUpdates(@RequestBody  TfEmail tfEmail){
        return R.ok(dataSourceService.saveOrUpdates(tfEmail));
    }
    @PostMapping("deleteById/{id}")
    public R<Boolean> deleteById(@PathVariable("id")Long id){
        return R.ok(dataSourceService.deleteById(id));
    }
}
