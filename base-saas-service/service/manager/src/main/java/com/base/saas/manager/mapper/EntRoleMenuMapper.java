package com.base.saas.manager.mapper;


import com.base.saas.manager.model.EntRoleMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface EntRoleMenuMapper {
    int deleteByPrimaryKey(String id);

    int insert(EntRoleMenu record);

    int insertSelective(EntRoleMenu record);

    EntRoleMenu selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(EntRoleMenu record);

    int updateByPrimaryKey(EntRoleMenu record);

    int addEntRoleMenuList(List<EntRoleMenu> list);

    int deleteEntRoleMenu(String roleId);

    int delEntMenuNotInModelDetail(Map map);
}