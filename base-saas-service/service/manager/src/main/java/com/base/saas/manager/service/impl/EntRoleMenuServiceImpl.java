package com.base.saas.manager.service.impl;

import com.base.saas.manager.mapper.EntMenuMapper;
import com.base.saas.manager.mapper.EntRoleMenuMapper;
import com.base.saas.manager.model.EntRoleMenu;
import com.base.saas.manager.service.EntMenuService;
import com.base.saas.manager.service.EntRoleMenuService;
import com.base.saas.common.exception.TransactionRollBackException;
import com.base.saas.util.CreateIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Title :
 * Description : @企业角色权限@
 * Create on : 2018年06月08日
 * Copyright (C) zw.FinTec
 *
 * @author department:研发部
 *         username:chengrui
 * @version 修改历史:
 *          修改人 修改日期 修改描述
 *          -------------------------------------------<
 */
@Service
public class EntRoleMenuServiceImpl implements EntRoleMenuService {

    private static Logger logger = LoggerFactory.getLogger(EntRoleMenuServiceImpl.class);

    @Autowired
    private EntMenuService entMenuService;

    @Autowired
    private EntMenuMapper entMenuMapper;

    @Autowired
    private EntRoleMenuMapper entRoleMenuMapper;

    @Transactional(rollbackFor = TransactionRollBackException.class)
    @Override
    public int addEntRoleMenu(String roleId, List<String> sysCodeList, String companyCode, String orgId) {
        int code = 0;
        try {
            //删除企业角色菜单信息
            entRoleMenuMapper.deleteEntRoleMenu(roleId);
            //创建企业角色菜单对象集合
            List<EntRoleMenu> entRoleMenuList = new ArrayList<>();
            //插入企业模块信息和企业菜单信息
            int num = entMenuService.addEntMenuAndEntModule(sysCodeList, companyCode);
            if (num > 0) {
                //根据companyCode查询所有的企业菜单id
                List<String> entMenuIdList = entMenuMapper.getEntMenuListByCompanyCode(companyCode);
                if (null != entMenuIdList && entMenuIdList.size() > 0) {
                    for (String menuId : entMenuIdList) {
                        //创建企业角色菜单对象
                        EntRoleMenu entRoleMenu = new EntRoleMenu();
                        entRoleMenu.setId(CreateIDUtil.getId());
                        entRoleMenu.setMenuId(menuId);
                        entRoleMenu.setCreateTime(new Date());
                        entRoleMenu.setRoleId(roleId);
                        entRoleMenu.setOrgId(orgId);
                        entRoleMenu.setCompanyCode(companyCode);
                        entRoleMenuList.add(entRoleMenu);
                    }
                    //批量插入企业角色菜单信息
                    code = entRoleMenuMapper.addEntRoleMenuList(entRoleMenuList);
                }
            } else {
                logger.error("插入企业菜单信息失败!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("系统异常!");
        }
        return code;
    }
}
