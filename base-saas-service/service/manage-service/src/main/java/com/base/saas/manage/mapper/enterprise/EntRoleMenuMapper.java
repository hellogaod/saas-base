package com.base.saas.manage.mapper.enterprise;


import com.base.saas.manage.domain.entity.enterprise.EntRoleMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface EntRoleMenuMapper {

    //删除企业角色菜单信息
    int deleteEntRoleMenu(String roleId);

    //批量插入企业角色菜单
    int addEntRoleMenuList(List<EntRoleMenu> list);

    List<String> selectMenuIdsByRoleId(String roleId);


    int deleteByPrimaryKey(String id);

    int insert(EntRoleMenu record);

    int insertSelective(EntRoleMenu record);

    EntRoleMenu selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(EntRoleMenu record);

    int updateByPrimaryKey(EntRoleMenu record);


    int delEntMenuNotInModelDetail(Map map);
}