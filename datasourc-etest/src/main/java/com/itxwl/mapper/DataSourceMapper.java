package com.itxwl.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itxwl.domain.TfEmail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

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
    @Select("select * from  tf_email limit #{current},#{size}")
    List<TfEmail> findAll(@Param("current") Integer current, @Param("size")Integer size);
    @Select("select count(1) from tf_email")
    Integer findCounts();

}
