package com.base.saas.common.controller;

import com.base.saas.common.model.ValidateCode;
import com.base.saas.common.service.ValidateCodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/validate/code")
@Api(tags = "验证码服务")
public class ValidateCodeController {

    @Autowired
    private ValidateCodeService validateCodeService;

    /**
     * 获取图片验证码
     *
     * @return CommonResult
     * @author Mr.Zhang
     * @since 2020-04-16
     */
    @ApiOperation(value = "获取验证码")
    @GetMapping(value = "/get")
    public ResponseEntity get() {
        ValidateCode validateCode = validateCodeService.get();
        return ResponseEntity.ok().body(validateCode);
    }

    /**
     * 检测验证码
     *
     * @return CommonResult
     * @author Mr.Zhang
     * @since 2020-04-16
     */
    @ApiOperation(value = "检测验证码")
    @GetMapping(value = "/check")
    public ResponseEntity validateCode(@RequestParam(value = "key") String key, @RequestParam(value = "code") String code) {
        boolean result = validateCodeService.check(new ValidateCode(key, code));
        return ResponseEntity.ok().body(result);
    }
}



