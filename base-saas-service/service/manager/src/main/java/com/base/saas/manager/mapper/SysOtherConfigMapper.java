package com.base.saas.manager.mapper;

import com.base.saas.manager.model.SysOtherConfig;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SysOtherConfigMapper {
    int deleteByPrimaryKey(String id);


    int insertSelective(SysOtherConfig record);

    SysOtherConfig selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysOtherConfig record);


    List<SysOtherConfig> findList(Map map);

    List<SysOtherConfig> getConfListByCompanyCode(String companyCode);
}