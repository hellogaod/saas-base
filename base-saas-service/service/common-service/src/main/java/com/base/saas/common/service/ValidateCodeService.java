package com.base.saas.common.service;


import com.base.saas.common.model.ValidateCode;

/**
 * ValidateCodeService 验证码获取接口
 */
public interface ValidateCodeService {

    ValidateCode get();

    boolean check(ValidateCode validateCode);
}
