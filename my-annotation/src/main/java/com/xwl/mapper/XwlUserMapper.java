package com.xwl.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.xwl.domain.XwlUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author xueWenLiang
 * @date 2021/4/29 17:07
 * @Description 描述信息
 */
@Mapper
public interface XwlUserMapper extends BaseMapper<XwlUser> {
    @Select("select *  from xwl_user where id=#{id}dsadasdsa")
    XwlUser getUserInfo(@Param("id") Long id);
}
