package com.base.saas.manage.mapper;

import com.base.saas.manage.model.RoleMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Title :
 * Description : 权限管理
 * Create on : 2018年05月21日
 * Copyright (C) zw.FinTec
 *
 * @author department:研发部
 *         username: wangtao
 * @version 修改历史:
 *          修改人 修改日期 修改描述
 *          -------------------------------------------<
 */
@Mapper
public interface RoleMenuMapper {
    int deleteByPrimaryKey(String id);

    int insert(RoleMenu record);

    int insertSelective(RoleMenu record);

    RoleMenu selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RoleMenu record);

    int updateByPrimaryKey(RoleMenu record);

    /**
    * @Description: 根据roleId查询所有的menuId
    * @Param:  * @param roleId
    * @return: java.util.List<java.lang.String>
    * @Date: 2018/5/24
    */
    List<Map> getMenuByRoleId(String roleId);

    /**
    * @Description: 更新角色菜单状态为停用
    * @Param:  * @param roleMenu
    * @return: void
    * @Date: 2018/5/24
    */
    void deleteRoleMenuByRoleId(String roleId);

    /**
    * @Description:  批量插入角色菜单数据
    * @Param:  * @param notExistsMenus
    * @return: void
    * @Date: 2018/5/24
    */
    int insertRoleMenuList(List<RoleMenu> roleMenuList);

    /**
     * @Author: wangtao
     * @Date: 2018/05/28 15:56
     * @Params: roleId
     *              角色id
     * @Description: 查询角色拥有的菜单
     * @return: List
     */
    List<Map> getMenuListByRoleId(@Param("roleId") String roleId, @Param("sysCode") String sysCode);

    /**
     * @Author: wangtao
     * @Date: 2018/05/29 13:07
     * @Params: map（roleId：角色id
     *                menuId：菜单id）
     * @Description: 查询角色下该菜单的所有子集
     * @return:
     */
    List<Map> getMenuListByRoleIdAndMenuId(Map map);
}