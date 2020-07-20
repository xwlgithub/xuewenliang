package com.itxwl.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.itxwl.domain.TfEmail;
import com.itxwl.mapper.DataSourceMapper;
import com.itxwl.service.IDataSourceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @Auther: 薛
 * @Date: 2020/7/20 13:33
 * @Description:
 */
@Service
@AllArgsConstructor
@DS("master")
public class DataSourceServiceImpl implements IDataSourceService {
    private DataSourceMapper dataSourceMapper;
    @Override
    public TfEmail findSourceData(Integer id) {
        //如果是1调用基础库-也就是本身的默认库
        if (id.equals(1)){
                return findSourceDatas();
        }
        return findSourceDatasT();
    }
    public TfEmail findSourceDatas(){
       return dataSourceMapper.findServer();
    }
    public TfEmail findSourceDatasT(){
       return dataSourceMapper.findServerT();
    }
}
