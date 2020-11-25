package com.itxwl.mayi;

import com.itxwl.controller.TwoDetail;
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
@Qualifier("weiXinStatery")
public class WeiXinStatery implements MyStatery {
    @Override
    public R<List<TwoDetail>> startData(Map<String,Object> params) {
        List<TwoDetail> twoDetails = TwoDetail.getPlanDetail(params);
        return R.ok(twoDetails);
    }
}
