package com.base.saas.manage.domain.entity.system;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

//系统三方配置参数实体类
public class SysOtherConfig implements Serializable {
    private static final long serialVersionUID = -3176766286628083983L;
    private String otherId;

    private String otherName;

    private String otherValue;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;

    //是否被选中
    private boolean isChecked;

    //1有效，0无效
    private int status;

    private int type;

    private List<SysOtherColumnsConf> detailList;

    public boolean isChecked() {
        return isChecked;
    }

    public String getOtherValue() {
        return otherValue;
    }

    public void setOtherValue(String otherValue) {
        this.otherValue = otherValue;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getOtherId() {
        return otherId;
    }

    public void setOtherId(String otherId) {
        this.otherId = otherId == null ? null : otherId.trim();
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<SysOtherColumnsConf> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<SysOtherColumnsConf> detailList) {
        this.detailList = detailList;
    }
}