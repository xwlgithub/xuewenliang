package com.itxwl.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itxwl.domain.TfEmail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @Auther: 薛
 * @Date: 2020/7/20 13:34
 * @Description:
 */
@Mapper
public interface DataSourceMapper extends BaseMapper<TfEmail> {
    @DS("master")
    @Select("select * from bladex.tf_email")
    TfEmail findServer();
    @DS("other")
    @Select("select * from bladex.tf_email")
    TfEmail findServerT();
}
