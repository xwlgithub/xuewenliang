package com.itxwl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.itxwl.domain.TfEmail;
import com.itxwl.util.MyPage;
import com.itxwl.util.Query;

public interface IDataSourceService {
    TfEmail findSourceData(Integer id);

    MyPage<TfEmail> findAll(Query query);

    Boolean saveOrUpdates(TfEmail tfEmail);

    Boolean deleteById(Long id);
}
