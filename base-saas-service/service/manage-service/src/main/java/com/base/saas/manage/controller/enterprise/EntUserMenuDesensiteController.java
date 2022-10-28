package com.base.saas.manage.controller.enterprise;

import com.base.saas.common.language.LocaleMessage;
import com.base.saas.common.logger.LoggerCommon;
import com.base.saas.manage.model.ReturnMap;
import com.base.saas.manage.model.enterprise.EntModule;
import com.base.saas.manage.model.enterprise.EntUserMenuDesensite;
import com.base.saas.util.response.ExceptionStackMessage;
import com.base.saas.manage.service.enterprise.EntMenuDesensiteService;
import com.base.saas.util.HeaderUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/userDatePermission")
@Api(value = "数据权限管理")
public class EntUserMenuDesensiteController {

    @Autowired
    private EntMenuDesensiteService userDataPermissionService;

    @GetMapping("/getUserPermissonListByRoleId")
    @ApiOperation(value = "获取用户权限数据权限", notes = "获取数据权限")
    public ResponseEntity getUserDataPermisson(@RequestBody Map param) {
        try {
            List<EntModule> userDataPermission = userDataPermissionService.getUserDataPermisson((String) param.get("roleId"), (String) param.get("userId"));
            return ResponseEntity.ok().body(userDataPermission);
        } catch (Exception e) {
            String logmsg = LocaleMessage.get("message.query.errorMessage");
            LoggerCommon.info(this.getClass(), "获取数据权限异常：" + ExceptionStackMessage.collectExceptionStackMsg(e));
            return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(logmsg)).body(null);
        }
    }

    @PostMapping("/opertionDesensite")
    @ApiOperation(value = "添加或修改数据权限", notes = "添加数据权限")
    public ResponseEntity opertionDesensite(@RequestBody EntUserMenuDesensite data) {

        String logmsg = null;
        try {
            ReturnMap returnMap = userDataPermissionService.addOrUpdateUserDataPermisson(data);
            if (returnMap.getCode() == 1) {
                return ResponseEntity.ok().body(null);
            }

            return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(LocaleMessage.get(returnMap.getMsg()))).body(null);

        } catch (Exception e) {
            logmsg = LocaleMessage.get("message.system.save.fail");
            LoggerCommon.info(this.getClass(), "添加用户异常：" + ExceptionStackMessage.collectExceptionStackMsg(e));
            return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(logmsg)).body(null);
        }
    }


}
