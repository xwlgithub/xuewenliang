package com.itxwl.mayi;

import com.itxwl.controller.PlanDetail;
import com.itxwl.result.R;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Auther: 薛
 * @Date: 2020/11/21 17:11
 * @Description:
 */
@Service
@Qualifier("aliyunStatery")
public class AliyunStatery implements MyStatery {
    @Override
    public R<List<PlanDetail>> startData(Map<String,Object> params) {
        List<PlanDetail> planDetail = PlanDetail.getPlanDetail(params);
        return R.ok(planDetail);
    }
}
