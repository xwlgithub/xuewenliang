package com.itxwl.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.itxwl.domain.ErrorLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author xueWenLiang
 * @date 2021/5/12 15:00
 * @Description 描述信息
 */
@Mapper
public interface XwlLogErrorMapper extends BaseMapper<ErrorLog> {
}
