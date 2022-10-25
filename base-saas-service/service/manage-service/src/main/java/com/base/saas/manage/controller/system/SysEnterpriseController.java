package com.base.saas.manage.controller.system;

import com.base.saas.manage.model.ReturnMap;
import com.base.saas.manage.model.SysEnterprise;
import com.base.saas.common.language.LocaleMessage;
import com.base.saas.common.logger.LoggerCommon;
import com.base.saas.manage.model.system.SysOtherConfig;
import com.base.saas.util.response.ExceptionStackMessage;
import com.base.saas.manage.service.enterprise.EntOtherConfigService;
import com.base.saas.manage.service.system.SysEnterpriseService;
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
import java.util.Map;

@RestController
@RequestMapping("/api/sysEnterprise")
@Api(value = "企业信息管理")
public class SysEnterpriseController {

    @Autowired
    private SysEnterpriseService sysEnterpriseService;

    @Autowired
    private EntOtherConfigService sysEntOtherConfigService;

    @GetMapping("/getSysEnterpriseList")
    @ApiOperation(value = "企业列表", notes = "企业列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize", value = "分页大小", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "pageIndex", value = "当前页码", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "companyName", value = "企业名称", dataType = "String", paramType = "query", required = false),
    })
    public ResponseEntity getSysEnterpriseList(@RequestParam(value = "pageSize") Integer pageSize,
                                               @RequestParam(value = "pageIndex") Integer pageIndex,
                                               @RequestParam(value = "companyName", required = false) String companyName) {
        try {
            PageHelper.startPage(pageIndex, pageSize, true);
            List list = sysEnterpriseService.getSysEnterpriseList(companyName);
            PageInfo pageInfo = new PageInfo(list);
            return ResponseEntity.ok().body(pageInfo);
        } catch (Exception e) {
            String logmsg = LocaleMessage.get("message.query.errorMessage");
            LoggerCommon.info(this.getClass(), "查询已启用的模块列表异常：" + ExceptionStackMessage.collectExceptionStackMsg(e));
            return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(logmsg)).body(null);
        }
    }

    @PostMapping("/addSysEnterprise")
    @ApiOperation(value = "添加企业信息", notes = "添加企业信息")
    public ResponseEntity addSysEnterprise(@RequestBody SysEnterprise data) {

        String logmsg = null;
        try {
            //添加企业
            ReturnMap retMap = sysEnterpriseService.addSysEnterprise(data);

            logmsg = retMap.getMsg();
            if (retMap.getCode() == 1) {

                LoggerCommon.info(this.getClass(), "添加企业信息成功");
                return ResponseEntity.ok().body(null);
            } else {
                LoggerCommon.info(this.getClass(), "添加企业信息失败：" + logmsg);
                return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(logmsg)).body(null);
            }
        } catch (Exception e) {
            logmsg = LocaleMessage.get("message.system.save.fail");
            LoggerCommon.info(this.getClass(), "添加企业信息异常：" + ExceptionStackMessage.collectExceptionStackMsg(e));
            return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(logmsg)).body(null);
        }
    }

    @PostMapping("/updateStatus")
    @ApiOperation(value = "修改状态", notes = "修改状态")
    public ResponseEntity updateStatus(@RequestBody SysEnterprise data) {
        String logmsg = null;
        try {
            ReturnMap map = sysEnterpriseService.updateStatus(data);
            logmsg = LocaleMessage.get(map.getMsg());
            if (map.getCode() == 1) {
                LoggerCommon.info(this.getClass(), "修改企业状态成功");
                return ResponseEntity.ok().body(null);
            } else {
                LoggerCommon.info(this.getClass(), "修改企业状态失败:" + logmsg);
                return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(logmsg)).body(null);
            }
        } catch (Exception e) {
            logmsg = LocaleMessage.get("message.system.operation.fail");
            LoggerCommon.info(this.getClass(), "修改状态异常：" + ExceptionStackMessage.collectExceptionStackMsg(e));
            return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(logmsg)).body(null);
        }
    }


    @GetMapping("/getEnterpriseByCompanyCode")
    @ApiOperation(value = "根据企业编码查询企业信息", notes = "根据企业编码查询企业信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "companyCode", value = "企业编码", dataType = "String", paramType = "query", required = true),
    })
    public ResponseEntity getEnterpriseByCompanyCode(@RequestParam("companyCode") String companyCode) {
        try {
            //企业信息
            SysEnterprise sysEnterprise = sysEnterpriseService.getEnterpriseByCompanyCode(companyCode);

            return ResponseEntity.ok().body(sysEnterprise);
        } catch (Exception e) {
            String logmsg = LocaleMessage.get("message.query.errorMessage");
            LoggerCommon.info(this.getClass(), "根据企业编码查询企业信息异常：" + ExceptionStackMessage.collectExceptionStackMsg(e));
            return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(logmsg)).body(null);
        }
    }


    @PostMapping("/updateSysEnterprise")
    @ApiOperation(value = "修改企业信息", notes = "修改企业信息")
    public ResponseEntity updateSysEnterprise(@RequestBody SysEnterprise data) throws Exception {

        String logmsg = null;
        try {
            //修改企业
            ReturnMap retMap = sysEnterpriseService.updateSysEnterprise(data);
            logmsg = LocaleMessage.get(retMap.getMsg());
            if (retMap.getCode() == 1) {
                LoggerCommon.info(this.getClass(), "修改企业信息成功");
                return ResponseEntity.ok().body(null);
            } else {
                LoggerCommon.info(this.getClass(), "修改企业信息失败：" + logmsg);
                return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(logmsg)).body(null);
            }
        } catch (Exception e) {
            logmsg = LocaleMessage.get("message.system.update.fail");
            LoggerCommon.info(this.getClass(), "修改企业信息异常：" + ExceptionStackMessage.collectExceptionStackMsg(e));
            return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(logmsg)).body(null);
        }
    }

    @ApiOperation(value = "查询企业三方配置关联列表", httpMethod = "GET", notes = "查询企业三方配置关联列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "companyCode", value = "企业编码", dataType = "String", paramType = "query", required = true),
    })
    @GetMapping("/getCompanyConfigList")
    public ResponseEntity getCompanyConfigList(@RequestParam(value = "companyCode") String companyCode) {
        //查询所有已启用的配置
        List<SysOtherConfig> allOthers = null;
        try {
            allOthers = sysEntOtherConfigService.getConfigList(companyCode);
        } catch (Exception e) {
            String logmsg = LocaleMessage.get("message.query.errorMessage");
            LoggerCommon.info(this.getClass(), "查询企业三方关联列表异常：" + ExceptionStackMessage.collectExceptionStackMsg(e));
            return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(logmsg)).body(null);
        }

        return ResponseEntity.ok().body(allOthers);
    }


    @ApiOperation(value = "修改企业关联三方配置列表", notes = "修改企业关联三方配置列表")
    @PostMapping("/updateCompanyOtherConf")
    public ResponseEntity updateCompanyOtherConf(@RequestBody Map data) {
        String logmsg = null;
        try {
            ReturnMap respMap = sysEntOtherConfigService.updateCompanyOtherConf(data);

            logmsg = String.valueOf(respMap.getMap());
            if (respMap.getCode() == 0) {
                LoggerCommon.info(this.getClass(), "修改企业关联三方列表失败:" + logmsg);
                return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(logmsg)).body(null);
            }
            LoggerCommon.info(this.getClass(), "修改企业关联三方列表成功");
            return ResponseEntity.ok().body(null);
        } catch (Exception e) {
            logmsg = LocaleMessage.get("message.system.operation.fail");
            LoggerCommon.info(this.getClass(), "修改企业关联三方列表：" + ExceptionStackMessage.collectExceptionStackMsg(e));
            return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(logmsg)).body(null);
        }

    }

}
