package com.base.saas.system.controller;

import com.alibaba.fastjson.JSON;
import com.base.saas.common.constant.AppConstant;
import com.base.saas.common.language.LocaleMessage;
import com.base.saas.common.logger.LoggerCommon;
import com.base.saas.common.response.BaseResponse;
import com.base.saas.common.response.ExceptionEnum;
import com.base.saas.common.response.ExceptionStackMessage;
import com.base.saas.common.response.ResponseLogMessageHandel;
import com.base.saas.common.userinfo.UserContextUtil;
import com.base.saas.common.userinfo.UserInfo;
import com.base.saas.system.model.CompanyUserLogin;
import com.base.saas.system.model.CompanyUserLoginResponse;
import com.base.saas.system.model.KeyScriptRequest;
import com.base.saas.system.model.ValidateCode;
import com.base.saas.system.service.CompanyLoginService;
import com.base.saas.system.service.ValidateCodeService;
import com.base.saas.util.HeaderUtil;
import com.base.saas.util.IPUtil;
import com.base.saas.util.RsaUtils;
import com.base.saas.util.redis.RedisKeyConstants;
import com.base.saas.util.redis.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Title :
 * Description : @类注释说明写在此处@
 * Create on : 2018年05月22日
 * Copyright (C) zw.FinTec
 *
 * @author department:研发部
 * username:zh-pc
 * @version 修改历史:
 * 修改人 修改日期 修改描述
 * -------------------------------------------<
 */
@RestController
@RequestMapping("/api/login")
@Api(value = "登录相关", description = "登陆相关")
public class LoginController {

    private static final String ENTITY_NAME = "login";

    @Autowired
    private CompanyLoginService companyLoginService;

    @Autowired
    private ValidateCodeService validateCodeService;

    @Value("${sessionTimeout}")
    private String sessionTimeout;

    @Autowired
    private LocaleMessage localeMessage;


    @ApiOperation(value = "加载系统菜单", notes = "加载系统菜单")
    @GetMapping("/index")
    public BaseResponse index() {
        UserInfo userInfo = UserContextUtil.getUserInfo();
        String userId = userInfo.getUserId();
        String companyCode = userInfo.getCompanyCode();
        Map<String, Object> returnMap =null;
        try {
           returnMap = companyLoginService.getComRoleList(userId, companyCode);
            //获取当前登录用户的ip
            String ip = IPUtil.getIpAddr(UserContextUtil.getHttpServletRequest());
            companyLoginService.updateComLoginInfo(userId, companyCode, ip);
        }catch (Exception e){
            String logmsg = LocaleMessage.get("message.query.errorMessage");
            LoggerCommon.info(this.getClass(),"加载系统菜单异常："+ ExceptionStackMessage.collectExceptionStackMsg(e));
            return BaseResponse.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME,"system.server.exception",
                    logmsg)).body(ResponseLogMessageHandel.initResponseData(HttpStatus.BAD_REQUEST.value(), ExceptionEnum.EXCEPTION_CODE.code(),
                    LocaleMessage.get("system.server.exception"), ExceptionStackMessage.collectExceptionStackMsg(e),null));
        }
        Map respMap = new HashMap();
        respMap.put("allBtnIds",returnMap.get("allBtnIds"));//企业所有菜单及按钮的id
        respMap.put("menuList", returnMap.get("menuList"));//企业第一个模块下的所有菜单
        respMap.put("moduleList",returnMap.get("moduleList"));//企业所有模块
        userInfo.setDataPermission((Map<String, Map<String, String>>) returnMap.get("dataRoleList"));
        userInfo.setPermissionList((java.util.List<String>) returnMap.get("permissionList"));
        UserContextUtil.setUserInfo(userInfo);
        return BaseResponse.ok().body(ResponseLogMessageHandel.initSuccessResponseData(respMap));
    }

    @ApiOperation(value = "企业用户登录", notes = "企业用户登录")
    @PostMapping("/doLogin")
    public BaseResponse doLogin(@RequestBody KeyScriptRequest keyScriptRequest, HttpServletRequest request) {

        String result = null;
        try {
            result = RsaUtils.privateDecrypt(keyScriptRequest.getKeyScript());
        } catch (Exception e) {
            String localeTipMsg = "登录秘钥错误";
            LoggerCommon.info(this.getClass(),"企业用户登录异常："+ExceptionStackMessage.collectExceptionStackMsg(e));
            return BaseResponse.badRequest()
                    .headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "system.server.exception", localeTipMsg))
                    .body(
                            ResponseLogMessageHandel.initResponseData(HttpStatus.BAD_REQUEST.value(), ExceptionEnum.EXCEPTION_CODE.code(),
                                    "登录秘钥错误", "登录秘钥错误",null)
                    );
        }
        CompanyUserLogin userLogin = JSON.parseObject(result, CompanyUserLogin.class);

        // 判断验证码
        ValidateCode validateCode = new ValidateCode(userLogin.getKey(), userLogin.getCode());
        boolean codeCheckResult = validateCodeService.check(validateCode);
        if(!codeCheckResult) {
            LoggerCommon.info(this.getClass(),"企业用户登录失败：验证码不正确");
            return BaseResponse.badRequest().headers(HeaderUtil.createAlert("验证码不正确",ENTITY_NAME)).body(
//                    ResponseLogMessageHandel.initExceptionResponseData()
                    ResponseLogMessageHandel.initResponseData(HttpStatus.BAD_REQUEST.value(), ExceptionEnum.EXCEPTION_CODE.code(),
                            "验证码不正确", "验证码不正确",null)
            );
        }

        String username=userLogin.getUserName().trim();
        String password=userLogin.getPassword().trim();
        String companyCode=userLogin.getCompanyCode().trim();

        Map<String, String> returnMap =null;
        String localeTipMsg =null;
        try {
            returnMap = companyLoginService.comLogin(username, password, companyCode);
        }catch (Exception e){
            localeTipMsg=LocaleMessage.get("system.server.exception");
            LoggerCommon.info(this.getClass(),"企业用户登录异常："+ExceptionStackMessage.collectExceptionStackMsg(e));
            return BaseResponse.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "system.server.exception", localeTipMsg))
                    .body(
                            ResponseLogMessageHandel.initResponseData(HttpStatus.BAD_REQUEST.value(), ExceptionEnum.EXCEPTION_CODE.code(),
                                    "企业用户登录异常", "企业用户登录异常",null)
                    );
        }
        localeTipMsg=LocaleMessage.get(returnMap.get("message"));
        if ("0".equals(returnMap.get("code"))) {
            UserInfo userInfo = new UserInfo();
            userInfo.setAccount(username);
            userInfo.setCompanyCode(companyCode);
            UserContextUtil.setUserInfo(userInfo);
            UserContextUtil.setAttribute("message", localeTipMsg);
            LoggerCommon.info(this.getClass(),"企业用户登录失败："+localeTipMsg);
            return BaseResponse.badRequest().headers(HeaderUtil.createAlert(localeTipMsg,ENTITY_NAME)).body(

                    ResponseLogMessageHandel.initResponseData(HttpStatus.BAD_REQUEST.value(), ExceptionEnum.EXCEPTION_CODE.code(),
                            localeTipMsg + "", localeTipMsg + "",null)

            );
        } else {
            String loginIp =request.getRemoteAddr();
            LoggerCommon.info(this.getClass(),"登录成功"+username);
            UserInfo userInfo = new UserInfo();
            userInfo.setAccount(username);
            userInfo.setCompanyCode(returnMap.get("company_code"));
            userInfo.setCompanyName(returnMap.get("company_name"));
            userInfo.setOrgId(returnMap.get("org_id"));
            userInfo.setRealName(returnMap.get("real_name"));
            userInfo.setUserId(returnMap.get("user_id"));
            userInfo.setUserType(2);
            UserContextUtil.setUserInfo(UserContextUtil.getSession().getId(),userInfo);
            UserContextUtil.setAttribute("roleName", returnMap.get("role_name"));
            CompanyUserLoginResponse response = new CompanyUserLoginResponse();
            response.setToken(UserContextUtil.getSession().getId());
            response.setUserInfo(userInfo);
            LocaleMessage.setLocale(response.getToken());
            RedisUtil.set(RedisKeyConstants.LOGIN_PREFIX + companyCode + "-" + username, loginIp, Integer.parseInt(sessionTimeout));
            return BaseResponse.ok().body(ResponseLogMessageHandel.initSuccessResponseData(response));
        }
    }


    /*@ApiOperation(value = "企业用户登录", notes = "企业用户登录")
    @PostMapping("/doLogin")
    public BaseResponse doLogin(@RequestBody CompanyUserLogin userLogin, HttpServletRequest request) {
        String username=userLogin.getUserName().trim();
        String password=userLogin.getPassword().trim();
        String companyCode=userLogin.getCompanyCode().trim();
        Map<String, String> returnMap =null;
        String localeTipMsg =null;
        try {
            returnMap = companyLoginService.comLogin(username, password, companyCode);
        }catch (Exception e){
            localeTipMsg=LocaleMessage.get("system.server.exception");
            LoggerCommon.info(this.getClass(),"企业用户登录异常："+ExceptionStackMessage.collectExceptionStackMsg(e));
            return BaseResponse.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "system.server.exception", localeTipMsg))
                    .body(ResponseLogMessageHandel.initExceptionResponseData());
        }
            localeTipMsg=LocaleMessage.get(returnMap.get("message"));
            if ("0".equals(returnMap.get("code"))) {
                UserInfo userInfo = new UserInfo();
                userInfo.setAccount(username);
                userInfo.setCompanyCode(companyCode);
                UserContextUtil.setUserInfo(userInfo);
                UserContextUtil.setAttribute("message", localeTipMsg);
                LoggerCommon.info(this.getClass(),"企业用户登录失败："+localeTipMsg);
                return BaseResponse.badRequest().headers(HeaderUtil.createAlert(localeTipMsg,ENTITY_NAME)).body(ResponseLogMessageHandel.initExceptionResponseData());
            } else {
//                String lastLoginIp = RedisUtil.get(RedisKeyConstants.LOGIN_PREFIX + companyCode + "-" + username);
                String loginIp =request.getRemoteAddr();
//                if (lastLoginIp != null && !loginIp.equals(lastLoginIp)) {
//                    UserContextUtil.setAttribute("message", "用户已登录!");
//                    modelAndView.setViewName("redirect:/");
//                    return modelAndView;
//                }
                LoggerCommon.info(this.getClass(),"登录成功"+username);
                UserInfo userInfo = new UserInfo();
                userInfo.setAccount(username);
                userInfo.setCompanyCode(returnMap.get("company_code"));
                userInfo.setCompanyName(returnMap.get("company_name"));
                userInfo.setOrgId(returnMap.get("org_id"));
                userInfo.setRealName(returnMap.get("real_name"));
                userInfo.setUserId(returnMap.get("user_id"));
                userInfo.setUserType(2);
                UserContextUtil.setUserInfo(UserContextUtil.getSession().getId(),userInfo);
//                UserContextUtil.setUserInfo(userInfo);
                UserContextUtil.setAttribute("roleName", returnMap.get("role_name"));
                CompanyUserLoginResponse response = new CompanyUserLoginResponse();
                response.setToken(UserContextUtil.getSession().getId());
                response.setUserInfo(userInfo);
                LocaleMessage.setLocale(response.getToken());
                RedisUtil.set(RedisKeyConstants.LOGIN_PREFIX + companyCode + "-" + username, loginIp, Integer.parseInt(sessionTimeout));
                return BaseResponse.ok().body(ResponseLogMessageHandel.initSuccessResponseData(response));
            }
    }*/



    /**
     * 修改密码
     *
     * @param map
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "修改密码", notes = "修改密码")
    @PostMapping("/updateUserPwd")
    public BaseResponse updateComUserPwd(@RequestBody Map map) {
        String oldPwd = map.get("oldPwd").toString();
        String newPwd = map.get("newPwd").toString();
        UserInfo userInfo = UserContextUtil.getUserInfo();
        String localeTipMsg=null;
        try {
            Map<String, Object> respMap = companyLoginService.updateComUserPwd(userInfo.getAccount(), userInfo.getCompanyCode(), oldPwd, newPwd);
            boolean flag = (boolean) respMap.get("flag");
            localeTipMsg=LocaleMessage.get(respMap.get("msg").toString());
            if (flag) {
                LoggerCommon.info(this.getClass(),"修改密码成功");
                return BaseResponse.ok().headers(HeaderUtil.createAlert(localeTipMsg, ENTITY_NAME)).body(null);
            } else {
                LoggerCommon.info(this.getClass(),"修改密码失败："+localeTipMsg);
                return BaseResponse.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "system.server.exception", localeTipMsg)).body(null);
            }
        }catch (Exception e){
            localeTipMsg=LocaleMessage.get("message.system.update.fail");
            LoggerCommon.info(this.getClass(),"修改密码："+localeTipMsg);
            return BaseResponse.badRequest().headers(HeaderUtil.createAlert(localeTipMsg,ENTITY_NAME)).body(null);
        }
    }

    @GetMapping("/loadindex")
    @ApiOperation(value = "测试多语言响应", notes = "测试多语言响应")
    public BaseResponse loadindex(HttpServletRequest request,String userId) throws Exception {
        LoggerCommon.info(this.getClass(),"测试多语言响应");
       /* UserLoginResponse response = new UserLoginResponse();
        Map<String,Object> data = new HashMap<>();
        response.setData(data);
        response.setToken(request.getSession().getId());*/
        return BaseResponse.ok().headers(HeaderUtil.createAlert(localeMessage.get("message.system.errorMessage"), ENTITY_NAME)).body(null);
    }



    /**
     * 退出
     *
     * @return
     */
    @ApiOperation(value = "退出",httpMethod = "POST", notes = "退出")
    @PostMapping("/logout")
    public BaseResponse logout() {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            subject.logout();
        }
        if (UserContextUtil.getUserInfo() != null) {
            RedisUtil.del(RedisKeyConstants.LOGIN_PREFIX  + UserContextUtil.getUserInfo().getCompanyCode() + "-" + UserContextUtil.getUserInfo().getAccount());
            RedisUtil.del(AppConstant.APP_USER_INFO + UserContextUtil.getUserTokenId());
        }
        return BaseResponse.ok().body(null);
    }
}
