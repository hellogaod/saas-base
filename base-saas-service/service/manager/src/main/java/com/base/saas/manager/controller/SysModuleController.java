package com.base.saas.manager.controller;

import com.base.saas.manager.model.SysModule;
import com.base.saas.manager.service.SysModuleDetailService;
import com.base.saas.manager.service.SysModuleService;
import com.base.saas.common.language.LocaleMessage;
import com.base.saas.common.logger.LoggerCommon;
import com.base.saas.common.response.BaseResponse;
import com.base.saas.common.response.ExceptionEnum;
import com.base.saas.common.response.ExceptionStackMessage;
import com.base.saas.common.response.ResponseLogMessageHandel;
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
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Title :
 * Description :
 * Create on : 2018年06月08日
 * Copyright (C) zw.FinTec
 *
 * @author department:研发部
 *         username: wangtao
 * @version 修改历史:
 *          修改人 修改日期 修改描述
 *          -------------------------------------------<
 */
@RestController
@RequestMapping("/api/sysModule")
@Api(value = "/api/sysModule", description = "模块管理")
public class SysModuleController {

    private static final String ENTITY_NAME = "sysModule";

    @Resource
    private SysModuleService sysModuleService;

    @Resource
    private SysModuleDetailService sysModuleDetailService;


    @GetMapping("/getEffectiveModule")
    @ApiOperation(value = "查询已启用的模块列表", notes = "查询已启用的模块列表")
    public BaseResponse getEffectiveModule(){
        try {
            List list = sysModuleService.getEffectiveModule();
            return BaseResponse.ok().body(ResponseLogMessageHandel.initSuccessResponseData(list));
        }catch (Exception e){
            String localeTipMsg = LocaleMessage.get("message.query.errorMessage");
            LoggerCommon.info(this.getClass(),"查询已启用的模块列表异常："+ ExceptionStackMessage.collectExceptionStackMsg(e));
            return BaseResponse.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME,"system.server.exception",
                    localeTipMsg)).body(ResponseLogMessageHandel.initResponseData(HttpStatus.BAD_REQUEST.value(), ExceptionEnum.EXCEPTION_CODE.code(),
                    LocaleMessage.get("system.server.exception"), ExceptionStackMessage.collectExceptionStackMsg(e),null));
        }
    }



    /**
     * 获取数据列表
     * @param pageSize
     * @param pageIndex
     * @param sysName
     * @return
     */
    @ApiOperation(value = "查询模块列表", httpMethod = "GET", notes = "查询模块列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize", value = "分页大小", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "pageIndex", value = "当前页码", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "sysName", value = "模块名称", dataType = "String", paramType = "query", required = false),
    })
    @GetMapping("/getModuleList")
    public BaseResponse getModuleList(@RequestParam(value = "pageSize") Integer pageSize,
                                        @RequestParam(value = "pageIndex") Integer pageIndex,
                                        @RequestParam(value = "sysName",required = false) String sysName){
        Map map = new HashMap();
        map.put("sysName",sysName);
        PageHelper.startPage(pageIndex, pageSize, true);
        try {
            List<SysModule> moduleList = sysModuleService.getSysModuleList(map);
            PageInfo pageInfo = new PageInfo(moduleList);
            return BaseResponse.ok().body( ResponseLogMessageHandel.initSuccessResponseData(pageInfo));
        }catch (Exception e){
            String localeTipMsg = LocaleMessage.get("message.query.errorMessage");
            LoggerCommon.info(this.getClass(),"查询模块列表异常："+ExceptionStackMessage.collectExceptionStackMsg(e));
            return BaseResponse.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME,"system.server.exception",
                    localeTipMsg)).body(ResponseLogMessageHandel.initResponseData(HttpStatus.BAD_REQUEST.value(), ExceptionEnum.EXCEPTION_CODE.code(),
                    LocaleMessage.get("system.server.exception"), ExceptionStackMessage.collectExceptionStackMsg(e),null));
        }

    }


    /**
     * 查询模块信息
     * @param sysCode
     * @return
     */
    @ApiOperation(value = "根据主键查询模块信息", httpMethod = "GET", notes = "根据主键查询模块信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sysCode", value = "主键", dataType = "String", paramType = "query", required = true),
    })
    @GetMapping("/getModuleInfo")
    public BaseResponse getModuleInfo(@RequestParam(value = "sysCode") String sysCode){
        try {
            SysModule sysModule = sysModuleService.getModuleInfo(sysCode);
            return BaseResponse.ok().body( ResponseLogMessageHandel.initSuccessResponseData(sysModule));
        }catch (Exception e){
            String localeTipMsg = LocaleMessage.get("message.query.errorMessage");
            LoggerCommon.info(this.getClass(),"根据主键查询模块信息异常："+ExceptionStackMessage.collectExceptionStackMsg(e));
            return BaseResponse.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME,"system.server.exception",
                    localeTipMsg)).body(ResponseLogMessageHandel.initResponseData(HttpStatus.BAD_REQUEST.value(), ExceptionEnum.EXCEPTION_CODE.code(),
                    LocaleMessage.get("system.server.exception"), ExceptionStackMessage.collectExceptionStackMsg(e),null));
        }

    }

    /**
     * 保存系统模块
     * @param sysModule
     * @return
     */
    @ApiOperation(value = "保存系统模块", httpMethod = "POST", notes = "保存系统模块")
    @PostMapping("/saveSysModule")
    public BaseResponse saveSysModule(@RequestBody SysModule sysModule){
        UserInfo userInfo = UserContextUtil.getUserInfo();
        sysModule.setCreateUser(userInfo.getAccount());
        String localeTipMsg =null;
        try {
            Map map = sysModuleService.saveSysModule(sysModule);
            boolean flag = (boolean) map.get("flag");
            localeTipMsg = LocaleMessage.get(map.get("msg").toString());
            if(flag){
                LoggerCommon.info(this.getClass(),"保存系统模块成功");
                return BaseResponse.ok().headers(HeaderUtil.createAlert(localeTipMsg,ENTITY_NAME)).body(null);
            }else{
                LoggerCommon.info(this.getClass(),"保存系统模块失败:"+localeTipMsg);
                return BaseResponse.badRequest().headers(HeaderUtil.createAlert(localeTipMsg,ENTITY_NAME)).body(null);
            }
        }catch (Exception e){
            localeTipMsg = LocaleMessage.get("message.system.save.fail");
            LoggerCommon.info(this.getClass(),"保存系统模块异常："+ExceptionStackMessage.collectExceptionStackMsg(e));
            return BaseResponse.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME,"system.server.exception",
                    localeTipMsg)).body(ResponseLogMessageHandel.initResponseData(HttpStatus.BAD_REQUEST.value(), ExceptionEnum.EXCEPTION_CODE.code(),
                    LocaleMessage.get("system.server.exception"), ExceptionStackMessage.collectExceptionStackMsg(e),null));
        }

    }


    /**
     * 修改
     * @param sysModule
     * @return
     */
    @ApiOperation(value = "修改系统模块", httpMethod = "POST", notes = "修改系统模块")
    @PostMapping("/updateSysModule")
    public BaseResponse updateSysModule(@RequestBody SysModule sysModule){
        UserInfo userInfo = UserContextUtil.getUserInfo();
        sysModule.setUpdateUser(userInfo.getAccount());
        String localeTipMsg =null;
        try {
            Map map = sysModuleService.updateSysModule(sysModule);
            boolean flag = (boolean) map.get("flag");
            localeTipMsg = map.get("msg").toString();
            if(flag){
                LoggerCommon.info(this.getClass(),"修改系统模块成功");
                return BaseResponse.ok().headers(HeaderUtil.createAlert(localeTipMsg,ENTITY_NAME)).body(null);
            }else{
                LoggerCommon.info(this.getClass(),"修改系统模块失败:"+localeTipMsg);
                return BaseResponse.badRequest().headers(HeaderUtil.createAlert(localeTipMsg,ENTITY_NAME)).body(null);
            }
        }catch (Exception e){
            localeTipMsg = LocaleMessage.get("message.system.update.fail");
            LoggerCommon.info(this.getClass(),"修改系统模块异常："+ExceptionStackMessage.collectExceptionStackMsg(e));
            return BaseResponse.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME,"system.server.exception",
                    localeTipMsg)).body(ResponseLogMessageHandel.initResponseData(HttpStatus.BAD_REQUEST.value(), ExceptionEnum.EXCEPTION_CODE.code(),
                    LocaleMessage.get("system.server.exception"), ExceptionStackMessage.collectExceptionStackMsg(e),null));
        }

    }

    /**
     * 修改状态
     * @param data
     * @return
     */
    @ApiOperation(value = "修改系统状态", httpMethod = "POST", notes = "修改系统状态")
    @PostMapping("/updateStatus")
    public BaseResponse updateStatus(@RequestBody Map data){
        //判断参数是否正确
        String localeTipMsg = LocaleMessage.get("message.system.request.param.exception");;
        if(null==data.get("sysCode")||StringUtil.isEmpty(data.get("sysCode").toString())){
            return BaseResponse.ok().headers(HeaderUtil.createAlert(localeTipMsg,ENTITY_NAME)).body(null);
        }
        if(null==data.get("status")||StringUtil.isEmpty(data.get("status").toString())){
            return BaseResponse.ok().headers(HeaderUtil.createAlert(localeTipMsg,ENTITY_NAME)).body(null);
        }
        UserInfo userInfo = UserContextUtil.getUserInfo();
        SysModule sysModule = new SysModule();
        sysModule.setSysCode(data.get("sysCode").toString());
        sysModule.setStatus(Short.parseShort(data.get("status").toString()));
        sysModule.setUpdateUser(userInfo.getAccount());
        try {
            Map repMap = sysModuleService.updateSysModuleStatus(sysModule);
            localeTipMsg=LocaleMessage.get(repMap.get("msg").toString());
            boolean flag = (boolean) repMap.get("flag");
            if(flag){
                LoggerCommon.info(this.getClass(),"修改系统模块状态成功");
                return BaseResponse.ok().headers(HeaderUtil.createAlert(localeTipMsg,ENTITY_NAME)).body(null);
            }else {
                LoggerCommon.info(this.getClass(),"修改系统模块状态失败："+localeTipMsg);
                return BaseResponse.badRequest().headers(HeaderUtil.createAlert(localeTipMsg, ENTITY_NAME)).body(null);
            }
        }catch(Exception e){
            localeTipMsg=LocaleMessage.get("message.system.operation.fail");
            LoggerCommon.info(this.getClass(),"修改系统状态异常："+ExceptionStackMessage.collectExceptionStackMsg(e));
            return BaseResponse.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME,"system.server.exception",
                    localeTipMsg)).body(ResponseLogMessageHandel.initResponseData(HttpStatus.BAD_REQUEST.value(), ExceptionEnum.EXCEPTION_CODE.code(),
                    LocaleMessage.get("system.server.exception"), ExceptionStackMessage.collectExceptionStackMsg(e),null));
        }

    }


    /**
     * 根据syscode查询模块相关的菜单
     * @param sysCode
     * @return
     */
    @ApiOperation(value = "根据模块主键查询模块相关的菜单", httpMethod = "GET", notes = "根据模块主键查询模块相关的菜单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sysCode", value = "模块主键", dataType = "String", paramType = "query", required = true),
    })
    @GetMapping("/getModuleMenuListBySysCode")
    public BaseResponse getModuleMenuListBySysCode(@RequestParam(value = "sysCode") String sysCode){
        try {
            List<Map> moduleMenuList = sysModuleDetailService.getModuleMenuListBySysCode(sysCode);
            return BaseResponse.ok().body(ResponseLogMessageHandel.initSuccessResponseData(moduleMenuList));
        }catch (Exception e){
            String localeTipMsg=LocaleMessage.get("message.query.errorMessage");
            LoggerCommon.info(this.getClass(),"根据模块主键查询模块相关的菜单异常："+ExceptionStackMessage.collectExceptionStackMsg(e));
            return BaseResponse.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME,"system.server.exception",
                    localeTipMsg)).body(ResponseLogMessageHandel.initResponseData(HttpStatus.BAD_REQUEST.value(), ExceptionEnum.EXCEPTION_CODE.code(),
                    LocaleMessage.get("system.server.exception"), ExceptionStackMessage.collectExceptionStackMsg(e),null));
        }

    }

}
