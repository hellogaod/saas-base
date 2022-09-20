package com.base.saas.manager.controller;

import com.base.saas.manager.model.SysEntOtherConfig;
import com.base.saas.manager.model.SysOtherConfig;
import com.base.saas.manager.service.SysEntOtherConfigService;
import com.base.saas.manager.service.SysOtherConfigService;
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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Title :
 * Description : @系统参数管理@
 * Create on : 2018年07月18日
 * Copyright (C) zw.FinTec
 *
 * @author department:研发部
 * username:hanxiaoxue
 * @version 修改历史:
 * 修改人 修改日期 修改描述
 * -------------------------------------------<
 */

@RestController
@RequestMapping("/api/sysOther")
@Api(value = "/api/sysOther", description = "系统参数配置")
public class SysOtherController {

    private static final String ENTITY_NAME = "sysOther";

    @Autowired
    private SysOtherConfigService sysOtherConfigService;

    @Autowired
    private SysEntOtherConfigService sysEntOtherConfigService;



    /**
     * 查询数据列表
     * @param pageSize
     * @param pageIndex
     * @param otherName
     * @return
     */
    @ApiOperation(value = "查询配置列表", httpMethod = "GET", notes = "查询配置列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize", value = "分页大小", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "pageIndex", value = "当前页码", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "otherName", value = "配置名称", dataType = "String", paramType = "query", required = false),
            @ApiImplicitParam(name = "status", value = "状态", dataType = "short", paramType = "query", required = false),
            @ApiImplicitParam(name = "type", value = "配置类型", dataType = "short", paramType = "query", required = false),
    })
    @GetMapping("/getOtherList")
    public BaseResponse getOtherList(@RequestParam(value = "pageSize") Integer pageSize,
                                     @RequestParam(value = "pageIndex") Integer pageIndex,
                                     @RequestParam(value = "otherName",required = false) String otherName,
                                     @RequestParam(value = "status",required = false) Short status,
                                     @RequestParam(value = "type",required = false) Short type){
        PageHelper.startPage(pageIndex, pageSize, true);
        Map map = new HashMap();
        //配置名称
        map.put("otherName",otherName);
        //状态
        map.put("status",status);
        //类型
        map.put("type",type);
        try {
            List<SysOtherConfig> list = sysOtherConfigService.getOtherConfigList(map);
            PageInfo pageInfo = new PageInfo(list);
            return BaseResponse.ok().body(ResponseLogMessageHandel.initSuccessResponseData(pageInfo));
        }catch (Exception e){
            String logmsg = LocaleMessage.get("message.query.errorMessage");
            LoggerCommon.info(this.getClass(),"查询配置列表异常："+ ExceptionStackMessage.collectExceptionStackMsg(e));
            return BaseResponse.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME,"system.server.exception",
                    logmsg)).body(ResponseLogMessageHandel.initResponseData(HttpStatus.BAD_REQUEST.value(), ExceptionEnum.EXCEPTION_CODE.code(),
                    LocaleMessage.get("system.server.exception"), ExceptionStackMessage.collectExceptionStackMsg(e),null));
        }
    }



    /**
     * 修改三方配置
     * @param sysOtherConfig
     * @return
     */
//    @ApiOperation(value = "修改配置", httpMethod = "POST", notes = "修改配置")
//    @PostMapping("/updateOther")
//    public BaseResponse updateOther(@RequestBody SysOtherConfig sysOtherConfig){
//        sysOtherConfig.setUpdateUser(UserContextUtil.getUserInfo().getAccount());
//        Map map = sysOtherConfigService.update(sysOtherConfig);
//        boolean flag = (boolean) map.get("flag");
//        if(!flag){
//            return BaseResponse.badRequest().headers(HeaderUtil.createAlert(map.get("msg").toString(),ENTITY_NAME)).body(null);
//        }
//        return BaseResponse.ok().headers(HeaderUtil.createAlert(map.get("msg").toString(),ENTITY_NAME)).body(null);
//    }
    /**
     * 修改状态
     * @param data {id:,status:}
     * @return
     */
    @ApiOperation(value = "修改状态", httpMethod = "POST", notes = "修改状态")
    @PostMapping("/updateStatus")
    public BaseResponse updateStatus(@RequestBody Map data){
        //获取登录人信息
        UserInfo userInfo = UserContextUtil.getUserInfo();
        SysOtherConfig sysOtherConfig  =new SysOtherConfig();
        //修改人
        sysOtherConfig.setUpdateUser(userInfo.getAccount());
        //主键
        sysOtherConfig.setId(data.get("id").toString());
        sysOtherConfig.setStatus(Short.parseShort(data.get("status").toString()));
        String logmsg=null;
        try {
            //修改
            Map map =sysOtherConfigService.update(sysOtherConfig);
            logmsg=LocaleMessage.get(map.get("msg").toString());
            boolean flag = (boolean) map.get("flag");
            if(!flag){
                LoggerCommon.info(this.getClass(),"修改状态失败："+logmsg);
                return BaseResponse.badRequest().headers(HeaderUtil.createAlert(logmsg,ENTITY_NAME)).body(null);
            }
            LoggerCommon.info(this.getClass(),"修改状态成功");
            return BaseResponse.ok().headers(HeaderUtil.createAlert(logmsg,ENTITY_NAME)).body(null);
        }catch (Exception e){
            logmsg = LocaleMessage.get("message.system.operation.fail");
            LoggerCommon.info(this.getClass(),"修改状态异常："+ExceptionStackMessage.collectExceptionStackMsg(e));
            return BaseResponse.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME,"system.server.exception",
                    logmsg)).body(ResponseLogMessageHandel.initResponseData(HttpStatus.BAD_REQUEST.value(), ExceptionEnum.EXCEPTION_CODE.code(),
                    LocaleMessage.get("system.server.exception"), ExceptionStackMessage.collectExceptionStackMsg(e),null));
        }

    }

//    @RequestMapping("/toEditPage")
//    public String toEditPage(String id){
//        UserContextUtil.getHttpServletRequest().setAttribute("id",id);
//        return "OtherConfigEdit";
//    }


    @ApiOperation(value = "保存参数配置", httpMethod = "POST", notes = "保存参数配置")
    @PostMapping("/saveOtherConfig")
    public BaseResponse saveOtherConfig(@RequestBody Map data){
        //解析请求参数
        //获取用户信息
        UserInfo userInfo = UserContextUtil.getUserInfo();
        data.put("createUser",userInfo.getAccount());
        String logmsg = null;
        try {
            Map respMap = sysOtherConfigService.saveOtherConfig(data);
            boolean flag = (boolean) respMap.get("flag");
            logmsg=LocaleMessage.get(respMap.get("msg").toString());
            if (!flag) {
                LoggerCommon.info(this.getClass(),"保存系统参数配置失败:"+logmsg);
                return BaseResponse.badRequest().headers(HeaderUtil.createAlert(logmsg, ENTITY_NAME)).body(null);
            }
            LoggerCommon.info(this.getClass(),"保存系统参数配置成功");
            return BaseResponse.ok().headers(HeaderUtil.createAlert(logmsg, ENTITY_NAME)).body(null);
        }catch (Exception e){
            logmsg = LocaleMessage.get("message.system.save.fail");
            LoggerCommon.info(this.getClass(),"保存参数配置异常："+ExceptionStackMessage.collectExceptionStackMsg(e));
            return BaseResponse.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME,"system.server.exception",
                    logmsg)).body(ResponseLogMessageHandel.initResponseData(HttpStatus.BAD_REQUEST.value(), ExceptionEnum.EXCEPTION_CODE.code(),
                    LocaleMessage.get("system.server.exception"), ExceptionStackMessage.collectExceptionStackMsg(e),null));
        }
    }



    @ApiOperation(value = "修改参数配置", httpMethod = "POST", notes = "修改参数配置")
    @PostMapping("/updateOtherConfig")
    public BaseResponse updateOtherConfig(@RequestBody Map data){
        //解析参数
        //用户信息
        UserInfo userInfo = UserContextUtil.getUserInfo();
        data.put("updateUser",userInfo.getAccount());
        String logmsg =null;
        try {
            Map respMap = sysOtherConfigService.updateOtherConfig(data);
            boolean flag = (boolean) respMap.get("flag");
            logmsg=LocaleMessage.get(respMap.get("msg").toString());
            if (!flag) {
                LoggerCommon.info(this.getClass(),"修改系统参数配置失败:"+logmsg);
                return BaseResponse.badRequest().headers(HeaderUtil.createAlert(logmsg, ENTITY_NAME)).body(null);
            }
            LoggerCommon.info(this.getClass(),"修改系统参数配置成功");
            return BaseResponse.ok().headers(HeaderUtil.createAlert(logmsg, ENTITY_NAME)).body(null);
        }catch (Exception e){
            logmsg = LocaleMessage.get("message.system.save.fail");
            LoggerCommon.info(this.getClass(),"修改参数配置异常："+ExceptionStackMessage.collectExceptionStackMsg(e));
            return BaseResponse.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME,"system.server.exception",
                    logmsg)).body(ResponseLogMessageHandel.initResponseData(HttpStatus.BAD_REQUEST.value(), ExceptionEnum.EXCEPTION_CODE.code(),
                    LocaleMessage.get("system.server.exception"), ExceptionStackMessage.collectExceptionStackMsg(e),null));
        }
    }


    @ApiOperation(value = "查询参数配置", httpMethod = "GET", notes = "查询参数配置")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键", dataType = "String", paramType = "query", required =true),
    })
    @GetMapping("/getOtherConfigInfo")
    public BaseResponse getOtherConfigInfo(@RequestParam(value = "id") String id){
        try {
            Map map = sysOtherConfigService.getOtherConfInfo(id);
            return BaseResponse.ok().body(ResponseLogMessageHandel.initSuccessResponseData(map));
        }catch (Exception e){
            String logmsg = LocaleMessage.get("message.query.errorMessage");
            LoggerCommon.info(this.getClass(),"查询参数配置异常："+ExceptionStackMessage.collectExceptionStackMsg(e));
            return BaseResponse.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME,"system.server.exception",
                    logmsg)).body(ResponseLogMessageHandel.initResponseData(HttpStatus.BAD_REQUEST.value(), ExceptionEnum.EXCEPTION_CODE.code(),
                    LocaleMessage.get("system.server.exception"), ExceptionStackMessage.collectExceptionStackMsg(e),null));
        }
    }


    /**
     * 查询三方列表
     * @return
     */
//    @ApiOperation(value = "查询启用的三方列表", httpMethod = "GET", notes = "查询启用的三方列表")
//    @GetMapping("/getOtherConfigList")
//    public BaseResponse getOtherConfigList(){
//        List<SysOtherConfig> otherConfigs = sysOtherConfigService.getOtherConfigList(new HashMap());
//        return BaseResponse.ok().header("find success",ENTITY_NAME).body(otherConfigs);
//    }


    /**
     * 查询企业三方列表
     * @param companyCode
     * @return
     */
    @ApiOperation(value = "查询企业三方关联列表", httpMethod = "GET", notes = "查询企业三方关联列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "companyCode", value = "企业编码", dataType = "String", paramType = "query", required =true),
    })
    @GetMapping("/getCompanyConfigList")
    public BaseResponse getCompanyConfigList(@RequestParam(value = "companyCode") String companyCode){
        //查询所有已启用的配置
        List<SysOtherConfig> allOthers = null;
        try {
            allOthers = sysOtherConfigService.getOtherConfigList(new HashMap());
        }catch (Exception e){
            String logmsg = LocaleMessage.get("message.query.errorMessage");
            LoggerCommon.info(this.getClass(),"查询企业三方关联列表异常："+ExceptionStackMessage.collectExceptionStackMsg(e));
            return BaseResponse.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME,"system.server.exception",
                    logmsg)).body(ResponseLogMessageHandel.initResponseData(HttpStatus.BAD_REQUEST.value(), ExceptionEnum.EXCEPTION_CODE.code(),
                    LocaleMessage.get("system.server.exception"), ExceptionStackMessage.collectExceptionStackMsg(e),null));
        }
        //查询企业关联的
        List<SysEntOtherConfig> otherConfigs = sysEntOtherConfigService.getConfigList(companyCode);
        List<Map> returnList= new ArrayList<>();
        for(SysOtherConfig config:allOthers){
            Map confMap = new HashMap();
            confMap.put("id",config.getId());
            confMap.put("otherName",config.getOtherName());
            confMap.put("type",config.getType());
            short checked=0;
            for (SysEntOtherConfig cc:otherConfigs) {
                if(cc.getOtherId().equals(config.getId())){
                    checked++;
                    break;
                }
            }
            confMap.put("checked",checked);
            returnList.add(confMap);
        }

        return BaseResponse.ok().body(ResponseLogMessageHandel.initSuccessResponseData(returnList));
    }



}