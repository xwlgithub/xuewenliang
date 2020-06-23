package com.itxwl.rabbitmqstudy.seckill.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itxwl.rabbitmqstudy.seckill.eneity.StokOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
@SuppressWarnings("ALL")
public interface StokOrderMapper  extends BaseMapper<StokOrder> {
    @Select("select stock_count from stok_order where com_modity=#{shopType}")
    Integer findCountByShopType(@Param("shopType") Integer shopType);
}
