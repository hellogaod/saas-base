package com.base.saas.manage.service.enterprise;

import com.base.saas.manage.domain.model.ReturnMap;
import com.base.saas.manage.domain.entity.enterprise.EntModule;

import java.util.List;

/**
 * Title :
 * Description : @角色权限管理接口@
 */
public interface EntRoleMenuService {

    //获取当前企业下的所有模块及其模块下的菜单信息
    //再根据roleId判断当前角色可以使用那些模块及其模块下那些菜单
    List<EntModule> getModuleList(String roleId) throws Exception;

    //给角色批量添加菜单权限
    //1. 在ent_role_menu中删除当前角色的菜单信息
    //2. 在批量插入当前权限
    ReturnMap insertMenus(String roleId, List<String> menuIds);

}