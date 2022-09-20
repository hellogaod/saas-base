package com.base.saas.manager.model;

import java.io.Serializable;
import java.util.Date;

public class SysOtherConfig implements Serializable {
    private static final long serialVersionUID = -3176766286628083983L;
    private String id;

    private String otherName;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;

    private Short status;

    private String configColumn;

    private Short type;

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getOtherName() {
        return otherName;
    }

    public void setOtherName(String otherName) {
        this.otherName = otherName == null ? null : otherName.trim();
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

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public String getConfigColumn() {
        return configColumn;
    }

    public void setConfigColumn(String configColumn) {
        this.configColumn = configColumn == null ? null : configColumn.trim();
    }
}