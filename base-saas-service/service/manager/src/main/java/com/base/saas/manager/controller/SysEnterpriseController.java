package com.base.saas.manager.controller;

import com.base.saas.manager.model.SysEnterprise;
import com.base.saas.manager.service.*;
import com.base.saas.common.language.LocaleMessage;
import com.base.saas.common.logger.LoggerCommon;
import com.base.saas.common.response.BaseResponse;
import com.base.saas.common.response.ExceptionEnum;
import com.base.saas.common.response.ExceptionStackMessage;
import com.base.saas.common.response.ResponseLogMessageHandel;
import com.base.saas.common.userinfo.UserContextUtil;
import com.base.saas.common.userinfo.UserInfo;
import com.base.saas.manager.service.*;
import com.base.saas.util.HeaderUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/api/sysEnterprise")
@Api(value = "/api/sysEnterprise", description = "企业信息管理")
public class SysEnterpriseController {

    private static final String ENTITY_NAME = "sysEnterprise";

    @Autowired
    private SysEnterpriseService sysEnterpriseService;
    @Autowired
    private EntModuleService entModuleService;
    @Autowired
    private EntRoleMenuService entRoleMenuService;
    @Autowired
    private EntUserService entUserService;
    @Autowired
    private SysEntOtherConfigService sysEntOtherConfigService;


    /**
     * @Author: wangtao
     * @Date: 2018/06/08 15:00
     * @Params: map（企业信息和选择的系统模块信息）
     * @Description: 添加企业信息
     * @return: ResultVO
     */
    @PostMapping("/addSysEnterprise")
    @ApiOperation(value = "添加企业信息", notes = "添加企业信息")
    public BaseResponse addSysEnterprise(@RequestBody Map data) {
        UserInfo userInfo = UserContextUtil.getUserInfo();
        data.put("createUser",userInfo.getAccount());//userInfo.getAccount()
        String logmsg= null;
        try {
            //添加企业
            Map retMap = sysEnterpriseService.addSysEnterprise(data);
            boolean flag = (Boolean) retMap.get("flag");
            logmsg  = LocaleMessage.get(String.valueOf(retMap.get("msg")));
            if (flag) {
                //菜单、权限分配
                String roleId = (String) retMap.get("roleId");
                List<String> sysCodeList = (List<String>) data.get("moduleList");
                String companyCode = (String) retMap.get("companyCode");
                String orgId = (String) retMap.get("orgId");
                entRoleMenuService.addEntRoleMenu(roleId, sysCodeList, companyCode, orgId);
                LoggerCommon.info(this.getClass(),"添加企业信息成功");
                return BaseResponse.ok().headers(HeaderUtil.createAlert(logmsg, ENTITY_NAME)).body(null);
            } else {
                LoggerCommon.info(this.getClass(),"添加企业信息失败："+logmsg);
                return BaseResponse.badRequest().headers(HeaderUtil.createAlert(logmsg, ENTITY_NAME)).body(null);
            }
        }catch (Exception e){
            logmsg  =LocaleMessage.get("message.system.save.fail");
            LoggerCommon.info(this.getClass(),"添加企业信息异常："+ ExceptionStackMessage.collectExceptionStackMsg(e));
            return BaseResponse.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME,"system.server.exception",
                    logmsg)).body(ResponseLogMessageHandel.initResponseData(HttpStatus.BAD_REQUEST.value(), ExceptionEnum.EXCEPTION_CODE.code(),
                    LocaleMessage.get("system.server.exception"), ExceptionStackMessage.collectExceptionStackMsg(e),null));
        }
    }

    /**
     * @Author: wangtao
     * @Date: 2018/06/08 15:00
     * @Params: map（企业信息和选择的系统模块信息）
     * @Description: 修改企业信息
     * @return: ResultVO
     */
    @PostMapping("/updateSysEnterprise")
    @ApiOperation(value = "修改企业信息", notes = "修改企业信息")
    public BaseResponse updateSysEnterprise(@RequestBody Map data) throws Exception {
        UserInfo userInfo = UserContextUtil.getUserInfo();
        data.put("createUser",userInfo.getAccount());
        String logmsg = null;
        try {
            //修改企业
            Map retMap = sysEnterpriseService.updateSysEnterprise(data);
            boolean flag = (Boolean) retMap.get("flag");
            logmsg = LocaleMessage.get(String.valueOf(retMap.get("msg")));
            if (flag) {
                //菜单、权限分配
                String companyCode = String.valueOf(data.get("companyCode"));
                Map userMap = entUserService.getUserByCompanyCode(companyCode);
                String roleId = String.valueOf(userMap.get("roleId"));
                String orgId = String.valueOf(userMap.get("orgId"));
                List<String> sysCodeList = (List<String>) data.get("moduleList");
                entRoleMenuService.addEntRoleMenu(roleId, sysCodeList, companyCode, orgId);
                LoggerCommon.info(this.getClass(),"修改企业信息成功");
                return BaseResponse.ok().headers(HeaderUtil.createAlert(logmsg, ENTITY_NAME)).body(null);
            } else {
                LoggerCommon.info(this.getClass(),"修改企业信息失败："+logmsg);
                return BaseResponse.badRequest().headers(HeaderUtil.createAlert(logmsg, ENTITY_NAME)).body(null);
            }
        }catch (Exception e){
            logmsg  =LocaleMessage.get("message.system.update.fail");
            LoggerCommon.info(this.getClass(),"修改企业信息异常："+ExceptionStackMessage.collectExceptionStackMsg(e));
            return BaseResponse.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME,"system.server.exception",
                    logmsg)).body(ResponseLogMessageHandel.initResponseData(HttpStatus.BAD_REQUEST.value(), ExceptionEnum.EXCEPTION_CODE.code(),
                    LocaleMessage.get("system.server.exception"), ExceptionStackMessage.collectExceptionStackMsg(e),null));
        }
    }

    /**
     * @Author: wangtao
     * @Date: 2018/06/12 13:22
     * @Params: data
     * @Description: 企业列表
     * @return: ResultVO
     */
    @GetMapping("/getSysEnterpriseList")
    @ApiOperation(value = "企业列表", notes = "企业列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize", value = "分页大小", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "pageIndex", value = "当前页码", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "companyName", value = "企业名称", dataType = "String", paramType = "query", required = false),
    })
    public BaseResponse getSysEnterpriseList(@RequestParam(value = "pageSize") Integer pageSize,
                                               @RequestParam(value = "pageIndex") Integer pageIndex,
                                               @RequestParam(value = "companyName",required = false) String companyName){
        try {
            PageHelper.startPage(pageIndex, pageSize, true);
            List list = sysEnterpriseService.getSysEnterpriseList(companyName);
            PageInfo pageInfo = new PageInfo(list);
            return BaseResponse.ok().body(ResponseLogMessageHandel.initSuccessResponseData(pageInfo));
        }catch (Exception e){
            String logmsg = LocaleMessage.get("message.query.errorMessage");
            LoggerCommon.info(this.getClass(),"查询已启用的模块列表异常："+ExceptionStackMessage.collectExceptionStackMsg(e));
            return BaseResponse.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME,"system.server.exception",
                    logmsg)).body(ResponseLogMessageHandel.initResponseData(HttpStatus.BAD_REQUEST.value(), ExceptionEnum.EXCEPTION_CODE.code(),
                    LocaleMessage.get("system.server.exception"), ExceptionStackMessage.collectExceptionStackMsg(e),null));
        }
    }

    /**
     * @Author: wangtao
     * @Date: 2018/06/12 13:22
     * @Params: companyCode
     * @Description: 修改回显信息
     * @return: ResultVO
     */
    @GetMapping("/getEnterpriseByCompanyCode")
    @ApiOperation(value = "根据企业编码查询企业信息", notes = "根据企业编码查询企业信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "companyCode", value = "企业编码", dataType = "String", paramType = "query", required = true),
    })
    public BaseResponse getEnterpriseByCompanyCode(@RequestParam("companyCode")String companyCode){
        try {
            //企业信息
            SysEnterprise sysEnterprise = sysEnterpriseService.getEnterpriseByCompanyCode(companyCode);
            //企业选择的模块
            List moduleList = entModuleService.getModuleByCompanyCode(companyCode);
            Map map = new HashMap();
            map.put("sysEnterprise", sysEnterprise);
            map.put("moduleList", moduleList);
            return BaseResponse.ok().body(ResponseLogMessageHandel.initSuccessResponseData(map));
        }catch (Exception e) {
            String logmsg = LocaleMessage.get("message.query.errorMessage");
            LoggerCommon.info(this.getClass(),"根据企业编码查询企业信息异常："+ExceptionStackMessage.collectExceptionStackMsg(e));
            return BaseResponse.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME,"system.server.exception",
                    logmsg)).body(ResponseLogMessageHandel.initResponseData(HttpStatus.BAD_REQUEST.value(), ExceptionEnum.EXCEPTION_CODE.code(),
                    LocaleMessage.get("system.server.exception"), ExceptionStackMessage.collectExceptionStackMsg(e),null));
        }
    }

    /**
     * @Author: wangtao
     * @Date: 2018/06/12 13:22
     * @Params: status：状态
     * @Params: companyCode：企业code
     * @Description: 修改状态
     * @return:
     */
    @PostMapping("/updateStatus")
    @ApiOperation(value = "修改状态", notes = "修改状态")
    public BaseResponse updateStatus(@RequestBody Map data){
        String logmsg = null;
        try {
            Map map = sysEnterpriseService.updateStatus(data);
            boolean flag = (Boolean) map.get("flag");
            logmsg = LocaleMessage.get(String.valueOf(map.get("msg")));
            if (flag) {
                LoggerCommon.info(this.getClass(),"修改企业状态成功");
                return BaseResponse.ok().headers(HeaderUtil.createAlert(logmsg, ENTITY_NAME)).body(null);
            } else {
                LoggerCommon.info(this.getClass(),"修改企业状态失败:"+logmsg);
                return BaseResponse.badRequest().headers(HeaderUtil.createAlert(logmsg, ENTITY_NAME)).body(null);
            }
        }catch (Exception e){
            logmsg = LocaleMessage.get("message.system.operation.fail");
            LoggerCommon.info(this.getClass(),"修改状态异常："+ExceptionStackMessage.collectExceptionStackMsg(e));
            return BaseResponse.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME,"system.server.exception",
                    logmsg)).body(ResponseLogMessageHandel.initResponseData(HttpStatus.BAD_REQUEST.value(), ExceptionEnum.EXCEPTION_CODE.code(),
                    LocaleMessage.get("system.server.exception"), ExceptionStackMessage.collectExceptionStackMsg(e),null));
        }
    }




    /**
     * 修改企业关联三方列表
     * @param data
     * @return
     */
    @ApiOperation(value = "修改企业关联三方列表", notes = "修改企业关联三方列表")
    @PostMapping("/updateCompanyOtherConf")
    public BaseResponse updateCompanyOtherConf(@RequestBody Map data){
        String logmsg = null;
        Map map= (Map) data.get("data");
        UserInfo user = UserContextUtil.getUserInfo();
        data.put("createUser",user.getAccount());
        try {
            Map respMap = sysEntOtherConfigService.updateCompanyOtherConf(map);
            boolean flag = (boolean) respMap.get("flag");
            logmsg= String.valueOf(respMap.get("msg"));
            if(!flag){
                LoggerCommon.info(this.getClass(),"修改企业关联三方列表失败:"+logmsg);
                return BaseResponse.badRequest().headers(HeaderUtil.createAlert(logmsg,ENTITY_NAME)).body(null);
            }
            LoggerCommon.info(this.getClass(),"修改企业关联三方列表成功");
            return BaseResponse.ok().headers(HeaderUtil.createAlert(logmsg, ENTITY_NAME)).body(null);
        }catch (Exception e){
            logmsg = LocaleMessage.get("message.system.operation.fail");
            LoggerCommon.info(this.getClass(),"修改企业关联三方列表："+ExceptionStackMessage.collectExceptionStackMsg(e));
            return BaseResponse.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME,"system.server.exception",
                    logmsg)).body(ResponseLogMessageHandel.initResponseData(HttpStatus.BAD_REQUEST.value(), ExceptionEnum.EXCEPTION_CODE.code(),
                    LocaleMessage.get("system.server.exception"), ExceptionStackMessage.collectExceptionStackMsg(e),null));
        }

    }

}
