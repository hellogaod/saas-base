package com.base.saas.manager.service;


import com.base.saas.manager.model.ValidateCode;

/**
 * ValidateCodeService 接口
 * +----------------------------------------------------------------------
 * | VONECHAIN [ VONECHAIN赋能开发者，助力企业发展 ]
 * +----------------------------------------------------------------------
 * | Copyright (c) 2016~2020
 * +----------------------------------------------------------------------
 * | Licensed VONECHAIN并不是自由软件，未经许可不能去掉VONECHAIN相关版权
 * +----------------------------------------------------------------------
 * | Author:vonechain
 * +----------------------------------------------------------------------
 */
public interface ValidateCodeService {

    ValidateCode get();

    boolean check(ValidateCode validateCode);
}
