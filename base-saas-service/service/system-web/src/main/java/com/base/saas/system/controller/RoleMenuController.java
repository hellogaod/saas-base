package com.base.saas.system.controller;


import com.base.saas.common.language.LocaleMessage;
import com.base.saas.common.logger.LoggerCommon;
import com.base.saas.common.response.BaseResponse;
import com.base.saas.common.response.ExceptionEnum;
import com.base.saas.common.response.ExceptionStackMessage;
import com.base.saas.common.response.ResponseLogMessageHandel;
import com.base.saas.common.userinfo.UserContextUtil;
import com.base.saas.system.service.EntMenuService;
import com.base.saas.system.service.RoleMenuService;
import com.base.saas.util.HeaderUtil;
import com.base.saas.util.StringUtil;
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
 * Description : @角色权限管理@
 * Create on : 2018年05月24日
 * Copyright (C) zw.FinTec
 *
 * @author department:研发部
 *         username:chengrui
 * @version 修改历史:
 *          修改人 修改日期 修改描述
 *          -------------------------------------------<
 */
@RestController
@RequestMapping("/api/roleMenu")
@Api(value = "/api/roleMenu", description = "角色权限管理")
public class RoleMenuController {

    @Autowired
    private RoleMenuService roleMenuService;

    @Autowired
    private EntMenuService entMenuService;

    private static final String ENTITY_NAME = "roleMenu";


    /**
    * @Description: 根据角色编号查询已有的菜单编号
    * @Param:  * @param roleId
    * @return: com.zw.fin.web.base.ResultVO
    * @Date: 2018/5/24
    */
    @GetMapping("/getMenuByRoleId")
    @ApiOperation(value = "根据角色编号查询已有的菜单编号", notes = "根据角色编号查询已有的菜单编号")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", value = "角色id", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "sysCode", value = "模块sysCode", dataType = "String", paramType = "query", required = true),
    })
    public BaseResponse getMenuByRoleId(@RequestParam String roleId,@RequestParam String sysCode) {
        String logmsg=LocaleMessage.get("message.system.request.param.exception");
        //判断角色编号是否为空
        if (StringUtil.isEmpty(roleId)) {
            return BaseResponse.ok().headers(HeaderUtil.createAlert(logmsg,ENTITY_NAME)).body(null);
        }
        try {
            List<Map> menuList = roleMenuService.getMenuByRoleId(roleId);
            //查询当前模块下所有的菜单
            List<Map> list = entMenuService.getAllMenuTree(sysCode);
            for (Map menu:list) {
                int checked=0;
                for (Map amenu:menuList) {
                    if(menu.get("id").toString().equals(amenu.get("menuId").toString())){
                        checked++;
                        break;
                    }
                }
                menu.put("checked",checked);
            }
            return BaseResponse.ok().body(ResponseLogMessageHandel.initSuccessResponseData(list));
        }catch(Exception e){
            logmsg = LocaleMessage.get("message.query.errorMessage");
            LoggerCommon.info(this.getClass(),"根据角色编号查询已有的菜单编号异常："+ExceptionStackMessage.collectExceptionStackMsg(e));
            return BaseResponse.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME,"system.server.exception",
                    logmsg)).body(ResponseLogMessageHandel.initResponseData(HttpStatus.BAD_REQUEST.value(), ExceptionEnum.EXCEPTION_CODE.code(),
                    LocaleMessage.get("system.server.exception"), ExceptionStackMessage.collectExceptionStackMsg(e),null));
        }

    }

    /**
    * @Description: 添加角色权限
    * @Param:  * @param data {roleId:,menuIds[1,2,3]}
    * @return: com.zw.fin.web.base.ResultVO
    * @Date: 2018/5/24
    */
    @PostMapping("/addRoleMenu")
    @ApiOperation(value = "添加角色权限", notes = "添加角色权限")
    public BaseResponse addRoleMenu(@RequestBody Map data) {
        List<String>menuIds= (List<String>) data.get("menuIds");
        Map params = new HashMap();
        //角色编号
        params.put("roleId", data.get("roleId"));
        //菜单编号集合
        params.put("menuIds", menuIds);
        //半选中菜单
        params.put("halfMenuIds", data.get("halfMenuIds"));
        //创建人
        params.put("createUser", UserContextUtil.getUserInfo().getAccount());
        String logmsg=null;
        try {
            Map resultMap = roleMenuService.addRoleMenu(params);
            boolean flag = (boolean) resultMap.get("flag");
            logmsg=LocaleMessage.get(resultMap.get("msg").toString());
            if (flag) {
                return BaseResponse.ok().headers(HeaderUtil.createAlert(logmsg, ENTITY_NAME)).body(null);
            } else {
                return BaseResponse.badRequest().headers(HeaderUtil.createAlert(logmsg, ENTITY_NAME)).body(null);
            }
        }catch (Exception e){
            logmsg=LocaleMessage.get("message.system.save.fail");
            LoggerCommon.info(this.getClass(),"添加角色权限异常："+ExceptionStackMessage.collectExceptionStackMsg(e));
            return BaseResponse.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME,"system.server.exception",
                    logmsg)).body(ResponseLogMessageHandel.initResponseData(HttpStatus.BAD_REQUEST.value(), ExceptionEnum.EXCEPTION_CODE.code(),
                    LocaleMessage.get("system.server.exception"), ExceptionStackMessage.collectExceptionStackMsg(e),null));
        }
    }

    /**
     * @Author: wangtao
     * @Date: 2018/05/25 11:03
     * @Params: roleId 角色id
     * @Description: 获取菜单
     * @return: ResultVO
     */
    @GetMapping("/getMenuListByRoleId")
    @ApiOperation(value = "获取菜单 -用户管理（角色分配）", notes = "获取菜单-用户管理（角色分配）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", value = "角色id", dataType = "String", paramType = "query", required = false),
            @ApiImplicitParam(name = "sysCode", value = "sysCode", dataType = "String", paramType = "query", required = true)
    })
    public BaseResponse getMenuListByRoleId(@RequestParam(value = "roleId",required = false) String roleId,@RequestParam String sysCode){
        try {
            List list = roleMenuService.getMenuListByRoleId(roleId, sysCode);
            return BaseResponse.ok().body(ResponseLogMessageHandel.initSuccessResponseData(list));
        }catch (Exception e){
            String logmsg = LocaleMessage.get("message.query.errorMessage");
            LoggerCommon.info(this.getClass(),"获取菜单 -用户管理（角色分配）异常："+ExceptionStackMessage.collectExceptionStackMsg(e));
            return BaseResponse.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME,"system.server.exception",
                    logmsg)).body(ResponseLogMessageHandel.initResponseData(HttpStatus.BAD_REQUEST.value(), ExceptionEnum.EXCEPTION_CODE.code(),
                    LocaleMessage.get("system.server.exception"), ExceptionStackMessage.collectExceptionStackMsg(e),null));
        }
    }

}
