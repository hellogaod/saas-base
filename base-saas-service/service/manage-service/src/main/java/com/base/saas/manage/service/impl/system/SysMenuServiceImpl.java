package com.base.saas.manage.service.impl.system;

import com.base.saas.manage.mapper.system.SysMenuMapper;
import com.base.saas.manage.domain.model.ReturnMap;
import com.base.saas.manage.domain.entity.system.SysMenu;
import com.base.saas.manage.service.system.SysMenuService;
import com.base.saas.userinfo.UserContextUtil;
import com.base.saas.userinfo.UserInfo;
import com.base.saas.manage.mapper.enterprise.EntMenuMapper;
import com.base.saas.util.CreateIDUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Title :
 * Description : 系统菜单service，隶属于系统模块
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Resource
    private SysMenuMapper sysMenuMapper;
    @Resource
    private EntMenuMapper entMenuMapper;

    @Override
    public List<SysMenu> getAllMenuList(String moduleId, String parentId) throws Exception {
        List<SysMenu> sysMenus = sysMenuMapper.getSysMenuList(moduleId, parentId, -1);
        return sysMenus;
    }

    @Override
    public SysMenu getMenuByMenuId(String MenuId) {
        return sysMenuMapper.selectByPrimaryKey(MenuId);
    }

    @Override
    public List<SysMenu> getAllMenuTree(String moduleId, String parentId) {
        List<SysMenu> sysMenus = sysMenuMapper.getAllMenuTreeByModuleId(moduleId, parentId);
        return getTree(sysMenus);
    }

    // 获取组织架构的树形结构
    private List<SysMenu> getTree(List<SysMenu> list) {
        List<SysMenu> tree = list.stream().
                filter(item -> "#".equals(item.getParentId()))
                .peek(
                        item -> {
                            List<SysMenu> childrens = getChildrens(item, list);
                            item.setChildren(childrens);
                        }
                )
                .collect(Collectors.toList());

        return tree;
    }


    private List<SysMenu> getChildrens(SysMenu root, List<SysMenu> list) {

        List<SysMenu> lists = list.stream()
                .filter(item ->
                        //筛选出下一层元素节点
                        Objects.equals(item.getParentId(), root.getMenuId())
                )
                .map(item ->
                {
                    //递归set子节点
                    List<SysMenu> childrens = this.getChildrens(item, list);
                    item.setChildren(childrens);
                    return item;
                })
                .collect(Collectors.toList());

        return lists;
    }

    @Override
    @Transactional
    public ReturnMap addMenu(SysMenu moduleDetail) throws Exception {
        //返回集合
        ReturnMap returnMap = new ReturnMap(0);
        //判断改菜单的URL是否已经存在
        if (!"".equals(moduleDetail.getUrl())) {
            int isUrlRepeat = sysMenuMapper.selectCountByMenuUrl(moduleDetail.getModuleId(), null, moduleDetail.getUrl());
            if (isUrlRepeat > 0) {//该菜单url已存在
                returnMap.setMsg("message.menu.url.existed");
                return returnMap;
            }
        }

        UserInfo userInfo = UserContextUtil.getUserInfo();
        //创建人
        moduleDetail.setCreateUser(userInfo.getAccount());
        moduleDetail.setUpdateUser(userInfo.getAccount());

        String sysMenuCode = CreateIDUtil.getId();
        moduleDetail.setMenuId(sysMenuCode);
        moduleDetail.setCreateTime(new Date());
        moduleDetail.setUpdateTime(new Date());
        int result = sysMenuMapper.insertSelective(moduleDetail);
        if (result == 0) {
            returnMap.setMsg("message.system.sql.error");
            return returnMap;
        }

        return returnMap;
    }

    @Override
    @Transactional
    public ReturnMap editMenu(SysMenu moduleDetail) throws Exception {
        //返回集合
        ReturnMap returnMap = new ReturnMap(0);
        //判断改菜单的URL是否已经存在
        if (!"".equals(moduleDetail.getUrl())) {
            int isUrlRepeat = sysMenuMapper.selectCountByMenuUrl(moduleDetail.getModuleId(), moduleDetail.getMenuId(), moduleDetail.getUrl());
            if (isUrlRepeat > 0) {//该菜单url已存在
                returnMap.setMsg("message.menu.url.existed");
                return returnMap;
            }
        }

        UserInfo userInfo = UserContextUtil.getUserInfo();
        //创建人
        moduleDetail.setUpdateUser(userInfo.getAccount());

        moduleDetail.setUpdateTime(new Date());
        int result = sysMenuMapper.updateByPrimaryKeySelective(moduleDetail);
        if (result == 0) {
            returnMap.setMsg("message.system.sql.error");
            return returnMap;
        }

        returnMap.setCode(1);
        returnMap.setMsg("message.system.update.success");
        return returnMap;
    }

    @Override
    @Transactional
    public ReturnMap updateMenuStatus(String menuId, int status) throws Exception {
        UserInfo userInfo = UserContextUtil.getUserInfo();

        //返回集合
        ReturnMap returnMap = new ReturnMap(0);

        //如果当前
        int entMenuAndSubMenuCount = entMenuMapper.getMenuAndSubMenuCount(menuId);

        //表示当前模块或其子模块已经被企业使用了，那么不允许被停用
        if (status == 0 && entMenuAndSubMenuCount > 0) {
            returnMap.setMsg("message.otherConfig.company.isconf");
            return returnMap;
        }

        //启用或暂停：本身及其下面的子菜单全部启动或暂停
        sysMenuMapper.updateSysMenuStatus(menuId, userInfo.getAccount(), status);

        returnMap.setCode(1);
        returnMap.setMsg("message.system.update.success");
        return returnMap;
    }
}