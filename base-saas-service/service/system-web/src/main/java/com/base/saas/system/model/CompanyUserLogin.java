package com.base.saas.system.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Title :
 * Description : @企业用户登录@
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
public class CompanyUserLogin {
    @ApiModelProperty("企业编码")
    private String companyCode;
    @ApiModelProperty("用户名称")
    private String userName;
    @ApiModelProperty("密码")
    private String password;
    @ApiModelProperty("验证码key")
    private String key;
    @ApiModelProperty("验证码code")
    private String code;
}