package com.base.saas.manager.mapper;


import com.base.saas.manager.model.EntModule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface EntModuleMapper {
    int deleteByPrimaryKey(String sysCode);

    int insert(EntModule record);

    int insertSelective(EntModule record);

    EntModule selectByPrimaryKey(String sysCode);

    int updateByPrimaryKeySelective(EntModule record);

    int updateByPrimaryKey(EntModule record);

    int addEntModuleList(List<EntModule> list);

    int updateEntModuleStatus(String companyCode);

    List<EntModule> getModuleByCompanyCode(@Param("companyCode") String companyCode);
    //根据系统模块编码查询所有关联企业信息
    List<Map> findAllCompanyCodeBySysCode(String sysModuleCode);
}