package com.base.saas.manager.model;

import java.io.Serializable;
import java.util.Date;

public class SysEnterprise implements Serializable {
    private static final long serialVersionUID = 469879304086992954L;

    //企业编号
    private String companyCode;

    //企业名称
    private String companyName;

    //企业邮箱
    private String email;

    //企业联系电话
    private String tel;

    //联系人
    private String companyManager;

    //营业执照
    private String orgImg;

    //法人身份证
    private String orgOwnerImg;

    //企业状态 1：启用（默认值） 0：停用
    private Short status;

    //创建人
    private String createUser;

    //创建时间
    private Date createTime;

    //修改人
    private String updateUser;

    //修改时间
    private Date updateTime;

    //企业简称
    private String shortName;

    //微信号
    private String wechatNo;

    public String getWechatNo() {
        return wechatNo;
    }

    public void setWechatNo(String wechatNo) {
        this.wechatNo = wechatNo;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode == null ? null : companyCode.trim();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getCompanyManager() {
        return companyManager;
    }

    public void setCompanyManager(String companyManager) {
        this.companyManager = companyManager == null ? null : companyManager.trim();
    }

    public String getOrgImg() {
        return orgImg;
    }

    public void setOrgImg(String orgImg) {
        this.orgImg = orgImg == null ? null : orgImg.trim();
    }

    public String getOrgOwnerImg() {
        return orgOwnerImg;
    }

    public void setOrgOwnerImg(String orgOwnerImg) {
        this.orgOwnerImg = orgOwnerImg == null ? null : orgOwnerImg.trim();
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
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