package com.base.saas.manage.mapper.enterprise;


import com.base.saas.manage.domain.entity.enterprise.EntRole;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EntRoleMapper {

    //插入一条角色信息
    int insertSelective(EntRole record);

    //修改角色信息
    int updateByPrimaryKeySelective(EntRole record);

    //获取角色信息
    EntRole selectByPrimaryKey(String roleId);

    //获取角色列表
    List<EntRole> getRoleList(@Param("status") int status,@Param("roleName") String roleName,@Param("companyCode") String companyCode);

}