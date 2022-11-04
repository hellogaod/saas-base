package com.base.saas.manage.controller.enterprise;

import com.base.saas.language.LocaleMessage;
import com.base.saas.logger.LoggerCommon;
import com.base.saas.manage.domain.model.ReturnMap;
import com.base.saas.manage.domain.entity.enterprise.EntProfile;
import com.base.saas.util.ExceptionStackUtils;
import com.base.saas.userinfo.UserContextUtil;
import com.base.saas.userinfo.UserInfo;
import com.base.saas.manage.service.enterprise.EntProfileService;
import com.base.saas.util.HeaderUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/entProfile")
@Api(tags = "公司简介配置")
public class EntProfileController {

    private static final String ENTITY_NAME = "companyProfile";

    @Autowired
    private EntProfileService companyProfileService;

    @GetMapping("/getEntProfile")
    @ApiOperation(value = "获取公司简介", httpMethod = "GET", notes = "获取公司简介")
    public ResponseEntity getCompanyProfile() {
        LoggerCommon.info(this.getClass(), "获取公司简介");
        UserInfo userInfo = UserContextUtil.getUserInfo();
        String companyCode = userInfo.getCompanyCode();
        EntProfile companyProfile = null;
        String localeTipMsg = null;
        try {
            companyProfile = companyProfileService.getInfo(companyCode);
        } catch (Exception e) {
            localeTipMsg = LocaleMessage.get("message.query.errorMessage");
            LoggerCommon.info(this.getClass(), "获取公司简介异常：" + ExceptionStackUtils.collectExceptionStackMsg(e));
            return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(localeTipMsg)).body(null);
        }
        LoggerCommon.info(this.getClass(), "获取公司简介成功");
        return ResponseEntity.ok().body(companyProfile);
    }

    @PostMapping("/saveEntProfile")
    @ApiOperation(value = "保存公司简介", httpMethod = "POST", notes = "保存公司简介")
    public ResponseEntity saveCompanyProfile(@RequestBody EntProfile companyProfile) {
        LoggerCommon.info(this.getClass(), "保存公司简介");
        UserInfo userInfo = UserContextUtil.getUserInfo();
        companyProfile.setCompanyCode(userInfo.getCompanyCode());
        ReturnMap map = null;
        String localeTipMsg = null;
        try {
            map = companyProfileService.saveCompanyProfile(companyProfile);

            localeTipMsg = LocaleMessage.get(map.getMsg());
            if (map.getCode() == 1) {
                LoggerCommon.info(this.getClass(), "保存公司简介成功");
                return ResponseEntity.ok().body(null);
            } else {
                return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(localeTipMsg)).body(null);
            }
        } catch (Exception e) {
            localeTipMsg = LocaleMessage.get("message.system.save.fail");
            LoggerCommon.info(this.getClass(), "保存公司简介异常：" + ExceptionStackUtils.collectExceptionStackMsg(e));
            return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(localeTipMsg)).body(null);
        }

    }

}
