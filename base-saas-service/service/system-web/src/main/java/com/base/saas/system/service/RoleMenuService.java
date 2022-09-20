package com.base.saas.system.service;

import java.util.List;
import java.util.Map;

/**
 * Title :
 * Description : @角色权限管理接口@
 * Create on : 2018年05月24日
 * Copyright (C) zw.FinTec
 *
 * @author department:研发部
 * username:chengrui
 * @version 修改历史:
 * 修改人 修改日期 修改描述
 * -------------------------------------------<
 */
public interface RoleMenuService {

    /**
     * @Description: 根据roleId查询已有的menuId集合
     * @Param: * @param roleId
     * @return: java.util.List<java.lang.String>
     * @Date: 2018/5/24
     */
    List<Map> getMenuByRoleId(String roleId) throws Exception;

    /**
     * @Author: wangtao
     * @Date: 2018/05/25 11:03
     * @Params: roleId 角色id
     * @Description: 获取菜单
     * @return: List
     */
    List getMenuListByRoleId(String roleId, String sysCode) throws Exception;

    /**
     * @Description: 添加角色权限
     * @Param: * @param params
     * @return: java.util.Map
     * @Date: 2018/5/24
     */
    Map addRoleMenu(Map params) throws Exception;

    /**
     * @Author: wangtao
     * @Date: 2018/05/29 13:07
     * @Params: map（roleId：角色id
     * menuId：菜单id）
     * @Description: 查询角色下该菜单的所有子集
     * @return:
     */
    List<Map> getMenuListByRoleIdAndMenuId(Map map) throws Exception;

}