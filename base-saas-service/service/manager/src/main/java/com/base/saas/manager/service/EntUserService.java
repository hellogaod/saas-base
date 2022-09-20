package com.base.saas.manager.service;

import java.util.Map;

/**
 * Title :
 * Description :
 * Create on : 2018年06月11日
 * Copyright (C) zw.FinTec
 *
 * @author department:研发部
 *         username: wangtao
 * @version 修改历史:
 *          修改人 修改日期 修改描述
 *          -------------------------------------------<
 */
public interface EntUserService {
    //获取用户信息
    Map getUserByCompanyCode(String companyCode);
}
