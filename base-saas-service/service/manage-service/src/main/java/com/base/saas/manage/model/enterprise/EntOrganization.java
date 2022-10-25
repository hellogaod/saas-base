package com.base.saas.manage.model.enterprise;

import java.io.Serializable;
import java.util.Date;

//组织架构
public class EntOrganization implements Serializable {
    private static final long serialVersionUID = 2171673469791585503L;

    //组织编号
    private String orgId;

    //父级编号
    private String parentOrgId;

    //企业编号
    private String companyCode;

    //组织名称
    private String orgName;

    //组织序号
    private String orgCode;

    //联系人
    private String orgManager;

    //联系电话(手机号)
    private String orgTel;

    //固定电话
    private String orgShortTel;

    //状态（1：启用  0：停用）
    private Short status;

    //组织机构类型（1=部门;0=公司）
    private Short orgType;

    //创建人
    private String createUser;

    //创建时间
    private Date createTime;

    //修改人
    private String updateUser;

    //修改时间
    private Date updateTime;

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    public String getParentOrgId() {
        return parentOrgId;
    }

    public void setParentOrgId(String parentOrgId) {
        this.parentOrgId = parentOrgId == null ? null : parentOrgId.trim();
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode == null ? null : companyCode.trim();
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    public String getOrgManager() {
        return orgManager;
    }

    public void setOrgManager(String orgManager) {
        this.orgManager = orgManager == null ? null : orgManager.trim();
    }

    public String getOrgTel() {
        return orgTel;
    }

    public void setOrgTel(String orgTel) {
        this.orgTel = orgTel == null ? null : orgTel.trim();
    }

    public String getOrgShortTel() {
        return orgShortTel;
    }

    public void setOrgShortTel(String orgShortTel) {
        this.orgShortTel = orgShortTel == null ? null : orgShortTel.trim();
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Short getOrgType() {
        return orgType;
    }

    public void setOrgType(Short orgType) {
        this.orgType = orgType;
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

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}