package com.base.saas.manage.controller.enterprise;

import com.base.saas.common.constant.ServerConstans;
import com.base.saas.common.language.LocaleMessage;
import com.base.saas.common.logger.LoggerCommon;
import com.base.saas.common.response.BaseResponse;
import com.base.saas.util.response.ExceptionStackMessage;
import com.base.saas.common.response.ResponseLogMessageHandel;
import com.base.saas.common.userinfo.UserContextUtil;
import com.base.saas.common.userinfo.UserInfo;
import com.base.saas.manage.model.User;
import com.base.saas.manage.service.enterprise.UserService;
import com.base.saas.util.HeaderUtil;
import com.base.saas.util.StringUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Title :
 * Description : 用户管理
 * Create on : 2018年05月21日
 * Copyright (C) zw.FinTec
 *
 * @author department:研发部
 *         username: wangtao
 * @version 修改历史:
 *          修改人 修改日期 修改描述
 *          -------------------------------------------<
 */

@RestController
@RequestMapping("/api/user")
@Api(value = "/api/user", description = "用户管理")
public class UserController {
    @Autowired
    private UserService userService;

    private static final String ENTITY_NAME = "user";

    @Value("${spring.application.name}")
    private String serverName;

//    @RequestMapping("/toUserPage")
//    public String toUserPage(){
//        return "SystemManage/UserManage";
//    }

    /**
     * @Author: wangtao
     * @Date: 2018/05/21 16:44
     * @Params: user
     *            用户实体
     * @Description: 添加用户
     * @return: ResultVO
     */
    @PostMapping("/addUser")
    @ApiOperation(value = "添加用户", notes = "添加用户")
    public BaseResponse addUser(@RequestBody User user){
        //获取登录用户信息
        UserInfo userInfo = UserContextUtil.getUserInfo();
        user.setCreateUser(userInfo.getAccount());
        user.setCompanyCode(userInfo.getCompanyCode());
        user.setUpdateUser(userInfo.getAccount());
        String logmsg=null;
        try {
            Map map = userService.addUser(user);
            boolean flag = (boolean) map.get("flag");
            logmsg=LocaleMessage.get(map.get("msg").toString());
            if (flag) {
                return BaseResponse.ok().headers(HeaderUtil.createAlert(logmsg, ENTITY_NAME)).body(null);
            } else {
                return BaseResponse.badRequest().headers(HeaderUtil.createAlert(logmsg, ENTITY_NAME)).body(null);
            }
        }catch (Exception e){
            logmsg = LocaleMessage.get("message.system.save.fail");
            LoggerCommon.info(this.getClass(),"添加用户异常："+ExceptionStackMessage.collectExceptionStackMsg(e));
            return BaseResponse.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME,"system.server.exception",
                    logmsg)).body(ResponseLogMessageHandel.initResponseData(HttpStatus.BAD_REQUEST.value(), ExceptionEnum.EXCEPTION_CODE.code(),
                    LocaleMessage.get("system.server.exception"), ExceptionStackMessage.collectExceptionStackMsg(e),null));
        }
    }

    /**
     * @Author: wangtao
     * @Date: 2018/05/21 16:56
     * @Params: user
     *           用户实体
     * @Description: 修改用户信息
     * @return: ResultVO
     */
    @PostMapping("/updateUser")
    @ApiOperation(value = "修改用户信息", notes = "修改用户信息")
    public BaseResponse updateUser(@RequestBody  User user){
        //获取登录用户信息
        UserInfo userInfo = UserContextUtil.getUserInfo();
        user.setUpdateUser(userInfo.getAccount());
        user.setCompanyCode(userInfo.getCompanyCode());
        String logmsg=null;
        try {
            Map map = userService.updateUser(user);
            boolean flag = (boolean) map.get("flag");
            logmsg = LocaleMessage.get(String.valueOf(map.get("msg")));
            if (flag) {
                return BaseResponse.ok().headers(HeaderUtil.createAlert(logmsg, ENTITY_NAME)).body(null);
            } else {
                return BaseResponse.badRequest().headers(HeaderUtil.createAlert(logmsg, ENTITY_NAME)).body(null);
            }
        }catch (Exception e){
            logmsg = LocaleMessage.get("message.system.update.fail");
            LoggerCommon.info(this.getClass(),"修改用户信息异常："+ExceptionStackMessage.collectExceptionStackMsg(e));
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
    @GetMapping("/getUserById")
    @ApiOperation(value = "查询单个用户", notes = "查询单个用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", dataType = "String", paramType = "query", required = true),
    })
    public BaseResponse getUserById(@RequestParam  String userId){
        try {
            Map user = userService.getUserByUserId(userId);
            return BaseResponse.ok().body(ResponseLogMessageHandel.initSuccessResponseData(user));
        }catch (Exception e){
            String logmsg=LocaleMessage.get("message.query.errorMessage");
            LoggerCommon.info(this.getClass(),"查询单个用户异常："+ExceptionStackMessage.collectExceptionStackMsg(e));
            return BaseResponse.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME,"system.server.exception",
                    logmsg)).body(ResponseLogMessageHandel.initResponseData(HttpStatus.BAD_REQUEST.value(), ExceptionEnum.EXCEPTION_CODE.code(),
                    LocaleMessage.get("system.server.exception"), ExceptionStackMessage.collectExceptionStackMsg(e),null));
        }
    }

    /**
     * @Author: wangtao
     * @Date: 2018/05/21 16:57
     * @Params: status
     *              状态（1：启用 0：停用）
     * @Params: userId
     *              用户id
     * @Description: 启用，停用 data{"userId":,"status":}
     * @return:
     */
    @PostMapping("/updateStatus")
    @ApiOperation(value = "用户启用停用", notes = "用户启用停用")
    public BaseResponse updateStatus(@RequestBody Map data){
        String logmsg =LocaleMessage.get("message.system.request.param.exception");
        if(null==data.get("userId")||StringUtil.isEmpty(data.get("userId").toString())){
            return BaseResponse.badRequest().headers(HeaderUtil.createAlert(logmsg,ENTITY_NAME)).body(null);
        }
        if(null==data.get("status")||StringUtil.isEmpty(data.get("status").toString())){
            return BaseResponse.badRequest().headers(HeaderUtil.createAlert(logmsg,ENTITY_NAME)).body(null);
        }
        User user = new User();
        //获取登录用户信息
        UserInfo userInfo = UserContextUtil.getUserInfo();
        user.setUpdateUser(userInfo.getAccount());
        user.setStatus(Short.parseShort(data.get("status").toString()));
        user.setUserId(data.get("userId").toString());
        try {
            Map map = userService.updateState(user);
            boolean flag = (boolean) map.get("flag");
            logmsg=LocaleMessage.get(map.get("msg").toString());
            if (flag) {
                return BaseResponse.ok().headers(HeaderUtil.createAlert(logmsg, ENTITY_NAME)).body(null);
            } else {
                return BaseResponse.badRequest().headers(HeaderUtil.createAlert(logmsg, ENTITY_NAME)).body(null);
            }
        }catch (Exception e){
            logmsg=LocaleMessage.get("message.system.operation.fail");
            LoggerCommon.info(this.getClass(),"用户启用停用异常："+ExceptionStackMessage.collectExceptionStackMsg(e));
            return BaseResponse.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME,"system.server.exception",
                    logmsg)).body(ResponseLogMessageHandel.initResponseData(HttpStatus.BAD_REQUEST.value(), ExceptionEnum.EXCEPTION_CODE.code(),
                    LocaleMessage.get("system.server.exception"), ExceptionStackMessage.collectExceptionStackMsg(e),null));
        }
    }

    /**
     * @Author: wangtao
     * @Date: 2018/05/21 16:59
     * @Params: userId
     *              用户id
     * @Description: 重置密码
     * @return: ResultVO
     */
    @PostMapping("/resetPassword")
    @ApiOperation(value = "重置密码", notes = "重置密码")
    public BaseResponse resetPassword(@RequestBody Map data){
        if(null==data.get("userId")||StringUtil.isEmpty(data.get("userId").toString())){
            return BaseResponse.badRequest().headers(HeaderUtil.createAlert("传参异常", ENTITY_NAME)).body(null);
        }
        String userId = data.get("userId").toString();
        //获取登录用户信息
        UserInfo userInfo = UserContextUtil.getUserInfo();
        String updateUser = userInfo.getAccount();
        String logmsg = null;
        try {
            Map map = userService.resetPassword(userId, updateUser);
            boolean flag = (boolean) map.get("flag");
            logmsg=LocaleMessage.get(map.get("msg").toString());
            if (flag) {
                return BaseResponse.ok().headers(HeaderUtil.createAlert(logmsg, ENTITY_NAME)).body(null);
            } else {
                return BaseResponse.badRequest().headers(HeaderUtil.createAlert(logmsg, ENTITY_NAME)).body(null);
            }
        }catch (Exception e){
            logmsg=LocaleMessage.get("message.system.operation.fail");
            LoggerCommon.info(this.getClass(),"用户重置密码异常："+ExceptionStackMessage.collectExceptionStackMsg(e));
            return BaseResponse.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME,"system.server.exception",
                    logmsg)).body(ResponseLogMessageHandel.initResponseData(HttpStatus.BAD_REQUEST.value(), ExceptionEnum.EXCEPTION_CODE.code(),
                    LocaleMessage.get("system.server.exception"), ExceptionStackMessage.collectExceptionStackMsg(e),null));
        }
    }

    /**
     * @Author: wangtao
     * @Date: 2018/05/21 17:04
     * @Params: data
     * @Description: 获取用户列表
     * @return: resultVO
     */
    @GetMapping("/getUserList")
    @ApiOperation(value = "获取用户列表", notes = "获取用户列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize", value = "分页大小", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "pageIndex", value = "当前页码", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "account", value = "账号", dataType = "String", paramType = "query", required = false),
            @ApiImplicitParam(name = "realName", value = "名称", dataType = "String", paramType = "query", required = false),
            @ApiImplicitParam(name = "tel", value = "电话", dataType = "String", paramType = "query", required = false),
    })
    public BaseResponse getUserList(@RequestParam("pageSize") Integer pageSize,
                                      @RequestParam("pageIndex")Integer pageIndex,
                                      @RequestParam(value = "account",required = false)String account,
                                      @RequestParam(value = "realName",required = false)String realName,
                                      @RequestParam(value = "tel",required = false)String tel){
        Map map = new HashMap();
        UserInfo userInfo = UserContextUtil.getUserInfo();
        String orgPath = UserContextUtil.getDataPermissions(serverName.replace(ServerConstans.API_PRE,""));
        String createUser = userInfo.getAccount();
        /*if(StringUtil.isNotEmpty(orgPath)){
            createUser = "";
        }*/
        map.put("orgPath",orgPath);
        map.put("createUser",createUser);
        map.put("companyCode",userInfo.getCompanyCode());
        map.put("loginAccount",userInfo.getAccount());
        map.put("account",account);
        map.put("realName",realName);
        map.put("tel",tel);
        try {
            PageHelper.startPage(pageIndex, pageSize, true);
            List<User> list = userService.getUserList(map);
            PageInfo pageInfo = new PageInfo(list);
            return BaseResponse.ok().body(ResponseLogMessageHandel.initSuccessResponseData(pageInfo));
        }catch(Exception e){
            String logmsg=LocaleMessage.get("message.query.errorMessage");
            LoggerCommon.info(this.getClass(),"获取用户列表异常："+ExceptionStackMessage.collectExceptionStackMsg(e));
            return BaseResponse.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME,"system.server.exception",
                    logmsg)).body(ResponseLogMessageHandel.initResponseData(HttpStatus.BAD_REQUEST.value(), ExceptionEnum.EXCEPTION_CODE.code(),
                    LocaleMessage.get("system.server.exception"), ExceptionStackMessage.collectExceptionStackMsg(e),null));
        }
    }
}
