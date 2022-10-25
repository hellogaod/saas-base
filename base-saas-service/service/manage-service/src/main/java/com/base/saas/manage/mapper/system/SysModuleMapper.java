package com.base.saas.manage.mapper.system;

import com.base.saas.manage.model.system.SysModule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface SysModuleMapper {

    /*插入一条item*/
    int insertSelective(SysModule record);

    /*根据menuId查询模块详情*/
    SysModule selectByPrimaryKey(String menuId);

    /*修改模块信息*/
    int updateByPrimaryKeySelective(SysModule record);

    /*查询列表*/
    List<SysModule> findList(@Param("status") int status, @Param("moduleName") String moduleName);
}