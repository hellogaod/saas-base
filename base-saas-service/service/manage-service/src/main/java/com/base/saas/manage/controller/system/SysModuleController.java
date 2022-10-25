package com.base.saas.manage.controller.system;

import com.base.saas.manage.model.ReturnMap;
import com.base.saas.manage.model.system.SysModule;
import com.base.saas.manage.service.system.SysModuleService;
import com.base.saas.common.language.LocaleMessage;
import com.base.saas.common.logger.LoggerCommon;
import com.base.saas.util.response.ExceptionStackMessage;
import com.base.saas.common.userinfo.UserContextUtil;
import com.base.saas.common.userinfo.UserInfo;
import com.base.saas.util.HeaderUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/sysModule")
@Api(value = "系统管理端模块管理")
public class SysModuleController {

    @Resource
    private SysModuleService sysModuleService;

    @ApiOperation(value = "查询模块列表", httpMethod = "GET", notes = "查询模块列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize", value = "分页大小", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "pageIndex", value = "当前页码", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "status", value = "状态", dataType = "int", paramType = "query", required = false),
            @ApiImplicitParam(name = "moduleName", value = "模块名称", dataType = "String", paramType = "query", required = false),
    })
    @GetMapping("/getModuleList")
    public ResponseEntity getModuleList(@RequestParam(value = "pageSize") Integer pageSize,
                                        @RequestParam(value = "pageIndex") Integer pageIndex,
                                        @RequestParam(value = "status", required = false) int status,
                                        @RequestParam(value = "moduleName", required = false) String moduleName) {
        Map map = new HashMap();
        map.put("moduleName", moduleName);
        map.put("status", status);
        PageHelper.startPage(pageIndex, pageSize, true);
        try {
            List<SysModule> moduleList = sysModuleService.getSysModuleList(status, moduleName);
            PageInfo pageInfo = new PageInfo(moduleList);
            return ResponseEntity.ok().body(pageInfo);
        } catch (Exception e) {
            String localeTipMsg = LocaleMessage.get("message.query.errorMessage");
            LoggerCommon.info(this.getClass(), "查询模块列表异常：" + ExceptionStackMessage.collectExceptionStackMsg(e));
            return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(localeTipMsg)).body(null);
        }

    }

    @ApiOperation(value = "根据主键查询模块信息", httpMethod = "GET", notes = "根据主键查询模块信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "modulId", value = "主键", dataType = "String", paramType = "query", required = true),
    })
    @GetMapping("/getModuleInfo")
    public ResponseEntity getModuleInfo(@RequestParam(value = "modulId") String modulId) {
        try {
            SysModule sysModule = sysModuleService.getModuleInfo(modulId);
            return ResponseEntity.ok().body(sysModule);
        } catch (Exception e) {
            String localeTipMsg = LocaleMessage.get("message.query.errorMessage");
            LoggerCommon.info(this.getClass(), "根据主键查询模块信息异常：" + ExceptionStackMessage.collectExceptionStackMsg(e));
            return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(localeTipMsg)).body(null);
        }

    }

    @ApiOperation(value = "保存系统模块", httpMethod = "POST", notes = "保存系统模块")
    @PostMapping("/saveSysModule")
    public ResponseEntity saveSysModule(@RequestBody SysModule sysModule) {
        UserInfo userInfo = UserContextUtil.getUserInfo();
        sysModule.setCreateUser(userInfo.getAccount());
        String localeTipMsg = null;
        try {
            ReturnMap returnMap = sysModuleService.saveSysModule(sysModule);

            localeTipMsg = LocaleMessage.get(returnMap.getMsg());
            if (returnMap.getCode() == 1) {
                LoggerCommon.info(this.getClass(), "保存系统模块成功");
                return ResponseEntity.ok().body(null);
            } else {
                LoggerCommon.info(this.getClass(), "保存系统模块失败:" + localeTipMsg);
                return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(localeTipMsg)).body(null);
            }
        } catch (Exception e) {
            localeTipMsg = LocaleMessage.get("message.system.save.fail");
            LoggerCommon.info(this.getClass(), "保存系统模块异常：" + ExceptionStackMessage.collectExceptionStackMsg(e));
            return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(localeTipMsg)).body(null);
        }

    }


    @ApiOperation(value = "修改系统模块", httpMethod = "POST", notes = "修改系统模块")
    @PostMapping("/updateSysModule")
    public ResponseEntity updateSysModule(@RequestBody SysModule sysModule) {
        UserInfo userInfo = UserContextUtil.getUserInfo();
        sysModule.setUpdateUser(userInfo.getAccount());
        String localeTipMsg = null;
        try {
            ReturnMap returnMap = sysModuleService.updateSysModule(sysModule);
            localeTipMsg = returnMap.getMsg();
            if (returnMap.getCode() == 1) {
                LoggerCommon.info(this.getClass(), "修改系统模块成功");
                return ResponseEntity.ok().body(null);
            } else {
                LoggerCommon.info(this.getClass(), "修改系统模块失败:" + localeTipMsg);
                return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(localeTipMsg)).body(null);
            }
        } catch (Exception e) {
            localeTipMsg = LocaleMessage.get("message.system.update.fail");
            LoggerCommon.info(this.getClass(), "修改系统模块异常：" + ExceptionStackMessage.collectExceptionStackMsg(e));
            return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(localeTipMsg)).body(null);
        }

    }

    @ApiOperation(value = "修改系统状态", httpMethod = "POST", notes = "修改系统状态")
    @PostMapping("/updateSysModuleStatus")
    public ResponseEntity updateStatus(@RequestBody SysModule sysModule) {
        //判断参数是否正确
        String localeTipMsg = LocaleMessage.get("message.system.request.param.exception");
        String moduleId = sysModule.getModuleId();

        if (StringUtil.isEmpty(moduleId)) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(localeTipMsg)).body(null);
        }

        UserInfo userInfo = UserContextUtil.getUserInfo();
        sysModule.setUpdateUser(userInfo.getAccount());
        try {
            ReturnMap returnMap = sysModuleService.updateSysModuleStatus(sysModule);
            localeTipMsg = LocaleMessage.get(returnMap.getMsg());

            if (returnMap.getCode() == 1) {
                LoggerCommon.info(this.getClass(), "修改系统模块状态成功");
                return ResponseEntity.ok().body(null);
            } else {
                LoggerCommon.info(this.getClass(), "修改系统模块状态失败：" + localeTipMsg);
                return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(localeTipMsg)).body(null);
            }
        } catch (Exception e) {
            localeTipMsg = LocaleMessage.get("message.system.operation.fail");
            LoggerCommon.info(this.getClass(), "修改系统状态异常：" + ExceptionStackMessage.collectExceptionStackMsg(e));
            return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(localeTipMsg)).body(null);
        }

    }


}
