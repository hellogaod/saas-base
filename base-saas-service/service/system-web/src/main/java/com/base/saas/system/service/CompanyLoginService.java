package com.base.saas.system.service;

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
public interface CompanyLoginService {
    Map<String, String> comLogin(String account, String password, String companyCode) throws Exception;

    Map<String, Object> getComRoleList(String userId, String companyCode) throws Exception;

    Map<String, Object> updateComUserPwd(String account, String companyCode, String oldPwd, String newPwd) throws Exception;

    void updateComLoginInfo(String userId, String companyCode, String remoteAdrr);
}
