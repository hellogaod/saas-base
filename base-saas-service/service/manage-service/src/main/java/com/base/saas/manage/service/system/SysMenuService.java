package com.base.saas.manage.service.system;/**
 * Created by win7 on 2018/6/11.
 */

import com.base.saas.manage.model.ReturnMap;
import com.base.saas.manage.model.system.SysMenu;

import java.util.List;
import java.util.Map;

/**
 * Title :
 * Description : 系统菜单service，隶属于系统模块
 */
public interface SysMenuService {

    /***
     * 获取当前模块下的所有菜单，如果存在parentId表示获取当前菜单下的子菜单
     *
     * @return
     */
    List<SysMenu> getAllMenuList(String moduleId, String parentId) throws Exception;

    //通过菜单id获取菜单信息
    SysMenu getMenuByMenuId(String MenuId) throws Exception;

    //获取模块下所有的菜单信息,如果存在parentId表示获取当前菜单下的子菜单
    List<SysMenu> getAllMenuTree(String moduleId, String parentId) throws Exception;

    //添加系统菜单
    ReturnMap addMenu(SysMenu moduleDetail) throws Exception;

    //修改系统菜单信息
    ReturnMap editMenu(SysMenu moduleDetail) throws Exception;

    //修改系统菜单状态
    ReturnMap updateMenuStatus(String menuId,int status) throws Exception;
}
