package com.base.saas.manage.model.system;

import java.io.Serializable;
import java.util.Date;

//系统menu实体类
public class SysMenu implements Serializable {
    private static final long serialVersionUID = -5240316484297995750L;

    //主键id
    private String menuId;

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

    //创建时间
    private Date createTime;

    //修改人
    private String updateUser;

    //修改时间
    private Date updateTime;

    //状态   0：停用, 1：启用
    private Short status;

    //是否支持脱敏(0,否,1是)
    private Short desensitizeStatus;

    //所属模块id
    private String moduleId;

    //备注
    private String remark;

    //父级状态   0：停用, 1：启用
    private String parentStatus;

    //父级菜单url
    private String parentUrl;

//    private String moduelName;//所属模块名称
//    private Short moduelStatus;//所属模块状态 0：停用, 1：启用

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
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

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Short getDesensitizeStatus() {
        return desensitizeStatus;
    }

    public void setDesensitizeStatus(Short desensitizeStatus) {
        this.desensitizeStatus = desensitizeStatus;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}