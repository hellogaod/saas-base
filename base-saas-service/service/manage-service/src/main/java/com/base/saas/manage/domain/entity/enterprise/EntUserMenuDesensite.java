package com.base.saas.manage.domain.entity.enterprise;

import java.io.Serializable;
import java.util.Date;

/**
 * Title :
 * Description : 当前用户对某一个菜单是否脱敏
 */
public class EntUserMenuDesensite implements Serializable {
    private static final long serialVersionUID = -7368857570129948793L;

    //系统编号
    private String id;

    //企业编号
    private String companyCode;

    //用户编号
    private String userId;

    //菜单资源编号
    private String menuId;

    //创建人
    private String createUser;

    //创建时间
    private Date createTime;

    //修改人
    private String updateUser;

    //修改时间
    private Date updateTime;

    //是否脱敏（0-不脱敏，1-脱敏）
    private Short isDesensite;

    public Short getIsDesensite() {
        return isDesensite;
    }

    public void setIsDesensite(Short isDesensite) {
        this.isDesensite = isDesensite;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode == null ? null : companyCode.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId == null ? null : menuId.trim();
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