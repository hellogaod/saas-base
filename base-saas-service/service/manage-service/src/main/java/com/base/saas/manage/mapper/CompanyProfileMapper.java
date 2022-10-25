package com.base.saas.manage.mapper;

import com.base.saas.manage.model.CompanyProfile;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CompanyProfileMapper {
    int deleteByPrimaryKey(String companyCode);

    int insert(CompanyProfile record);

    int insertSelective(CompanyProfile record);

    CompanyProfile selectByPrimaryKey(String companyCode);

    int updateByPrimaryKeySelective(CompanyProfile record);

    int updateByPrimaryKeyWithBLOBs(CompanyProfile record);

    CompanyProfile getInfo(String companyCode);

    int update(CompanyProfile companyProfile);
}