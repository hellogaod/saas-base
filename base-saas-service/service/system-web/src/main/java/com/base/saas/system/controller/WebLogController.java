package com.base.saas.system.controller;

import com.base.saas.common.language.LocaleMessage;
import com.base.saas.common.logger.LoggerCommon;
import com.base.saas.common.response.BaseResponse;
import com.base.saas.common.response.ExceptionEnum;
import com.base.saas.common.response.ExceptionStackMessage;
import com.base.saas.common.response.ResponseLogMessageHandel;
import com.base.saas.common.userinfo.UserContextUtil;
import com.base.saas.system.service.WebLogService;
import com.base.saas.util.HeaderUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static org.hibernate.id.IdentifierGenerator.ENTITY_NAME;

/**
 * Title :
 * Description : @类注释说明写在此处@
 * Create on : 2018年09月11日
 * Copyright (C) zw.FinTec
 *
 * @author department:研发部
 * username:hanxiaoxue
 * @version 修改历史:
 * 修改人 修改日期 修改描述
 * -------------------------------------------<
 */
@RestController
@RequestMapping("/api/webLog")
@Api(value = "/api/webLog", description = "日志管理")
public class WebLogController {

    @Autowired
    private WebLogService webLogService;


    @GetMapping("/getLogList")
    @ApiOperation(value = "查询日志列表", httpMethod = "GET", notes = "查询日志列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize", value = "分页大小", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "pageIndex", value = "当前页码", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "loginAccount", value = "登录账号", dataType = "String", paramType = "query", required = false),
            @ApiImplicitParam(name = "method", value = "方法名", dataType = "String", paramType = "query", required = false),
            @ApiImplicitParam(name = "terminalType", value = "终端(0-pc端，1-安卓，2-ios)", dataType = "String", paramType = "query", required = false),
            @ApiImplicitParam(name = "operateType", value = "操作类型（0-登录日志，1-操作日志）", dataType = "String", paramType = "query", required = false),
            @ApiImplicitParam(name = "status", value = "执行状态（0成功1异常）", dataType = "String", paramType = "query", required = false),
            @ApiImplicitParam(name = "exceptionCode", value = "异常编码", dataType = "String", paramType = "query", required = false),
            @ApiImplicitParam(name = "startTime", value = "开始时间", dataType = "String", paramType = "query", required = false),
            @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "String", paramType = "query", required = false),
    })
    public BaseResponse getLogList(@RequestParam(value = "pageSize") Integer pageSize,
                                   @RequestParam(value = "pageIndex") Integer pageIndex,
                                   @RequestParam(value = "loginAccount",required = false) String loginAccount,
                                   @RequestParam(value = "method",required = false) String method,
                                   @RequestParam(value = "terminalType",required = false) String terminalType,
                                   @RequestParam(value = "operateType",required = false) String operateType,
                                   @RequestParam(value = "status",required = false) String status,
                                   @RequestParam(value = "exceptionCode",required = false) String exceptionCode,
                                   @RequestParam(value = "startTime",required = false) String startTime,
                                   @RequestParam(value = "endTime",required = false) String endTime ){
        Map map = new HashMap();
        map.put("pageSize",pageSize);
        map.put("pageIndex",pageIndex);
        map.put("loginAccount",loginAccount);
        map.put("method",method);
        map.put("terminalType",terminalType);
        map.put("operateType",operateType);
        map.put("status",status);
        map.put("exceptionCode",exceptionCode);
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        map.put("companyCode", UserContextUtil.getUserInfo().getCompanyCode());
        try {
            return webLogService.getLogList(map);
        }catch (Exception e){
            String localeTipMsg= LocaleMessage.get("message.weblog.manager.restTemplate");
            LoggerCommon.info(this.getClass(),"查询日志列表异常："+ ExceptionStackMessage.collectExceptionStackMsg(e));
            return BaseResponse.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME,"system.server.exception",
                    localeTipMsg)).body(ResponseLogMessageHandel.initResponseData(HttpStatus.BAD_REQUEST.value(), ExceptionEnum.EXCEPTION_CODE.code(),
                    LocaleMessage.get("system.server.exception"), ExceptionStackMessage.collectExceptionStackMsg(e),null));
        }
    }


    @ApiOperation(value = "查看异常信息", httpMethod = "GET", notes = "查看异常信息")
    @GetMapping("/getErrorLogById")
    public BaseResponse getErrorLogById(@RequestParam String id){
        try {
            return webLogService.getErrorMsgById(id);
        }catch(Exception e){
            String localeTipMsg=LocaleMessage.get("message.weblog.manager.restTemplate");
            LoggerCommon.info(this.getClass(),"查看异常信息异常："+ExceptionStackMessage.collectExceptionStackMsg(e));
            return BaseResponse.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME,"system.server.exception",
                    localeTipMsg)).body(ResponseLogMessageHandel.initResponseData(HttpStatus.BAD_REQUEST.value(), ExceptionEnum.EXCEPTION_CODE.code(),
                    LocaleMessage.get("system.server.exception"), ExceptionStackMessage.collectExceptionStackMsg(e),null));
        }
    }

}