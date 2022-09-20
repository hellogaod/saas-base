package com.base.saas.manager.controller;

import com.base.saas.manager.model.ValidateCode;
import com.base.saas.manager.service.ValidateCodeService;
import com.base.saas.common.response.BaseResponse;
import com.base.saas.common.response.ResponseLogMessageHandel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("api/validate/code")
@Api(tags = "验证码服务")
public class ValidateCodeController {

    private static final String ENTITY_NAME = "validateCode";

    @Autowired
    private ValidateCodeService validateCodeService;

    /**
     * 获取图片验证码
     * @author Mr.Zhang
     * @since 2020-04-16
     * @return CommonResult
     */
    @ApiOperation(value="获取验证码")
    @GetMapping(value = "/get")
    public BaseResponse get() {
        ValidateCode validateCode = validateCodeService.get();
        return BaseResponse.ok().body(ResponseLogMessageHandel.initSuccessResponseData(validateCode));
    }

    /**
     * 检测验证码
     * @author Mr.Zhang
     * @since 2020-04-16
     * @return CommonResult
     */
    @ApiOperation(value="检测验证码")
    @GetMapping(value = "/check")
    public BaseResponse validateCode(@Validated ValidateCode validateCode) {
        boolean result  = validateCodeService.check(validateCode);
        return BaseResponse.ok().body(ResponseLogMessageHandel.initSuccessResponseData(result));
    }
}



