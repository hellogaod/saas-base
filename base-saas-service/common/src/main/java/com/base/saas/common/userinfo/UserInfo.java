package com.base.saas.common.userinfo;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Title :
 * Description : @类注释说明写在此处@
 * Create on : 2018年05月21日
 * Copyright (C) zw.FinTec
 *
 * @author department:研发部
 * username:zh-pc
 * @version 修改历史:
 * 修改人 修改日期 修改描述
 * -------------------------------------------<
 */
@Data
@Slf4j
public class UserInfo implements Serializable {
    private static final long serialVersionUID = 7704782905876663335L;

    private String userId;

    private String account;

    private String companyCode;

    private String companyName;

    private String orgId;

    private String realName;

    private String tel;

    /**
     * 登录用户的类别；
     * 1：运营平台用户
     * 2:企业用户
     */
    private int userType;

    /**
     * 用户拥有权限的菜单列表
     */
    private List<String> permissionList;

    /**
     * 用户拥有权限的菜单列表的数据权限，数据查看范围和是否脱敏展示
     */
    private Map<String, Map<String, String>> dataPermission;

    /**
     * 登录令牌
     */
    private String token;

    /**
     * 电信登录令牌
     */
    /*private String ctLoginToken;

    private String ctSessionKey;*/

    /**
     * 白条分期账户ID
     */
    /*private String ctAccountId;*/

    /*public String getCtAccountId() {
        return ctAccountId;
    }

    public void setCtAccountId(String ctAccountId) {
        this.ctAccountId = ctAccountId;
    }

    public String getCtLoginToken() {
        return ctLoginToken;
    }

    public void setCtLoginToken(String ctLoginToken) {
        this.ctLoginToken = ctLoginToken;
    }

    public String getCtSessionKey() {
        return ctSessionKey;
    }

    public void setCtSessionKey(String ctSessionKey) {
        this.ctSessionKey = ctSessionKey;
    }

    public String getTel() {
        if("17007123402".equals(tel)){
            log.error("号码异常");
            throw new RuntimeException("号码异常");
        }
        return tel;
    }

    public void setTel(String tel) {
        if("17007123402".equals(tel)){
            log.error("号码异常");
            throw new RuntimeException("号码异常");
        }
        this.tel = tel;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public List<String> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<String> permissionList) {
        this.permissionList = permissionList;
    }

    public Map<String, Map<String, String>> getDataPermission() {
        return dataPermission;
    }

    public void setDataPermission(Map<String, Map<String, String>> dataPermission) {
        this.dataPermission = dataPermission;
    }

    *//**
     * 登录用户的类别；
     * 1：运营平台用户
     * 2:企业用户
     *
     * @return
     *//*
    public int getUserType() {
        return userType;
    }

    *//**
     * 登录用户的类别；
     * 1：运营平台用户
     * 2:企业用户
     *
     * @param userType
     *//*
    public void setUserType(int userType) {
        this.userType = userType;
    }*/
}
