package com.base.saas.manage.service.system;

import com.base.saas.manage.domain.model.ReturnMap;
import com.base.saas.manage.domain.entity.enterprise.EntMenu;
import com.base.saas.manage.domain.entity.system.SysUser;

import java.util.List;
import java.util.Map;

/**
 * Title :
 * Description : 系统管理端
 *
 * @author department:研发部
 * username:zh-pc
 * @version 修改历史:
 * 修改人 修改日期 修改描述
 * -------------------------------------------<
 */
public interface SysLoginService {

    //登录
    ReturnMap<SysUser> login(String account, String password) throws Exception;

    ReturnMap<List<EntMenu>> getMenuList(String userId) throws Exception;

    ReturnMap updateUserPwd(String account, String oldPwd, String newPwd) throws Exception;

    Map<String, Object> getUserRoleInfo(String userId) throws Exception;

    void updateLoginInfo(String userId, String remoteAdrr) throws Exception;
}
