package com.base.saas.manage.controller.enterprise;

import com.base.saas.language.LocaleMessage;
import com.base.saas.logger.LoggerCommon;
import com.base.saas.manage.domain.model.ReturnMap;
import com.base.saas.manage.domain.entity.enterprise.EntRole;
import com.base.saas.util.ExceptionStackUtils;
import com.base.saas.userinfo.UserContextUtil;
import com.base.saas.userinfo.UserInfo;
import com.base.saas.manage.service.enterprise.EntRoleService;
import com.base.saas.util.HeaderUtil;
import com.base.saas.util.StringUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/entRole")
@Api(tags = "角色管理")
public class EntRoleController {

    @Autowired
    private EntRoleService roleService;

    @GetMapping("/getRoleList")
    @ApiOperation(value = "获取角色列表", notes = "获取角色列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize", value = "分页大小", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "pageIndex", value = "当前页码", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "roleName", value = "角色名称", dataType = "String", paramType = "query", required = false),
    })
    public ResponseEntity getRoleList(@RequestParam("pageSize") Integer pageSize,
                                      @RequestParam("pageIndex") Integer pageIndex,
                                      @RequestParam(value = "roleName", required = false) String roleName) {

        try {
            PageHelper.startPage(pageIndex, pageSize, true);
            List<EntRole> list = roleService.getRoleList(-1, roleName, UserContextUtil.getUserInfo().getCompanyCode());
            PageInfo pageInfo = new PageInfo(list);
            return ResponseEntity.ok().body(pageInfo);
        } catch (Exception e) {
            String logmsg = LocaleMessage.get("message.query.errorMessage");
            LoggerCommon.info(this.getClass(), "获取角色列表异常：" + ExceptionStackUtils.collectExceptionStackMsg(e));
            return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(logmsg)).body(null);
        }
    }

    @PostMapping("/addRole")
    @ApiOperation(value = "添加角色", notes = "添加角色")
    public ResponseEntity addRole(@RequestBody EntRole role) {

        String logmsg = null;
        try {
            ReturnMap map = roleService.addRole(role);
            boolean flag = map.getCode() == 1;
            logmsg = LocaleMessage.get(map.getMsg());
            if (flag) {
                return ResponseEntity.ok().body(null);
            } else {
                return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(logmsg)).body(null);
            }
        } catch (Exception e) {
            logmsg = LocaleMessage.get("message.system.save.fail");
            LoggerCommon.info(this.getClass(), "添加角色异常：" + ExceptionStackUtils.collectExceptionStackMsg(e));
            return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(logmsg)).body(null);
        }
    }

    @PostMapping("/updateStatus")
    @ApiOperation(value = "角色启用停用", notes = "角色启用停用")
    public ResponseEntity updateStatus(@RequestBody EntRole data) {
        String logmsg = LocaleMessage.get("message.system.request.param.exception");

        if (null == data || StringUtil.isEmpty(data.getRoleId())) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(logmsg)).body(null);
        }

        try {
            ReturnMap map = roleService.updateState(data);
            boolean flag = map.getCode() == 1;
            String msg = LocaleMessage.get(map.getMsg());
            logmsg = LocaleMessage.get(msg);
            if (flag) {
                return ResponseEntity.ok().body(null);
            } else {
                return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(logmsg)).body(null);
            }
        } catch (Exception e) {
            logmsg = LocaleMessage.get("message.system.update.fail");
            LoggerCommon.info(this.getClass(), "修改角色状态异常：" + ExceptionStackUtils.collectExceptionStackMsg(e));
            return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(logmsg)).body(null);
        }
    }

    @GetMapping("/getRoleById")
    @ApiOperation(value = "查询单个角色", notes = "查询单个角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", value = "角色id", dataType = "String", paramType = "query", required = true),
    })
    public ResponseEntity getRoleById(@RequestParam String roleId) {
        try {
            EntRole role = roleService.getRoleById(roleId);
            return ResponseEntity.ok().body(role);
        } catch (Exception e) {
            String logmsg = LocaleMessage.get("message.query.errorMessage");
            LoggerCommon.info(this.getClass(), "查询单个角色异常：" + ExceptionStackUtils.collectExceptionStackMsg(e));
            return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(logmsg)).body(null);
        }
    }

    @PostMapping("/updateRole")
    @ApiOperation(value = "修改角色信息", notes = "修改角色信息")
    public ResponseEntity updateUser(@RequestBody EntRole role) {
        //获取登录用户信息
        UserInfo userInfo = UserContextUtil.getUserInfo();
        role.setUpdateUser(userInfo.getAccount());
        role.setCompanyCode(userInfo.getCompanyCode());
        String logmsg = null;
        try {
            ReturnMap map = roleService.updateRole(role);
            boolean flag = map.getCode() == 1;
            logmsg = LocaleMessage.get(map.getMsg());
            if (flag) {
                return ResponseEntity.ok().body(null);
            } else {
                return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(logmsg)).body(null);
            }
        } catch (Exception e) {
            logmsg = LocaleMessage.get("message.system.update.fail");
            LoggerCommon.info(this.getClass(), "修改角色信息异常：" + ExceptionStackUtils.collectExceptionStackMsg(e));
            return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(logmsg)).body(null);
        }
    }

}
