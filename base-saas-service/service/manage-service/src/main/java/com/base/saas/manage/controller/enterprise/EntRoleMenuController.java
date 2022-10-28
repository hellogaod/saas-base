package com.base.saas.manage.controller.enterprise;

import com.base.saas.common.language.LocaleMessage;
import com.base.saas.common.logger.LoggerCommon;
import com.base.saas.manage.model.ReturnMap;
import com.base.saas.manage.model.enterprise.EntModule;
import com.base.saas.util.response.ExceptionStackMessage;
import com.base.saas.manage.service.enterprise.EntRoleMenuService;
import com.base.saas.util.HeaderUtil;
import com.base.saas.util.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/entRoleMenu")
@Api(value = "角色权限管理")
public class EntRoleMenuController {

    @Autowired
    private EntRoleMenuService roleMenuService;

    @GetMapping("/getMenuTreeByRoleId")
    @ApiOperation(value = "根据角色编号查询已有的菜单编号", notes = "根据角色编号查询已有的菜单编号")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", value = "角色id", dataType = "String", paramType = "query", required = true),
    })
    public ResponseEntity getMenuByRoleId(@RequestParam String roleId) {
        String logmsg = LocaleMessage.get("message.system.request.param.exception");
        //判断角色编号是否为空
        if (StringUtil.isEmpty(roleId)) {
            return ResponseEntity.ok().headers(HeaderUtil.createErrorMsg(logmsg)).body(null);
        }
        try {
            List<EntModule> menuList = roleMenuService.getModuleList(roleId);

            return ResponseEntity.ok().body(menuList);
        } catch (Exception e) {
            logmsg = LocaleMessage.get("message.query.errorMessage");
            LoggerCommon.info(this.getClass(), "根据角色编号查询已有的菜单编号异常：" + ExceptionStackMessage.collectExceptionStackMsg(e));
            return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(logmsg)).body(null);
        }

    }

    @PostMapping("/addRoleMenu")
    @ApiOperation(value = "添加角色权限", notes = "添加角色权限")
    public ResponseEntity addRoleMenu(@RequestBody Map data) {

        String logmsg = null;
        try {
            ReturnMap resultMap = roleMenuService.insertMenus((String) data.get("roleId"), (List<String>) data.get("menuIds"));
            boolean flag = resultMap.getCode() == 1;
            logmsg = LocaleMessage.get(resultMap.getMsg());
            if (flag) {
                return ResponseEntity.ok().body(null);
            } else {
                return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(logmsg)).body(null);
            }
        } catch (Exception e) {
            logmsg = LocaleMessage.get("message.system.save.fail");
            LoggerCommon.info(this.getClass(), "添加角色权限异常：" + ExceptionStackMessage.collectExceptionStackMsg(e));
            return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(logmsg)).body(null);
        }
    }
}
