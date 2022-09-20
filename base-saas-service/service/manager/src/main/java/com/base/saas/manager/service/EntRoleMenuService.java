package com.base.saas.manager.service;

import java.util.List;

/**
 * Title :
 * Description : @企业角色权限@
 * Create on : 2018年06月08日
 * Copyright (C) zw.FinTec
 *
 * @author department:研发部
 *         username:chengrui
 * @version 修改历史:
 *          修改人 修改日期 修改描述
 *          -------------------------------------------<
 */
public interface EntRoleMenuService {

    int addEntRoleMenu(String roleId, List<String> sysCodeList, String companyCode, String orgId);

}