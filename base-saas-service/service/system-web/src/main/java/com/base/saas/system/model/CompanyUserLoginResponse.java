package com.base.saas.system.model;

import com.base.saas.common.userinfo.UserInfo;
import lombok.Data;

/**
 * Title :
 * Description : @企业用户登录返回参数@
 * Create on : 2018年08月28日
 * Copyright (C) zw.FinTec
 *
 * @author department:研发部
 * username:hanxiaoxue
 * @version 修改历史:
 * 修改人 修改日期 修改描述
 * -------------------------------------------<
 */
@Data
public class CompanyUserLoginResponse {

    String token;
    UserInfo userInfo;

}