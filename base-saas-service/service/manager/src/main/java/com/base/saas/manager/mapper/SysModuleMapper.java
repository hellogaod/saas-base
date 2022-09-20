package com.base.saas.manager.mapper;

import com.base.saas.manager.model.SysModule;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SysModuleMapper {
    int deleteByPrimaryKey(String sysCode);

    int insert(SysModule record);

    int insertSelective(SysModule record);

    SysModule selectByPrimaryKey(String sysCode);

    int updateByPrimaryKeySelective(SysModule record);

    int updateByPrimaryKey(SysModule record);

    List<SysModule> getEffectiveModule();

    List<SysModule> findList(Map map);
}