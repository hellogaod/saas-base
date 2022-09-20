package com.base.saas.system.controller;

import com.base.saas.common.language.LocaleMessage;
import com.base.saas.common.logger.LoggerCommon;
import com.base.saas.common.response.BaseResponse;
import com.base.saas.common.response.ExceptionEnum;
import com.base.saas.common.response.ExceptionStackMessage;
import com.base.saas.common.response.ResponseLogMessageHandel;
import com.base.saas.common.userinfo.UserContextUtil;
import com.base.saas.common.userinfo.UserInfo;
import com.base.saas.system.model.Role;
import com.base.saas.system.service.RoleService;
import com.base.saas.system.service.UserService;
import com.base.saas.util.HeaderUtil;
import com.base.saas.util.StringUtil;
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
 * Description : 角色管理
 * Create on : 2018年05月23日
 * Copyright (C) zw.FinTec
 *
 * @author department:研发部
 *         username: wangtao
 * @version 修改历史:
 *          修改人 修改日期 修改描述
 *          -------------------------------------------<
 */

@RestController
@RequestMapping("/api/role")
@Api(value = "/role", description = "角色管理")
public class RoleController {
    private static final String ENTITY_NAME = "role";
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;

//    @RequestMapping("/toRolePage")
//    public String toRolePage(){
//        return "SystemManage/RoleManage";
//    }

    /**
     * @Author: wangtao
     * @Date: 2018/05/23 16:14
     * @Params: map
     *             分页及查询参数
     * @Description: 获取角色列表
     * @return:
     */
    @GetMapping("/getRoleList")
    @ApiOperation(value = "获取角色列表", notes = "获取角色列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize", value = "分页大小", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "pageIndex", value = "当前页码", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "roleName", value = "角色名称", dataType = "String", paramType = "query", required = false),
    })
    public BaseResponse getRoleList(@RequestParam("pageSize") Integer pageSize,
                                      @RequestParam("pageIndex")Integer pageIndex,
                                      @RequestParam(value = "roleName",required = false) String roleName){
        Map map = new HashMap();
        map.put("roleName",roleName);
        PageHelper.startPage(pageIndex, pageSize, true);
        map.put("companyCode", UserContextUtil.getUserInfo().getCompanyCode());
        try {
            List<Role> list = roleService.getRoleList(map);
            PageInfo pageInfo = new PageInfo(list);
            return BaseResponse.ok().body(ResponseLogMessageHandel.initSuccessResponseData(pageInfo));
        }catch (Exception e){
            String logmsg = LocaleMessage.get("message.query.errorMessage");
            LoggerCommon.info(this.getClass(),"获取角色列表异常："+ExceptionStackMessage.collectExceptionStackMsg(e));
            return BaseResponse.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME,"system.server.exception",
                    logmsg)).body(ResponseLogMessageHandel.initResponseData(HttpStatus.BAD_REQUEST.value(), ExceptionEnum.EXCEPTION_CODE.code(),
                    LocaleMessage.get("system.server.exception"), ExceptionStackMessage.collectExceptionStackMsg(e),null));
        }
    }

    /**
     * @Author: wangtao
     * @Date: 2018/05/21 16:44
     * @Params: role
     *            角色实体
     * @Description: 添加角色
     * @return: ResultVO
     */
    @PostMapping("/addRole")
    @ApiOperation(value = "添加角色", notes = "添加角色")
    public BaseResponse addRole(@RequestBody Role role){
        //获取登录用户信息
        UserInfo userInfo = UserContextUtil.getUserInfo();
        role.setCreateUser(userInfo.getAccount());
        role.setUpdateUser(userInfo.getAccount());
        role.setCompanyCode(userInfo.getCompanyCode());
        role.setOrgId(userInfo.getOrgId());
        String logmsg = null;
        try {
            Map map = roleService.addRole(role);
            boolean flag = (boolean) map.get("flag");
            logmsg=LocaleMessage.get(map.get("msg").toString());
            if (flag) {
                return BaseResponse.ok().headers(HeaderUtil.createAlert(logmsg, ENTITY_NAME)).body(null);
            } else {
                return BaseResponse.badRequest().headers(HeaderUtil.createAlert(logmsg, ENTITY_NAME)).body(null);
            }
        }catch (Exception e){
            logmsg=LocaleMessage.get("message.system.save.fail");
            LoggerCommon.info(this.getClass(),"添加角色异常："+ExceptionStackMessage.collectExceptionStackMsg(e));
            return BaseResponse.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME,"system.server.exception",
                    logmsg)).body(ResponseLogMessageHandel.initResponseData(HttpStatus.BAD_REQUEST.value(), ExceptionEnum.EXCEPTION_CODE.code(),
                    LocaleMessage.get("system.server.exception"), ExceptionStackMessage.collectExceptionStackMsg(e),null));
        }
    }

    /**
     * @Author: wangtao
     * @Date: 2018/05/21 16:57
     * @Params: userId
     *              用户id
     * @Description: 查询单个用户
     * @return:
     */
    @GetMapping("/getRoleById")
    @ApiOperation(value = "查询单个角色", notes = "查询单个角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", value = "角色id", dataType = "String", paramType = "query", required = true),
    })
    public BaseResponse getRoleById(@RequestParam String roleId){
        try {
            Role role = roleService.getRoleById(roleId);
            return BaseResponse.ok().body(ResponseLogMessageHandel.initSuccessResponseData(role));
        }catch (Exception e){
            String logmsg = LocaleMessage.get("message.query.errorMessage");
            LoggerCommon.info(this.getClass(),"查询单个角色异常："+ExceptionStackMessage.collectExceptionStackMsg(e));
            return BaseResponse.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME,"system.server.exception",
                    logmsg)).body(ResponseLogMessageHandel.initResponseData(HttpStatus.BAD_REQUEST.value(), ExceptionEnum.EXCEPTION_CODE.code(),
                    LocaleMessage.get("system.server.exception"), ExceptionStackMessage.collectExceptionStackMsg(e),null));
        }
    }

    /**
     * @Author: wangtao
     * @Date: 2018/05/21 16:56
     * @Params: role
     *           角色实体
     * @Description: 修改角色信息
     * @return: ResultVO
     */
    @PostMapping("/updateRole")
    @ApiOperation(value = "修改角色信息", notes = "修改角色信息")
    public BaseResponse updateUser(@RequestBody Role role){
        //获取登录用户信息
        UserInfo userInfo = UserContextUtil.getUserInfo();
        role.setUpdateUser(userInfo.getAccount());
        role.setCompanyCode(userInfo.getCompanyCode());
        String logmsg = null;
        try {
            Map map = roleService.updateRole(role);
            boolean flag = (boolean) map.get("flag");
            logmsg=LocaleMessage.get(map.get("msg").toString());
            if (flag) {
                return BaseResponse.ok().headers(HeaderUtil.createAlert(logmsg, ENTITY_NAME)).body(null);
            } else {
                return BaseResponse.badRequest().headers(HeaderUtil.createAlert(logmsg, ENTITY_NAME)).body(null);
            }
        }catch (Exception e){
            logmsg = LocaleMessage.get("message.system.update.fail");
            LoggerCommon.info(this.getClass(),"修改角色信息异常："+ExceptionStackMessage.collectExceptionStackMsg(e));
            return BaseResponse.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME,"system.server.exception",
                    logmsg)).body(ResponseLogMessageHandel.initResponseData(HttpStatus.BAD_REQUEST.value(), ExceptionEnum.EXCEPTION_CODE.code(),
                    LocaleMessage.get("system.server.exception"), ExceptionStackMessage.collectExceptionStackMsg(e),null));
        }
    }

    /**
     * @Author: wangtao
     * @Date: 2018/05/21 16:57
     * @Params:  data{roleId:,status:}
     * @Description: 启用，停用
     * @return:
     */
    @PostMapping("/updateStatus")
    @ApiOperation(value = "角色启用停用", notes = "角色启用停用")
    public BaseResponse updateStatus(@RequestBody Map data){
        String logmsg = LocaleMessage.get("message.system.request.param.exception");
        if(null==data.get("status")||StringUtil.isEmpty(data.get("status").toString())){
            return BaseResponse.badRequest().headers(HeaderUtil.createAlert(logmsg, ENTITY_NAME)).body(null);
        }
        if(null==data.get("roleId")||StringUtil.isEmpty(data.get("roleId").toString())){
            return BaseResponse.badRequest().headers(HeaderUtil.createAlert(logmsg, ENTITY_NAME)).body(null);
        }
        Role role = new Role();
        //获取登录用户信息
        UserInfo userInfo = UserContextUtil.getUserInfo();
        role.setUpdateUser(userInfo.getAccount());
        role.setStatus(Short.parseShort(data.get("status").toString()));
        role.setRoleId(data.get("roleId").toString());
        try {
            Map map = roleService.updateState(role);
            boolean flag = (boolean) map.get("flag");
            String msg = String.valueOf(map.get("msg"));
            logmsg=LocaleMessage.get(msg);
            if (flag) {
                return BaseResponse.ok().headers(HeaderUtil.createAlert(logmsg, ENTITY_NAME)).body(null);
            } else {
                return BaseResponse.badRequest().headers(HeaderUtil.createAlert(logmsg, ENTITY_NAME)).body(null);
            }
        }catch (Exception e){
            logmsg = LocaleMessage.get("message.system.update.fail");
            LoggerCommon.info(this.getClass(),"修改角色状态异常："+ExceptionStackMessage.collectExceptionStackMsg(e));
            return BaseResponse.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME,"system.server.exception",
                    logmsg)).body(ResponseLogMessageHandel.initResponseData(HttpStatus.BAD_REQUEST.value(), ExceptionEnum.EXCEPTION_CODE.code(),
                    LocaleMessage.get("system.server.exception"), ExceptionStackMessage.collectExceptionStackMsg(e),null));
        }
    }

    /**
     * @Author: wangtao
     * @Date: 2018/05/24 09:30
     * @Params: userId：用户id
     * @Description: 角色分配，获取角色
     * @return:
     */
    @GetMapping("/getRole")
    @ApiOperation(value = "角色分配，获取角色", notes = "角色分配，获取角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", dataType = "String", paramType = "query", required = true),
    })
    public BaseResponse getRole(@RequestParam String userId){
        Map map = new HashMap();
        try {
            //获取所有角色
            List<Map> roleAllList = roleService.getRole(UserContextUtil.getUserInfo().getCompanyCode());
            //获取该用户原有的角色
            String roleId = userService.getRoleIdByUserId(userId);
            map.put("roleAllList", roleAllList);
            map.put("userRoleId", roleId);
            return BaseResponse.ok().body(ResponseLogMessageHandel.initSuccessResponseData(map));
        }catch (Exception e){
            String logmsg = LocaleMessage.get("message.query.errorMessage");
            LoggerCommon.info(this.getClass(),"角色分配，获取角色异常："+ExceptionStackMessage.collectExceptionStackMsg(e));
            return BaseResponse.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME,"system.server.exception",
                    logmsg)).body(ResponseLogMessageHandel.initResponseData(HttpStatus.BAD_REQUEST.value(), ExceptionEnum.EXCEPTION_CODE.code(),
                    LocaleMessage.get("system.server.exception"), ExceptionStackMessage.collectExceptionStackMsg(e),null));
        }
    }
}
