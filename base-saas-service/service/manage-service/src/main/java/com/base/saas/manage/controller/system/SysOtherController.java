package com.base.saas.manage.controller.system;

import com.base.saas.manage.domain.model.ReturnMap;
import com.base.saas.manage.domain.entity.system.SysOtherConfig;
import com.base.saas.manage.service.system.SysOtherConfigService;
import com.base.saas.language.LocaleMessage;
import com.base.saas.logger.LoggerCommon;
import com.base.saas.util.ExceptionStackUtils;
import com.base.saas.util.HeaderUtil;
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
@RequestMapping("/api/sysOther")
@Api(tags = "系统参数配置")
public class SysOtherController {

    @Autowired
    private SysOtherConfigService sysOtherConfigService;

    /**
     * 查询数据列表
     *
     * @param pageSize
     * @param pageIndex
     * @param otherName
     * @return
     */
    @ApiOperation(value = "查询三方配置列表", httpMethod = "GET", notes = "查询三方配置列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize", value = "分页大小", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "pageIndex", value = "当前页码", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "otherName", value = "配置名称", dataType = "String", paramType = "query", required = false),
            @ApiImplicitParam(name = "status", value = "状态", dataType = "short", paramType = "query", required = false),
            @ApiImplicitParam(name = "type", value = "配置类型", dataType = "short", paramType = "query", required = false),
    })
    @GetMapping("/getOtherList")
    public ResponseEntity getOtherList(@RequestParam(value = "pageSize") Integer pageSize,
                                       @RequestParam(value = "pageIndex") Integer pageIndex,
                                       @RequestParam(value = "otherName", required = false) String otherName,
                                       @RequestParam(value = "status", required = false) Short status,
                                       @RequestParam(value = "type", required = false) Short type) {
        PageHelper.startPage(pageIndex, pageSize, true);

        try {
            List<SysOtherConfig> list = sysOtherConfigService.getOtherConfigList(status, type, otherName);
            PageInfo pageInfo = new PageInfo(list);
            return ResponseEntity.ok().body(pageInfo);
        } catch (Exception e) {
            String logmsg = LocaleMessage.get("message.query.errorMessage");
            LoggerCommon.info(this.getClass(), "查询配置列表异常：" + ExceptionStackUtils.collectExceptionStackMsg(e));
            return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(logmsg)).body(null);
        }
    }


    @ApiOperation(value = "保存参数配置", httpMethod = "POST", notes = "保存参数配置")
    @PostMapping("/saveOtherConfig")
    public ResponseEntity saveOtherConfig(@RequestBody SysOtherConfig data) {
        //解析请求参数
        String logmsg = null;
        try {
            ReturnMap respMap = sysOtherConfigService.saveOtherConfig(data);
            logmsg = LocaleMessage.get(respMap.getMsg());
            if (respMap.getCode() == 0) {
                LoggerCommon.info(this.getClass(), "保存系统参数配置失败:" + logmsg);
                return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(logmsg)).body(null);
            }
            LoggerCommon.info(this.getClass(), "保存系统参数配置成功");
            return ResponseEntity.ok().body(null);
        } catch (Exception e) {
            logmsg = LocaleMessage.get("message.system.save.fail");
            LoggerCommon.info(this.getClass(), "保存参数配置异常：" + ExceptionStackUtils.collectExceptionStackMsg(e));
            return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(logmsg)).body(null);
        }
    }

    /**
     * 修改状态
     *
     * @param data {id:,status:}
     * @return
     */
    @ApiOperation(value = "修改状态", httpMethod = "POST", notes = "修改状态")
    @PostMapping("/updateStatus")
    public ResponseEntity updateStatus(@RequestBody SysOtherConfig data) {


        String logmsg = null;
        try {
            //修改
            ReturnMap map = sysOtherConfigService.updateStatus(data);
            logmsg = LocaleMessage.get(map.getMsg());

            if (map.getCode() == 0) {
                LoggerCommon.info(this.getClass(), "修改状态失败：" + logmsg);
                return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(logmsg)).body(null);
            }
            LoggerCommon.info(this.getClass(), "修改状态成功");
            return ResponseEntity.ok().body(null);
        } catch (Exception e) {
            logmsg = LocaleMessage.get("message.system.operation.fail");
            LoggerCommon.info(this.getClass(), "修改状态异常：" + ExceptionStackUtils.collectExceptionStackMsg(e));
            return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(logmsg)).body(null);
        }

    }

    @ApiOperation(value = "修改参数配置", httpMethod = "POST", notes = "修改参数配置")
    @PostMapping("/updateOtherConfig")
    public ResponseEntity updateOtherConfig(@RequestBody SysOtherConfig data) {

        String logmsg = null;
        try {
            ReturnMap respMap = sysOtherConfigService.updateOtherConfig(data);

            logmsg = LocaleMessage.get(respMap.getMsg());
            if (respMap.getCode() == 0) {
                LoggerCommon.info(this.getClass(), "修改系统参数配置失败:" + logmsg);
                return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(logmsg)).body(null);
            }
            LoggerCommon.info(this.getClass(), "修改系统参数配置成功");
            return ResponseEntity.ok().body(null);
        } catch (Exception e) {
            logmsg = LocaleMessage.get("message.system.save.fail");
            LoggerCommon.info(this.getClass(), "修改参数配置异常：" + ExceptionStackUtils.collectExceptionStackMsg(e));
            return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(logmsg)).body(null);
        }
    }

    @ApiOperation(value = "查询参数配置详情", httpMethod = "GET", notes = "查询参数配置")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "otherId", value = "主键", dataType = "String", paramType = "query", required = true),
    })
    @GetMapping("/getOtherConfigInfo")
    public ResponseEntity getOtherConfigInfo(@RequestParam(value = "otherId") String otherId) {
        try {
            SysOtherConfig sysOtherConfig = sysOtherConfigService.getOtherConfInfo(otherId);
            return ResponseEntity.ok().body(sysOtherConfig);
        } catch (Exception e) {
            String logmsg = LocaleMessage.get("message.query.errorMessage");
            LoggerCommon.info(this.getClass(), "查询参数配置异常：" + ExceptionStackUtils.collectExceptionStackMsg(e));
            return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(logmsg)).body(null);
        }
    }

}