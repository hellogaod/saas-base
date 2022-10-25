package com.base.saas.manage.model.enterprise;

import java.io.Serializable;
import java.util.Date;

public class EntMenu implements Serializable {
    private static final long serialVersionUID = 3024510722251124710L;

    private String id;//当前主键id

    //关联sysMenu主键id
    private String menuId;

    //关联sysModule主键id
    private String moduleId;

    //所属企业
    private String companyCode;

    //菜单名称
    private String menuName;

    //菜单路径
    private String url;

    //序号
    private int sequence;

    //菜单图标
    private String icon;

    //父级菜单id
    private String parentId;

    //父级菜单名称
    private String parentName;

    //创建人
    private String createUser;

    //是否支持脱敏(0,否,1是)
    private Short desensitizeStatus;
    //创建时间
    private Date createTime;

    //修改人
    private String updateUser;

    //修改时间
    private Date updateTime;

    //状态   0：停用, 1：启用
    private int status;

    //备注
    private String remark;

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Short getDesensitizeStatus() {
        return desensitizeStatus;
    }

    public void setDesensitizeStatus(Short desensitizeStatus) {
        this.desensitizeStatus = desensitizeStatus;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
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
        this.updateUser = updateUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}