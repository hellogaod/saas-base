package com.base.saas.manage.mapper;


import com.base.saas.manage.model.enterprise.EntModule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface EntModuleMapper {

    //根据companyCode查询该企业使用的模块信息
    List<EntModule> getModuleByCompanyCode(@Param("companyCode") String companyCode);

    //根据companyCode查询该企业使用的模块主键id
    List<String> getModuleIdsByCompanyCode(@Param("companyCode") String companyCode);

    //批量插入企业系统模块
    int addEntModuleList(List<EntModule> list);

    //根据系统模块编码查询所有关联企业信息
    List<String> selectEntModleInfoByModuleId(String moduleId);

    //删除企业模块信息
    int deleteEntModuleByCompanyCode(String companyCode);

    int deleteByPrimaryKey(String sysCode);

    int insert(EntModule record);

    int insertSelective(EntModule record);

    EntModule selectByPrimaryKey(String sysCode);

    int updateByPrimaryKeySelective(EntModule record);

    int updateByPrimaryKey(EntModule record);

    List<Map> getCompanyModule(@Param("companyCode") String companyCode);

    List<String> getAllBtnIds(@Param("companyCode") String companyCode, @Param("sysCode") String sysCode);
}