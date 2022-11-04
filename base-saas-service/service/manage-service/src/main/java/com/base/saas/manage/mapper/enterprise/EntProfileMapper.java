package com.base.saas.manage.mapper.enterprise;

import com.base.saas.manage.domain.entity.enterprise.EntProfile;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EntProfileMapper {
    //查询公司简介信息
    EntProfile getInfo(String companyCode);

    //插入一条公司简介信息
    int insert(EntProfile record);

    //更改一条公司简介信息
    int update(EntProfile companyProfile);
}