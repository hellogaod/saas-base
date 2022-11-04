package com.base.saas.manage.controller.enterprise;

import com.base.saas.manage.domain.entity.enterprise.EntLog;
import com.base.saas.manage.domain.model.EntLogRequest;
import com.base.saas.manage.service.enterprise.EntLogService;
import com.base.saas.language.LocaleMessage;
import com.base.saas.logger.LoggerCommon;
import com.base.saas.util.CreateIDUtil;
import com.base.saas.util.HeaderUtil;
import com.base.saas.util.ExceptionStackUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/entLog")
@Api(tags = "企业日志")
public class EntLogController {

    @Autowired
    private EntLogService sysWebLogService;

    @PostMapping(value = "/addLog")
    @ApiOperation(value = "新增系统日志", notes = "新增系统日志")
    public ResponseEntity addLog(@RequestBody Map map) {

        EntLog sysWebLog = new EntLog();
        try {
            sysWebLog.setOperateType((String) map.get("operateType"));
            sysWebLog.setTerminalType((String) map.get("terminalType"));
            sysWebLog.setOperateIp((String) map.get("operateIp"));
            sysWebLog.setMethodArgs((String) map.get("methodArgs"));
            sysWebLog.setMethod((String) map.get("method"));
            sysWebLog.setLoginAccount((String) map.get("loginAccount"));
            sysWebLog.setCreateBy((String) map.get("createBy"));
            sysWebLog.setCompanyCode((String) map.get("companyCode"));
            sysWebLog.setCompanyName((String) map.get("companyName"));
            sysWebLog.setStatusCode((String) map.get("statusCode"));
            sysWebLog.setStatus((String) map.get("status"));
            sysWebLog.setExceptionCode((String) map.get("exceptionCode"));
            sysWebLog.setExceptionStackMsg((String) map.get("exceptionStackMsg"));
            sysWebLog.setExceptionDescription((String) map.get("exceptionDescription"));
            sysWebLog.setMessages((String) map.get("messages"));
        } catch (Exception e) {
            e.getMessage();
        }

        LoggerCommon.info(this.getClass(), "新增系统日志" + sysWebLog.getMethod());
        if (StringUtils.isNotEmpty(sysWebLog.getMethod()) && !sysWebLog.getMethod().contains("file")) {
            sysWebLog.setId(CreateIDUtil.getId());
            try {
                sysWebLogService.addEntLogInfo(sysWebLog);
            } catch (Exception e) {
                String localeMsg = LocaleMessage.get("message.system.errorMessage");
                LoggerCommon.info(this.getClass(), "新增系统日志异常信息：" + ExceptionStackUtils.collectExceptionStackMsg(e));
                return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(localeMsg)).body(null);
            }
            LoggerCommon.info(this.getClass(), "新增系统日志成功");
        } else {
            LoggerCommon.info(this.getClass(), "文件上传，跳过日志添加");
        }
        return ResponseEntity.ok().body(null);
    }

    @ApiOperation(value = "查询日志列表", httpMethod = "GET", notes = "查询日志列表")
    @GetMapping("/getLogList")
    public ResponseEntity getLogList(@RequestParam(value = "pageSize", required = true) Integer pageSize,
                                     @RequestParam(value = "pageIndex", required = true) Integer pageIndex,
                                     @RequestParam(value = "loginAccount", required = false) String loginAccount,
                                     @RequestParam(value = "companyCode", required = false) String companyCode,
                                     @RequestParam(value = "method", required = false) String method,
                                     @RequestParam(value = "terminalType", required = false) String terminalType,
                                     @RequestParam(value = "operateType", required = false) String operateType,
                                     @RequestParam(value = "status", required = false) String status,
                                     @RequestParam(value = "exceptionCode", required = false) String exceptionCode,
                                     @RequestParam(value = "startTime", required = false) String startTime,
                                     @RequestParam(value = "endTime", required = false) String endTime) {
        LoggerCommon.info(this.getClass(), "查询日志列表");
        PageInfo pageInfo = null;
        EntLogRequest sysWebLogRequest = new EntLogRequest(loginAccount, companyCode, method, terminalType, operateType, status, exceptionCode, startTime, endTime, pageSize, pageIndex);
        try {
            PageHelper.startPage(pageIndex, pageSize, true);
            List<EntLog> sysWebLogList = sysWebLogService.getEntLogList(sysWebLogRequest);
            pageInfo = new PageInfo(sysWebLogList);
        } catch (Exception e) {
            String localeMsg = LocaleMessage.get("message.system.errorMessage");
            LoggerCommon.info(this.getClass(), "查询日志列表异常信息：" + ExceptionStackUtils.collectExceptionStackMsg(e));
            return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(localeMsg)).body(null);
        }
        LoggerCommon.info(this.getClass(), "成功查询日志列表数据");
        return ResponseEntity.ok().body(pageInfo);
    }

}
