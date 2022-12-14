package com.base.saas.manage.domain.entity.enterprise;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class EntModule implements Serializable {
    private static final long serialVersionUID = -4231663552157478115L;

    //主键
    private String id;

    //企业编号
    private String companyCode;

    private String moduleId;//关联sys_module主键

    //系统名称
    private String moduleName;

    //创建人
    private String createUser;

    //创建时间
    private Date createTime;

    //修改人
    private String updateUser;

    //修改时间
    private Date updateTime;

    //备注
    private String remark;

    //状态
    private int status;

    //当前模块下配置的菜单信息
    private List<EntMenu> menuList;

    //当前模块下被选中的菜单信息
    private List<String> checkedMenuIds;

    public List<String> getCheckedMenuIds() {
        return checkedMenuIds;
    }

    public void setCheckedMenuIds(List<String> checkedMenuIds) {
        this.checkedMenuIds = checkedMenuIds;
    }

    public List<EntMenu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<EntMenu> menuList) {
        this.menuList = menuList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}