package com.base.saas.manager.mapper;


import com.base.saas.manager.model.SysEnterprise;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface SysEnterpriseMapper {
    int deleteByPrimaryKey(String companyCode);

    int insert(SysEnterprise record);

    int insertSelective(SysEnterprise record);

    SysEnterprise selectByPrimaryKey(String companyCode);

    int updateByPrimaryKeySelective(SysEnterprise record);

    int updateByPrimaryKey(SysEnterprise record);

    List<SysEnterprise> getSysEnterpriseList(@Param("companyName") String companyName);

    String getSysEnterpriseByCompanyName(Map map);

    int updateStatus(@Param("status") String status, @Param("companyCode") String companyCode);
}