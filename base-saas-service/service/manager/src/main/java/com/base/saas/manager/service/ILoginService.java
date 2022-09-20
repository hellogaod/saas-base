package com.base.saas.manager.service;

import java.util.Map;

/**
 * Title :
 * Description : @类注释说明写在此处@
 * Create on : 2018年05月24日
 * Copyright (C) zw.FinTec
 *
 * @author department:研发部
 * username:zh-pc
 * @version 修改历史:
 * 修改人 修改日期 修改描述
 * -------------------------------------------<
 */
public interface ILoginService {
    Map<String, String> login(String account, String password) throws Exception;

    Map<String, Object> getRoleList(String userId) throws Exception;

    Map<String, Object> updateUserPwd(String account, String oldPwd, String newPwd) throws Exception;

    Map<String, Object> getUserRoleInfo(String userId) throws Exception;

    void updateLoginInfo(String userId, String remoteAdrr)throws Exception;
}
