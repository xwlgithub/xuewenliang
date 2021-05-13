package com.itxwl.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.itxwl.domain.ServiceLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author xueWenLiang
 * @date 2021/5/11 19:15
 * @Description 描述信息
 */
@Mapper
public interface XwlLogInfoMapper extends BaseMapper<ServiceLog> {
}
