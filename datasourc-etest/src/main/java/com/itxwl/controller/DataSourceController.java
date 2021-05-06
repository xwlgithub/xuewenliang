package com.itxwl.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.itxwl.domain.TfEmail;
import com.itxwl.service.IDataSourceService;
import com.itxwl.util.MyPage;
import com.itxwl.util.Query;
import lombok.AllArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Auther: 薛
 * @Date: 2020/7/18 15:25
 * @Description:
 */
@RestController
@AllArgsConstructor
@RequestMapping("/test")
@SuppressWarnings("all")
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

    public static void main(String[] args) {

        List<String> sp=new LinkedList<>();//
        sp.add("22");
        sp.add("23");
        sp.add("24");
        List<String> spT=new LinkedList<>();//数据库已经保存的
        spT.add("25");
        spT.add("23");
        spT.add("24");
        spT.add("22");
        Map<String, String> collect = sp.stream().collect(Collectors.toMap(st -> st, ss -> ss));
        List<String> ssss=new LinkedList<>();
        for (String s1 : spT) {
            if (sp.contains(s1)){
                continue;
            }
            ssss.add(s1);
        }
        System.out.println(ssss.toString());
    }
}
