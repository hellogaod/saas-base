package com.base.saas.system.service.impl;


import com.base.saas.system.mapper.EntMenuMapper;
import com.base.saas.system.mapper.RoleMapper;
import com.base.saas.system.mapper.RoleMenuMapper;
import com.base.saas.system.model.EntMenu;
import com.base.saas.system.model.Role;
import com.base.saas.system.model.RoleMenu;
import com.base.saas.system.service.RoleMenuService;
import com.base.saas.util.CreateIDUtil;
import com.base.saas.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Title :
 * Description : @角色权限管理实现@
 * Create on : 2018年05月24日
 * Copyright (C) zw.FinTec
 *
 * @author department:研发部
 * username:chengrui
 * @version 修改历史:
 * 修改人 修改日期 修改描述
 * -------------------------------------------<
 */
@Service
public class RoleMenuServiceImpl implements RoleMenuService {

    private static final Logger logger = LoggerFactory.getLogger(RoleMenuServiceImpl.class);

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private EntMenuMapper entMenuMapper;

    /**
     * @Description: 根据roleId查询已有的menuId集合
     * @Param: * @param roleId
     * @return: java.util.List<java.lang.String>
     * @Date: 2018/5/24
     */
    @Override
    public List<Map> getMenuByRoleId(String roleId) throws Exception {
        return roleMenuMapper.getMenuByRoleId(roleId);
    }

    /**
     * @Author: wangtao
     * @Date: 2018/05/25 11:03
     * @Params: roleId 角色id
     * @Description: 获取菜单
     * @return: List
     */
    @Override
    public List getMenuListByRoleId(String roleId, String sysCode) throws Exception {
        return roleMenuMapper.getMenuListByRoleId(roleId, sysCode);
    }
    /**
     * @Description: 添加角色权限
     * @Param: * @param params
     * @return: java.util.Map
     * @Date: 2018/5/24
     */
    @Override
    public Map addRoleMenu(Map params) throws Exception {
        Map resultMap = new HashMap();
        //角色编号
        String roleId = String.valueOf(params.get("roleId"));
        //当前操作的用户
        String createUser = String.valueOf(params.get("createUser"));

        //判断角色编号是否为空
        if (StringUtil.isEmpty(roleId)) {
            resultMap.put("flag", false);
            resultMap.put("msg", "message.system.request.param.exception");
            return resultMap;
        }
        //查询角色是否存在
        Role existsRole = roleMapper.selectByPrimaryKey(roleId);
        //组织id
        String orgId = "";
        //公司编号
        String companyCode = "";
        if (null == existsRole) {
            resultMap.put("flag", false);
            resultMap.put("msg", "message.role.not.existed");
            return resultMap;
        } else {
            orgId = existsRole.getOrgId();
            companyCode = existsRole.getCompanyCode();
        }

        List<String> menuIds = (List<String>) params.get("menuIds");
        //当前时间
        Date date = new Date();

        //menuIds为空,则删除修改之前所有的权限
        if (null == menuIds || menuIds.size() == 0) {
            roleMenuMapper.deleteRoleMenuByRoleId(roleId);
            resultMap.put("flag", true);
            resultMap.put("msg", "message.system.operation.success");
            return resultMap;
        } else {
            //不为空, 则先删除该角色之前的菜单, 在进行批量插入
            //先将该角色的所有的权限菜单删除
            roleMenuMapper.deleteRoleMenuByRoleId(roleId);


            resultMap = insertRoleMenuList(menuIds, roleId, createUser, orgId, companyCode, 0);

            List<String> halfMenuIds = (List<String>) params.get("halfMenuIds");
            if (halfMenuIds != null && halfMenuIds.size() > 0)
                resultMap = insertRoleMenuList(halfMenuIds, roleId, createUser, orgId, companyCode, 1);

        }

        return resultMap;
    }

    private Map insertRoleMenuList(List<String> menuIds, String roleId, String createUser, String orgId, String companyCode, int status) {

        //当前时间
        Date date = new Date();
        Map resultMap = new HashMap();

        //创建插入角色菜单数据集合
        List<RoleMenu> roleMenuList = new ArrayList<>();
        RoleMenu roleMenu = null;
        //遍历数组, 将传过来的数据放入roleMenuList集合中
        for (String menuId : menuIds) {
            roleMenu = new RoleMenu();
            //查询需要添加的菜单是否在菜单列表内
            EntMenu menu = entMenuMapper.selectByPrimaryKey(menuId);
            if (null == menu) {
                //没有, 直接返回错误信息
                logger.info("没有找到编号为" + menuId + "的菜单没有找到!");
                resultMap.put("flag", false);
                resultMap.put("msg", "message.menu.not.existed");
                return resultMap;
            }
            roleMenu.setCreateTime(date);
            roleMenu.setCreateUser(createUser);
            roleMenu.setRoleId(roleId);
            roleMenu.setMenuId(menuId);
            roleMenu.setCompanyCode(companyCode);
            roleMenu.setOrgId(orgId);
            roleMenu.setId(CreateIDUtil.getId());
            roleMenu.setStatus(status);
            roleMenuList.add(roleMenu);
        }

        //添加不存在的菜单数据
        int row = roleMenuMapper.insertRoleMenuList(roleMenuList);
        if (row > 0) {
            resultMap.put("flag", true);
            resultMap.put("msg", "message.system.operation.success");
        } else {
            resultMap.put("flag", false);
            resultMap.put("msg", "message.system.operation.fail");
        }


        return resultMap;
    }


    /**
     * @Author: wangtao
     * @Date: 2018/05/29 13:07
     * @Params: map（roleId：角色id
     * menuId：菜单id）
     * @Description: 查询角色下该菜单的所有子集
     * @return:
     */
    @Override
    public List<Map> getMenuListByRoleIdAndMenuId(Map map) throws Exception {
        return roleMenuMapper.getMenuListByRoleIdAndMenuId(map);
    }
}
