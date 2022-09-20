package com.base.saas.manager.service.impl;

import com.base.saas.manager.mapper.*;
import com.base.saas.manager.model.*;
import com.base.saas.manager.mapper.*;
import com.base.saas.manager.model.*;
import com.base.saas.manager.service.SysEnterpriseService;
import com.base.saas.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Title :
 * Description :
 * Create on : 2018年06月08日
 * Copyright (C) zw.FinTec
 *
 * @author department:研发部
 *         username: wangtao
 * @version 修改历史:
 *          修改人 修改日期 修改描述
 *          -------------------------------------------<
 */
@Service
public class SysEnterpriseServiceImpl implements SysEnterpriseService {
    @Autowired
    private SysEnterpriseMapper sysEnterpriseMapper;
    @Autowired
    private EntModuleMapper entModuleMapper;
    @Autowired
    private EntOrganizationMapper entOrganizationMapper;
    @Autowired
    private EntRoleMapper entRoleMapper;
    @Autowired
    private EntUserMapper entUserMapper;
    /**
     * @Author: wangtao
     * @Date: 2018/06/08 13:16
     * @Params: map（企业信息和选择的系统模块信息）
     * @Description: 企业注册
     * @return:
     */
    @Override
    @Transactional
    public Map addSysEnterprise(Map map) throws Exception {
        Map retMap = new HashMap();
        String createUser = String.valueOf(map.get("createUser"));
        //管理员账号
        String account = "admin";
        //企业名称
        String companyName = String.valueOf(map.get("companyName"));
        Map paramMap = new HashMap();
        paramMap.put("companyName",companyName);
        String name = sysEnterpriseMapper.getSysEnterpriseByCompanyName(paramMap);
        if(StringUtil.isNotEmpty(name)){
            retMap.put("flag",false);
            retMap.put("msg","message.enterprise.existed");
            return retMap;
        }
        //企业简称
        String shortName = String.valueOf(map.get("shortName"));
        //企业编号
        String firstCode = StringUtil.getPinYinHeadChar(shortName).toUpperCase();
        if(firstCode.length()>=4){
            firstCode = firstCode.substring(0,4);
        }
        String time = DateConversionUtil.getCurrentTime(DateConversionUtil.STYLE_3);
        String code = new GUIDUtil().toString();
        String lastCode = code.substring(code.length()-4,code.length());
        String companyCode = firstCode+time+lastCode;
        retMap.put("companyCode",companyCode);
        //企业邮箱
        String email = String.valueOf(map.get("email"));
        //企业联系电话
        String tel = String.valueOf(map.get("tel"));
        //联系人
        String companyManager = String.valueOf(map.get("companyManager"));
        //企业状态 1：启用（默认值） 0：停用
        Short status = Short.valueOf(String.valueOf(map.get("status")));
        //时间
        Date date = new Date();
        //企业实体赋值
        SysEnterprise sysEnterprise = new SysEnterprise();
        sysEnterprise.setCompanyCode(companyCode);
        sysEnterprise.setShortName(shortName);
        sysEnterprise.setCompanyName(companyName);
        sysEnterprise.setEmail(email);
        sysEnterprise.setTel(tel);
        sysEnterprise.setCompanyManager(companyManager);
        sysEnterprise.setStatus(status);
        sysEnterprise.setCreateUser(createUser);
        sysEnterprise.setCreateTime(date);
        sysEnterprise.setUpdateUser(createUser);
        sysEnterprise.setUpdateTime(date);
        sysEnterprise.setWechatNo(String.valueOf(map.get("wechatNo")));
        //插入企业
        int n1 = sysEnterpriseMapper.insertSelective(sysEnterprise);

        //插入企业选择的系统模块
        /*List<Map> moduleList = (List) map.get("moduleList");
        addEntModule(moduleList, createUser, companyCode);*/

        //创建组织
        EntOrganization entOrganization = new EntOrganization();
        String orgId = CreateIDUtil.getId();
        retMap.put("orgId",orgId);
        entOrganization.setOrgId(orgId);
        entOrganization.setParentOrgId("#");
        entOrganization.setCompanyCode(companyCode);
        entOrganization.setOrgName(companyName);
        entOrganization.setOrgCode("1");
        entOrganization.setOrgManager(companyManager);
        entOrganization.setOrgTel(tel);
        Short orgStatus = 1;
        entOrganization.setStatus(orgStatus);
        Short orgType = 0;
        entOrganization.setOrgType(orgType);
        entOrganization.setCreateUser(account);
        entOrganization.setCreateTime(date);
        entOrganization.setUpdateUser(account);
        entOrganization.setUpdateTime(date);
        int n2 = entOrganizationMapper.insertSelective(entOrganization);

        //创建超级管理员角色
        EntRole entRole = new EntRole();
        String roleId = CreateIDUtil.getId();
        retMap.put("roleId",roleId);
        entRole.setRoleId(roleId);
        entRole.setCompanyCode(companyCode);
        entRole.setOrgId(orgId);
        entRole.setRoleName("超级管理员");
        Short roleStatus = 1;
        entRole.setStatus(roleStatus);
        entRole.setCreateUser(account);
        entRole.setCreateTime(date);
        entRole.setUpdateUser(account);
        entRole.setUpdateTime(date);
        int n3 = entRoleMapper.insertSelective(entRole);

        //创建超级管理员用户
        EntUser entUser = new EntUser();
        entUser.setUserId(CreateIDUtil.getId());
        entUser.setCompanyCode(companyCode);
        entUser.setOrgId(orgId);
        entUser.setAccount(account);
        entUser.setRealName("管理员");
        String password = MD5Util.GetMD5Code("123456");
        entUser.setPassword(password);
        Short userStatus = 1;
        entUser.setStatus(userStatus);
        entUser.setRoleId(roleId);
        entUser.setCreateUser(account);
        entUser.setCreateTime(date);
        entUser.setUpdateUser(account);
        entUser.setUpdateTime(date);
        int n4 = entUserMapper.insertSelective(entUser);
        boolean flag = n1>0 && n2>0 && n3>0 && n4>0;
        retMap.put("flag",flag);
        if(flag){
            retMap.put("msg","message.system.save.success");
        }else{
            retMap.put("msg","message.system.save.fail");
        }
        return retMap;
    }

    /**
     * @Author: wangtao
     * @Date: 2018/06/08 14:31
     * @Params: map
     * @Description: 企业修改
     * @return:
     */
    @Override
    public Map updateSysEnterprise(Map map)throws Exception {
        Map retMap = new HashMap();
        //修改人
        String createUser = String.valueOf(map.get("createUser"));
        //企业编号
        String companyCode = String.valueOf(map.get("companyCode"));
        //企业名称
        String companyName = String.valueOf(map.get("companyName"));
        Map paramMap = new HashMap();
        paramMap.put("companyName",companyName);
        paramMap.put("companyCode",companyCode);
        String name = sysEnterpriseMapper.getSysEnterpriseByCompanyName(paramMap);
        if(StringUtil.isNotEmpty(name)){
            retMap.put("flag",false);
            retMap.put("msg","message.enterprise.existed");
            return retMap;
        }
        //企业简称
        String shortName = String.valueOf(map.get("shortName"));
        //企业邮箱
        String email = String.valueOf(map.get("email"));
        //企业联系电话
        String tel = String.valueOf(map.get("tel"));
        //联系人
        String companyManager = String.valueOf(map.get("companyManager"));
        //微信号
        String wechatNo = String.valueOf(map.get("wechatNo"));
        //时间
        Date date = new Date();
        //企业实体赋值
        SysEnterprise sysEnterprise = new SysEnterprise();
        sysEnterprise.setCompanyCode(companyCode);
        sysEnterprise.setCompanyName(companyName);
        sysEnterprise.setShortName(shortName);
        sysEnterprise.setEmail(email);
        sysEnterprise.setTel(tel);
        sysEnterprise.setWechatNo(wechatNo);
        sysEnterprise.setCompanyManager(companyManager);
        sysEnterprise.setUpdateUser(createUser);
        sysEnterprise.setUpdateTime(date);
        //修改企业
        int n = sysEnterpriseMapper.updateByPrimaryKeySelective(sysEnterprise);
        boolean flag = n>0;
        retMap.put("flag",flag);
        if(flag){
            retMap.put("msg","message.system.update.success");
        }else{
            retMap.put("msg","message.system.update.fail");
        }
        return retMap;
        /*//先删除之前选择的系统模块
        entModuleMapper.deleteByCompanyCode(companyCode);
        //再插入企业选择的系统模块
        List<Map> moduleList = (List) map.get("moduleList");
        addEntModule(moduleList, createUser, companyCode);*/
    }

    /**
     * @Author: wangtao
     * @Date: 2018/06/08 14:50
     * @Params: moduleList: 选择的系统模块
     * @Params: createUser: 创建人
     * @Params: companyCode: 企业编号
     * @Description: 插入企业选择的系统模块
     * @return:
     */
    public void addEntModule(List<Map> moduleList, String createUser, String companyCode){
        Date date = new Date();
        if(!ObjectUtils.isEmpty(moduleList)){
            for (int i = 0; i < moduleList.size(); i++) {
                EntModule entModule = new EntModule();
                Map moduleMap = moduleList.get(i);
                //主键
                String sysCode = CreateIDUtil.getId();
                //系统名称
                String sysName = String.valueOf(moduleMap.get("sysName"));
                entModule.setSysCode(sysCode);
                entModule.setSysName(sysName);
                entModule.setCreateUser(createUser);
                entModule.setCreateTime(date);
                entModule.setUpdateUser(createUser);
                entModule.setUpdateTime(date);
                Short moduleStatus = 1;
                entModule.setStatus(moduleStatus);
                entModule.setCompanyCode(companyCode);
                entModuleMapper.insertSelective(entModule);
            }
        }
    }

    @Override
    public List getSysEnterpriseList(String companyName) throws Exception{
        if(StringUtil.isNotEmpty(companyName)){
            companyName = StringUtil.escapeSQLSpecial(companyName);
        }
        List list = sysEnterpriseMapper.getSysEnterpriseList(companyName);
        return list;
    }

    @Override
    public SysEnterprise getEnterpriseByCompanyCode(String companyCode)throws Exception{
        return sysEnterpriseMapper.selectByPrimaryKey(companyCode);
    }

    @Override
    public Map updateStatus(Map param)throws Exception{
        Map map = new HashMap();
        if(null==param.get("status")||StringUtil.isEmpty(param.get("status").toString())){
            map.put("flag",false);
            map.put("msg","message.system.request.param.exception");
        }
        if(null==param.get("companyCode")||StringUtil.isEmpty(param.get("companyCode").toString())){
            map.put("flag",false);
            map.put("msg","message.system.request.param.exception");
        }
        int n = sysEnterpriseMapper.updateStatus(param.get("status").toString(),param.get("companyCode").toString());
        if(n>0){
            map.put("flag",true);
            map.put("msg","message.system.operation.success");
        }else{
            map.put("flag",false);
            map.put("msg","message.system.operation.success");
        }
        return map;
    }
}
