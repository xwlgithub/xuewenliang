package com.itxwl.service.impl;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.itxwl.domain.TfEmail;
import com.itxwl.mapper.DataSourceMapper;
import com.itxwl.service.IDataSourceService;
import com.itxwl.util.MyPage;
import com.itxwl.util.Query;
import lombok.AllArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: 薛
 * @Date: 2020/7/20 13:33
 * @Description:
 */
@Service
@AllArgsConstructor
public class DataSourceServiceImpl implements IDataSourceService {
    private DataSourceMapper dataSourceMapper;
    @Override
    public TfEmail findSourceData(Integer id) {
        DruidDataSource build = DruidDataSourceBuilder.create().build();
        //如果是1调用基础库-也就是本身的默认库
        if (id.equals(1)){
            return findSourceDatas();
        }
        return findSourceDatasT();
    }

    /**
     * 查询全部
     * @param query
     * @return
     */
    @Override
    public MyPage<TfEmail> findAll(Query query) {
        int currsorl = (query.getCurrent() - 1) * query.getSize();
        List<TfEmail> all = dataSourceMapper.findAll(currsorl, query.getSize());
        Integer counts = dataSourceMapper.findCounts();
        return new MyPage<>(query.getCurrent(),query.getSize(),all,counts);
    }

    /**
     * 新增或修改
     * @param tfEmail
     * @return
     */
    @Override
    public Boolean saveOrUpdates(TfEmail tfEmail) {
        //如果id不是空 就是修改
        if (tfEmail.getId()!=null){
            dataSourceMapper.updateById(tfEmail);
            return true;
        }
        dataSourceMapper.insert(tfEmail);
        return true;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @Override
    public Boolean deleteById(Long id) {
        return dataSourceMapper.deleteById(id)>0?true:false;
    }

    public TfEmail findSourceDatas(){
       return dataSourceMapper.findServer();
    }
    public TfEmail findSourceDatasT(){
       return dataSourceMapper.findServerT();
    }
}
