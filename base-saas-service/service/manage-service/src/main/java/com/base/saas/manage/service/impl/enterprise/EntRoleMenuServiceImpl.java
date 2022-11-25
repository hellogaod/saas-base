package com.base.saas.manage.service.impl.enterprise;


import com.base.saas.manage.mapper.enterprise.EntModuleMapper;
import com.base.saas.manage.domain.model.ReturnMap;
import com.base.saas.manage.domain.entity.enterprise.EntMenu;
import com.base.saas.manage.domain.entity.enterprise.EntModule;
import com.base.saas.manage.domain.entity.enterprise.EntRole;
import com.base.saas.manage.domain.entity.enterprise.EntRoleMenu;
import com.base.saas.manage.service.enterprise.EntRoleMenuService;
import com.base.saas.userinfo.UserContextUtil;
import com.base.saas.userinfo.UserInfo;
import com.base.saas.manage.mapper.enterprise.EntRoleMapper;
import com.base.saas.manage.mapper.enterprise.EntRoleMenuMapper;
import com.base.saas.manage.mapper.enterprise.EntMenuMapper;

import com.base.saas.util.CreateIDUtil;
import com.base.saas.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Title :
 * Description : @角色权限管理实现@
 */
@Service
public class EntRoleMenuServiceImpl implements EntRoleMenuService {

    @Autowired
    private EntRoleMenuMapper roleMenuMapper;

    @Autowired
    private EntRoleMapper roleMapper;

    @Autowired
    private EntMenuMapper entMenuMapper;

    @Autowired
    private EntModuleMapper entModuleMapper;

    @Override
    public List<EntModule> getModuleList(String roleId) throws Exception {
        UserInfo userInfo = UserContextUtil.getUserInfo();
        //1. 先查询当前企业下的模块
        List<EntModule> entModules = entModuleMapper.getModuleByCompanyCode(userInfo.getCompanyCode());

        entModules.stream().filter(entModule -> entModule != null && !StringUtil.isEmpty(entModule.getModuleId()))
                .forEach(entModule -> {
                    //2. 当前模块下的所有菜单信息
                    List<EntMenu> entMenus = entMenuMapper.getMenusByModuleId(entModule.getModuleId());

                    //3. 当前角色拥有哪些菜单信息,将这些菜单设置为选中状态
                    List<String> checkedMenuIds = roleMenuMapper.selectMenuIdsByRoleId(roleId);
                    entModule.setCheckedMenuIds(checkedMenuIds);

                    //4. 将菜单设置成树形结构
                    entModule.setMenuList(getTree(entMenus));
                });

        return entModules;
    }


    // 获取组织架构的树形结构
    private List<EntMenu> getTree(List<EntMenu> list) {
        List<EntMenu> tree = list.stream().
                filter(item -> "#".equals(item.getParentId()))
                .peek(
                        item -> {
                            List<EntMenu> childrens = getChildrens(item, list);
                            setChildren(item, childrens);
                        }
                )
                .collect(Collectors.toList());

        return tree;
    }

    private void setChildren(EntMenu item, List<EntMenu> childrens) {
        List<Boolean> checkeds = childrens.stream().map(entMenu -> entMenu.isChecked()).collect(Collectors.toList());

        item.setSubMenus(childrens);
    }

    private List<EntMenu> getChildrens(EntMenu root, List<EntMenu> list) {

        List<EntMenu> lists = list.stream()
                .filter(item ->
                        //筛选出下一层元素节点
                        Objects.equals(item.getParentId(), root.getMenuId())
                )
                .map(item ->
                {
                    //递归set子节点
                    List<EntMenu> childrens = this.getChildrens(item, list);
                    setChildren(item, childrens);
                    return item;
                })
                .collect(Collectors.toList());

        return lists;
    }

    @Override
    @Transactional
    public ReturnMap insertMenus(String roleId, List<String> menuIds) {
        ReturnMap resultMap = new ReturnMap();
        //判断角色编号是否为空
        if (StringUtil.isEmpty(roleId)) {
            resultMap.setMsg("message.system.request.param.exception");
            return resultMap;
        }

        //查询角色是否存在
        EntRole existsRole = roleMapper.selectByPrimaryKey(roleId);
        UserInfo userInfo = UserContextUtil.getUserInfo();

        //角色不存在
        if (null == existsRole && existsRole.getCompanyCode() != userInfo.getCompanyCode()) {
            resultMap.setMsg("message.role.not.existed");
            return resultMap;
        }

        //先删除原有角色对应权限
        roleMenuMapper.deleteEntRoleMenu(roleId);

        //menuIds为空,则删除修改之前所有的权限
        if (null == menuIds || menuIds.size() == 0) {

            resultMap.setCode(1);
            resultMap.setMsg("message.system.operation.success");
            return resultMap;
        } else {
            //批量插入当前选择的角色对应权限
            List<EntRoleMenu> roleMenus = menuIds.stream().map(menuId -> {
                //当前时间
                Date date = new Date();
                EntRoleMenu roleMenu = new EntRoleMenu();
                roleMenu.setCreateTime(date);
                roleMenu.setCreateUser(userInfo.getAccount());
                roleMenu.setRoleId(roleId);
                roleMenu.setMenuId(menuId);
                roleMenu.setCompanyCode(userInfo.getCompanyCode());
                roleMenu.setId(CreateIDUtil.getId());
                return roleMenu;
            }).collect(Collectors.toList());

            int n = roleMenuMapper.addEntRoleMenuList(roleMenus);
            if (n > 0) {
                resultMap.setCode(1);
                resultMap.setMsg("message.system.operation.success");
            } else {
                resultMap.setMsg("message.system.operation.fail");
            }
        }

        return resultMap;
    }

}
