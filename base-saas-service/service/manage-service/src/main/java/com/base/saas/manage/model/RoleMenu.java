package com.base.saas.manage.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Title :
 * Description : 权限管理实体
 * Create on : 2018年05月21日
 * Copyright (C) zw.FinTec
 *
 * @author department:研发部
 *         username: wangtao
 * @version 修改历史:
 *          修改人 修改日期 修改描述
 *          -------------------------------------------<
 */
public class RoleMenu implements Serializable{
    private static final long serialVersionUID = -6401298132848884415L;

    //系统编号
    private String id;

    //角色编号
    private String roleId;

    //组织编号
    private String orgId;

    //菜单资源编号
    private String menuId;

    //企业编号
    private String companyCode;

    //创建人
    private String createUser;

    //创建时间
    private Date createTime;

    public String getId() {
        return id;
    }

    public int status;//如果是1表示半选中，否则表示选中

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId == null ? null : menuId.trim();
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode == null ? null : companyCode.trim();
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}