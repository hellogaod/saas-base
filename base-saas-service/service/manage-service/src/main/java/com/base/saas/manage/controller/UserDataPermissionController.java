package com.base.saas.manage.controller;

import com.base.saas.common.language.LocaleMessage;
import com.base.saas.common.logger.LoggerCommon;
import com.base.saas.common.response.BaseResponse;
import com.base.saas.util.response.ExceptionStackMessage;
import com.base.saas.common.response.ResponseLogMessageHandel;
import com.base.saas.common.userinfo.UserContextUtil;
import com.base.saas.common.userinfo.UserInfo;
import com.base.saas.manage.model.User;
import com.base.saas.manage.model.UserDataPermission;
import com.base.saas.manage.service.enterprise.RoleMenuService;
import com.base.saas.manage.service.UserDataPermissionService;
import com.base.saas.manage.service.enterprise.UserService;
import com.base.saas.util.HeaderUtil;
import com.base.saas.util.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Title :
 * Description : 数据权限管理
 * Create on : 2018年05月25日
 * Copyright (C) zw.FinTec
 *
 * @author department:研发部
 *         username: wangtao
 * @version 修改历史:
 *          修改人 修改日期 修改描述
 *          -------------------------------------------<
 */
@RestController
@RequestMapping("/api/userDatePermission")
@Api(value = "/api/userDatePermission", description = "数据权限管理")
public class UserDataPermissionController {
    @Autowired
    private UserDataPermissionService userDataPermissionService;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleMenuService roleMenuService;

    private static final String ENTITY_NAME = "userDatePermission";

    /**
     * @Author: wangtao
     * @Date: 2018/05/29 13:22
     * @Params: data{"roleId":,"userId","menuId":,"orgPath":,"isDesensite":"是否脱敏()","type":,"sysCode":}
     * @Description: 添加数据权限
     * @return: ResultVO
     */
    @PostMapping("/addDataPermission")
    @ApiOperation(value = "添加数据权限", notes = "添加数据权限")
    public BaseResponse addDataPermission(@RequestBody Map data){
//        Map param = JSONObject.parseObject(data);
        String userId = String.valueOf(data.get("userId"));
        String orgPath = String.valueOf(data.get("orgPath"));
        String isDesensite = String.valueOf(data.get("isDesensite"));
        String logmsg =null;
        try {
            List<Map> list = roleMenuService.getMenuListByRoleIdAndMenuId(data);
            //1-更新数据权限，2-更新脱敏
            String type = String.valueOf(data.get("type"));
            for (int i = 0; i < list.size(); i++) {
                UserDataPermission userDataPermission = new UserDataPermission();
                if (StringUtil.isNotEmpty(isDesensite)) {
                    userDataPermission.setIsDesensite(Short.valueOf(isDesensite));
                }
                Map menuMap = list.get(i);
                String menuId = String.valueOf(menuMap.get("entMenuId"));
                userDataPermission.setUserId(userId);
                userDataPermission.setMenuId(menuId);
                if (StringUtil.isNotEmpty(orgPath)) {
                    userDataPermission.setOrgPath(orgPath);
                }
                addUserData(userDataPermission, type);
            }
            logmsg=LocaleMessage.get("message.system.save.success");
            return BaseResponse.ok().headers(HeaderUtil.createAlert(logmsg, ENTITY_NAME)).body(null);
        }catch(Exception  e){
            logmsg = LocaleMessage.get("message.system.save.fail");
            LoggerCommon.info(this.getClass(),"添加用户异常："+ExceptionStackMessage.collectExceptionStackMsg(e));
            return BaseResponse.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME,"system.server.exception",
                    logmsg)).body(ResponseLogMessageHandel.initResponseData(HttpStatus.BAD_REQUEST.value(), ExceptionEnum.EXCEPTION_CODE.code(),
                    LocaleMessage.get("system.server.exception"), ExceptionStackMessage.collectExceptionStackMsg(e),null));
        }
    }

    /**
     * @Author: wangtao
     * @Date: 2018/05/28 16:08
     * @Params: userDataPermisson
     * @Description: 保存数据权限
     * @return: Map
     */
    public Map addUserData(UserDataPermission userDataPermisson, String type)throws Exception{
        //添加之前先查询是否存在
        UserDataPermission model = userDataPermissionService.getUserDataPermisson(userDataPermisson);
        User user = userService.getUserById(userDataPermisson.getUserId());
        String orgId = user.getOrgId();
        String companyCode = user.getCompanyCode();
        UserInfo userInfo = UserContextUtil.getUserInfo();
        String userName = userInfo.getAccount();
        Map returnMap = new HashMap();
        if(model == null){//不存在则添加
            userDataPermisson.setCreateUser(userName);
            userDataPermisson.setUpdateUser(userName);
            userDataPermisson.setOrgId(orgId);
            userDataPermisson.setCompanyCode(companyCode);
            returnMap = userDataPermissionService.addUserDataPermisson(userDataPermisson);
        }else{//否则修改
            userDataPermisson.setId(model.getId());
            userDataPermisson.setUpdateUser(userName);
            returnMap = userDataPermissionService.updateUserDataPermisson(userDataPermisson, type);
        }
        return returnMap;
    }

    /**
     * @Author: wangtao
     * @Date: 2018/05/28 16:09
     * @Params: param
     * @Description: 获取数据权限
     * @return: ResultVO
     */
    @GetMapping("/getUserDataPermisson")
    @ApiOperation(value = "获取数据权限", notes = "获取数据权限")
    public BaseResponse getUserDataPermisson(@RequestBody UserDataPermission param){
        try {
            UserDataPermission userDataPermission = userDataPermissionService.getUserDataPermisson(param);
            return BaseResponse.ok().body(ResponseLogMessageHandel.initSuccessResponseData(userDataPermission));
        }catch (Exception e){
            String logmsg=LocaleMessage.get("message.query.errorMessage");
            LoggerCommon.info(this.getClass(),"获取数据权限异常："+ExceptionStackMessage.collectExceptionStackMsg(e));
            return BaseResponse.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME,"system.server.exception",
                    logmsg)).body(ResponseLogMessageHandel.initResponseData(HttpStatus.BAD_REQUEST.value(), ExceptionEnum.EXCEPTION_CODE.code(),
                    LocaleMessage.get("system.server.exception"), ExceptionStackMessage.collectExceptionStackMsg(e),null));
        }
    }
}
