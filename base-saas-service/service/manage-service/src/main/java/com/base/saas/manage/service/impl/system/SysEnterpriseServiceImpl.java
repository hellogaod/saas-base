package com.base.saas.manage.service.impl.system;

import com.base.saas.manage.mapper.enterprise.*;
import com.base.saas.manage.domain.model.ReturnMap;
import com.base.saas.userinfo.UserContextUtil;
import com.base.saas.userinfo.UserInfo;
import com.base.saas.manage.mapper.system.SysEnterpriseMapper;
import com.base.saas.manage.domain.entity.enterprise.EntOrganization;
import com.base.saas.manage.domain.entity.enterprise.EntRole;
import com.base.saas.manage.domain.entity.enterprise.EntRoleMenu;
import com.base.saas.manage.domain.entity.enterprise.EntUser;
import com.base.saas.manage.domain.entity.system.SysEnterprise;
import com.base.saas.manage.service.enterprise.EntMenuService;
import com.base.saas.manage.service.system.SysEnterpriseService;
import com.base.saas.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Title :
 * Description :
 * Create on : 2018年06月08日
 * Copyright (C)
 *
 * @author department:研发部
 * username:
 * @version 修改历史:
 * 修改人 修改日期 修改描述
 * -------------------------------------------<
 */
@Service
public class SysEnterpriseServiceImpl implements SysEnterpriseService {
    @Autowired
    private SysEnterpriseMapper sysEnterpriseMapper;

    @Autowired
    private EntOrganizationMapper entOrganizationMapper;

    @Autowired
    private EntRoleMapper entRoleMapper;
    @Autowired
    private EntUserMapper entUserMapper;

    @Autowired
    private EntModuleMapper entModuleMapper;

    @Autowired
    private EntMenuService entMenuService;

    @Autowired
    private EntMenuMapper entMenuMapper;

    @Autowired
    private EntRoleMenuMapper entRoleMenuMapper;


    @Override
    public List getSysEnterpriseList(String companyName) throws Exception {
        if (StringUtil.isNotEmpty(companyName)) {
            companyName = StringUtil.escapeSQLSpecial(companyName);
        }
        List list = sysEnterpriseMapper.getSysEnterpriseList(companyName);
        return list;
    }

    /**
     * @Description: 企业注册
     * @return:
     */
    @Override
    @Transactional
    public ReturnMap addSysEnterprise(SysEnterprise sysEnterprise) throws Exception {
        ReturnMap retMap = new ReturnMap();

        UserInfo userInfo = UserContextUtil.getUserInfo();

        //企业名称是否存在
        String name = sysEnterpriseMapper.getSysEnterpriseByCompanyName(null, sysEnterprise.getCompanyName());
        if (StringUtil.isNotEmpty(name)) {
            retMap.setMsg("message.enterprise.existed");
            return retMap;
        }

        //企业编号
        String firstCode = StringUtil.getPinYinHeadChar(sysEnterprise.getCompanyName()).toUpperCase();
        if (firstCode.length() >= 4) {
            firstCode = firstCode.substring(0, 4);
        }
        String time = DateConversionUtil.getCurrentTime(DateConversionUtil.STYLE_3);
        String code =  CreateIDUtil.getId();
        String lastCode = code.substring(code.length() - 4, code.length());
        String companyCode = firstCode + time + lastCode;

        //时间
        Date date = new Date();
        //企业实体
        sysEnterprise.setCompanyCode(companyCode);
        sysEnterprise.setCreateUser(userInfo.getAccount());
        sysEnterprise.setCreateTime(date);
        sysEnterprise.setUpdateUser(userInfo.getAccount());
        sysEnterprise.setUpdateTime(date);
        //插入企业
        int n1 = sysEnterpriseMapper.insertSelective(sysEnterprise);

        //创建组织
        String orgId = CreateIDUtil.getId();

        EntOrganization entOrganization = new EntOrganization();
        entOrganization.setOrgId(orgId);
        entOrganization.setParentOrgId("#");
        entOrganization.setCompanyCode(companyCode);
        entOrganization.setOrgName(sysEnterprise.getCompanyName());
        entOrganization.setOrgCode("1");
        entOrganization.setOrgManager(sysEnterprise.getCompanyManager());
        entOrganization.setOrgTel(sysEnterprise.getTel());
        entOrganization.setStatus((short) 1);
        entOrganization.setOrgType((short) 0);
        entOrganization.setCreateUser(userInfo.getAccount());
        entOrganization.setCreateTime(date);
        entOrganization.setUpdateUser(userInfo.getAccount());
        entOrganization.setUpdateTime(date);
        int n2 = entOrganizationMapper.insertSelective(entOrganization);

        String roleId = CreateIDUtil.getId();

        //创建超级管理员角色
        EntRole entRole = new EntRole();
        entRole.setRoleId(roleId);
        entRole.setCompanyCode(companyCode);
        entRole.setRoleName("超级管理员角色");
        entRole.setStatus((short) 1);
        entRole.setCreateUser(userInfo.getAccount());
        entRole.setCreateTime(date);
        entRole.setUpdateUser(userInfo.getAccount());
        entRole.setUpdateTime(date);
        int n3 = entRoleMapper.insertSelective(entRole);

        //创建超级管理员用户
        EntUser entUser = new EntUser();
        entUser.setUserId(CreateIDUtil.getId());
        entUser.setCompanyCode(companyCode);
        entUser.setOrgId(orgId);
        entUser.setAccount(sysEnterprise.getAdminAccount());
        entUser.setRealName("超级管理员");
        String password = MD5Util.GetMD5Code(sysEnterprise.getAdminPassword());
        entUser.setPassword(password);
        Short userStatus = 1;
        entUser.setStatus(userStatus);
        entUser.setRoleId(roleId);
        entUser.setCreateUser(userInfo.getAccount());
        entUser.setCreateTime(date);
        entUser.setUpdateUser(userInfo.getAccount());
        entUser.setUpdateTime(date);
        int n4 = entUserMapper.insertSelective(entUser);

        //角色分配模块和菜单
        retMap = addEntRoleMenu(userInfo, roleId, sysEnterprise.getModuleIds(), companyCode);

        return retMap;
    }

    public ReturnMap addEntRoleMenu(UserInfo userInfo, String roleId, List<String> moduleIds, String companyCode) {

        ReturnMap returnMap = new ReturnMap(0, "message.system.operation.fail");

        try {
            //删除企业角色菜单信息
            entRoleMenuMapper.deleteEntRoleMenu(roleId);

            //创建企业角色菜单对象集合
            List<EntRoleMenu> entRoleMenuList = new ArrayList<>();
            //ent_module插入企业模块信息和ent_menu插入该模块下的所有菜单菜单信息
            returnMap = entMenuService.addEntMenuAndEntModule(moduleIds, companyCode);

            if (returnMap.getCode() == 1) {
                //根据companyCode查询企业所有的菜单id
                List<String> entMenuIdList = entMenuMapper.getMenuIdsByCompanyCode(companyCode);
                if (null != entMenuIdList && entMenuIdList.size() > 0) {
                    for (String menuId : entMenuIdList) {
                        //创建企业角色菜单对象
                        EntRoleMenu entRoleMenu = new EntRoleMenu();
                        entRoleMenu.setId(CreateIDUtil.getId());
                        entRoleMenu.setMenuId(menuId);
                        entRoleMenu.setCreateTime(new Date());
                        entRoleMenu.setRoleId(roleId);
                        entRoleMenu.setCompanyCode(companyCode);
                        entRoleMenu.setCreateUser(userInfo.getAccount());
                        entRoleMenuList.add(entRoleMenu);
                    }
                    //批量插入企业角色菜单信息
                    entRoleMenuMapper.addEntRoleMenuList(entRoleMenuList);

                    returnMap.setMsg("message.system.operation.success");
                }
            } else {
                returnMap.setMsg("message.menu.insert.error");
            }
        } catch (Exception e) {
            e.printStackTrace();
            returnMap.setCode(0);
            returnMap.setMsg("system.server.exception");
        }
        return returnMap;
    }

    @Override
    public ReturnMap updateStatus(SysEnterprise param) throws Exception {
        ReturnMap map = new ReturnMap();

        if (StringUtil.isEmpty(param.getCompanyCode())) {
            map.setMsg("message.system.request.param.exception");
        }
        int n = sysEnterpriseMapper.updateStatus(param.getStatus(), param.getCompanyCode());
        if (n > 0) {
            map.setCode(1);
            map.setMsg("message.system.operation.success");
        } else {
            map.setMsg("message.system.operation.success");
        }
        return map;
    }

    @Override
    public SysEnterprise getEnterpriseByCompanyCode(String companyCode) throws Exception {
        SysEnterprise sysEnterprise = sysEnterpriseMapper.selectByPrimaryKey(companyCode);
        sysEnterprise.setModuleIds(entModuleMapper.getModuleIdsByCompanyCode(companyCode));
        return sysEnterprise;
    }

    /**
     * @Description: 企业修改
     * @return:
     */
    @Override
    @Transactional
    public ReturnMap updateSysEnterprise(SysEnterprise sysEnterprise) throws Exception {
        UserInfo userInfo = UserContextUtil.getUserInfo();

        ReturnMap retMap = new ReturnMap();

        //企业名称是否重复
        String name = sysEnterpriseMapper.getSysEnterpriseByCompanyName(sysEnterprise.getCompanyCode(), sysEnterprise.getCompanyName());
        if (StringUtil.isNotEmpty(name)) {
            retMap.setMsg("message.enterprise.existed");
            return retMap;
        }

        //时间
        Date date = new Date();
        //企业实体赋值
        sysEnterprise.setUpdateUser(userInfo.getAccount());
        sysEnterprise.setUpdateTime(date);
        //修改企业
        int n = sysEnterpriseMapper.updateByPrimaryKeySelective(sysEnterprise);

        String roleId = entUserMapper.getRoleIdByRealName("超级管理员");

        retMap = addEntRoleMenu(userInfo,roleId,sysEnterprise.getModuleIds(),sysEnterprise.getCompanyCode());

        return retMap;

    }


}
