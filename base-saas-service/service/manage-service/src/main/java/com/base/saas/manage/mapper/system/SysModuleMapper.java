package com.base.saas.manage.mapper.system;

import com.base.saas.manage.domain.entity.system.SysModule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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