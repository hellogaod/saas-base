package com.base.saas.manage.mapper;


import com.base.saas.manage.model.SysEnterprise;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface SysEnterpriseMapper {

    //获取创建时间倒序企业信息列表
    List<SysEnterprise> getSysEnterpriseList(@Param("companyName") String companyName);

    //添加企业信息
    int insertSelective(SysEnterprise record);

    //根据companyCode企业编号查询企业信息
    SysEnterprise selectByPrimaryKey(String companyCode);

    //更新企业信息
    int updateByPrimaryKeySelective(SysEnterprise record);

    //根据企业名称和不是当前企业编号 获取企业编号信息，即查询两个企业是否存在同一个企业名称
    String getSysEnterpriseByCompanyName(@Param("companyCode") String companyCode, @Param("companyName") String companyName);

    //修改企业信息状态
    int updateStatus(@Param("status") int status, @Param("companyCode") String companyCode);
}