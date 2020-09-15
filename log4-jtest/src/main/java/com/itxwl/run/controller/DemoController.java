package com.itxwl.run.controller;
import com.itxwl.run.service.DemoService;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*  query.addCriteria(Criteria.where("_time").gte("2020-04-10 13:10:33").lte("2020-04-10 13:12:25").and("machine_code").is("008").
                and("scheme_code").is("003d").and("param_code").is("hotWater"))
                .with(Sort.by(Sort.Order.asc("_time")));*/
/**
 * @Auther: 薛
 * @Date: 2020/4/30 15:37
 * @Description:
 */
@RestController
@RequestMapping("t")
@AllArgsConstructor
public class DemoController {
    private DemoService demoService;
    @GetMapping("/s/{data}")
    public Integer s(@PathVariable("data") Integer data) {
        return demoService.js(data);
    }


}
