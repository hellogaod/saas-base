package com.base.saas.manager.controller;

import com.base.saas.manager.model.SysWebLogRequest;
import com.base.saas.manager.service.SysWebLogService;
import com.base.saas.common.language.LocaleMessage;
import com.base.saas.common.logger.LoggerCommon;
import com.base.saas.common.logger.SysWebLog;
import com.base.saas.common.response.*;
import com.base.saas.util.CreateIDUtil;
import com.base.saas.util.HeaderUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName SysWebLogController
 * @Description
 * @Author coder_bao
 * @Date 2018/9/5 11:31
 */
@RestController
@RequestMapping("/api/weblog")
public class SysWebLogController {
    private static final String ENTITY_NAME = "sys_web_log";
    @Autowired
    private SysWebLogService sysWebLogService;

    @PostMapping(value = "/addweblog")
    @ApiOperation(value = "新增系统日志", notes = "新增系统日志")
    public BaseResponse<ResponseInfo> addSysWebLogInfo(@RequestBody SysWebLog sysWebLog){
        LoggerCommon.info(this.getClass(),"新增系统日志"+sysWebLog.getMethod());
        if(StringUtils.isNotEmpty(sysWebLog.getMethod()) && !sysWebLog.getMethod().contains("file")){
            sysWebLog.setId(CreateIDUtil.getId());
            try {
                sysWebLogService.addSysWebLogInfo(sysWebLog);
            } catch (Exception e) {
                String localeMsg = LocaleMessage.get("message.system.errorMessage");
                LoggerCommon.info(this.getClass(),"新增系统日志异常信息："+ ExceptionStackMessage.collectExceptionStackMsg(e));
                return BaseResponse.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME,"The addSysWebLogInfo Exception",
                        localeMsg)).body(null);
            }
            LoggerCommon.info(this.getClass(),"新增系统日志成功");
        }else{
            LoggerCommon.info(this.getClass(),"文件上传，跳过日志添加");
        }
        return BaseResponse.ok().body(null);
    }

    @ApiOperation(value = "查询日志列表", httpMethod = "GET", notes = "查询日志列表")
    @GetMapping("/getSysWebLogList")
    public BaseResponse getSysWebLogList(@RequestParam(value = "pageSize",required = true) Integer pageSize,
                                           @RequestParam(value = "pageIndex",required = true) Integer pageIndex,
                                           @RequestParam(value = "loginAccount",required = false) String loginAccount,
                                           @RequestParam(value = "companyCode",required = false) String companyCode,
                                           @RequestParam(value = "method",required = false) String method,
                                           @RequestParam(value = "terminalType",required = false) String terminalType,
                                           @RequestParam(value = "operateType",required = false) String operateType,
                                           @RequestParam(value = "status",required = false) String status,
                                           @RequestParam(value = "exceptionCode",required = false) String exceptionCode,
                                           @RequestParam(value = "startTime",required = false) String startTime,
                                           @RequestParam(value = "endTime",required = false) String endTime){
        LoggerCommon.info(this.getClass(),"查询日志列表");
        PageInfo pageInfo = null;
        SysWebLogRequest sysWebLogRequest  = new SysWebLogRequest(loginAccount,companyCode,method,terminalType,operateType,status,exceptionCode,startTime,endTime,pageSize,pageIndex);
        try {
            PageHelper.startPage(pageIndex, pageSize, true);
            List<SysWebLog> sysWebLogList = sysWebLogService.getSysWebLogList(sysWebLogRequest);
            pageInfo = new PageInfo(sysWebLogList);
        } catch (Exception e) {
            String localeMsg = LocaleMessage.get("message.system.errorMessage");
            LoggerCommon.info(this.getClass(),"查询日志列表异常信息："+ExceptionStackMessage.collectExceptionStackMsg(e));
            return BaseResponse.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME,"system.server.exception",
                    localeMsg)).body(ResponseLogMessageHandel.initResponseData(HttpStatus.BAD_REQUEST.value(), ExceptionEnum.EXCEPTION_CODE.code(),
                    LocaleMessage.get("system.server.exception"), ExceptionStackMessage.collectExceptionStackMsg(e),null));
        }
        LoggerCommon.info(this.getClass(),"成功查询日志列表数据");
        return BaseResponse.ok().headers(HeaderUtil.createAlert(LocaleMessage.get("message.system.successMessage"),ENTITY_NAME)).body(pageInfo);
    }

    @ApiOperation(value = "查询异常日志堆栈信息", httpMethod = "GET", notes = "查询异常日志堆栈信息")
    @GetMapping("/getExceptionStackMsgById")
    public BaseResponse getExceptionStackMsgById(@RequestParam(value = "id") String id){
        LoggerCommon.info(this.getClass(),"查询异常日志堆栈信息");
        String exceptionStacMsg = null;
        try {
            exceptionStacMsg = sysWebLogService.getExceptionStackMsgById(id);
        } catch (Exception e) {
            String localeMsg = LocaleMessage.get("message.system.errorMessage");
            LoggerCommon.info(this.getClass(),"查询异常日志堆栈信息异常："+ExceptionStackMessage.collectExceptionStackMsg(e));
            return BaseResponse.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME,"system.server.exception",
                    localeMsg)).body(ResponseLogMessageHandel.initResponseData(HttpStatus.BAD_REQUEST.value(), ExceptionEnum.EXCEPTION_CODE.code(),
                    LocaleMessage.get("system.server.exception"), ExceptionStackMessage.collectExceptionStackMsg(e),null));
        }
        LoggerCommon.info(this.getClass(),"成功查询异常日志堆栈信息");
        return BaseResponse.ok().headers(HeaderUtil.createAlert("find success",ENTITY_NAME)).body(
                ResponseLogMessageHandel.initSuccessResponseData(exceptionStacMsg)
        );
    }


}
