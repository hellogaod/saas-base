package com.base.saas.manage.controller.system;

import com.base.saas.manage.model.ReturnMap;
import com.base.saas.manage.model.UserLoginRequest;
import com.base.saas.manage.model.enterprise.EntMenu;
import com.base.saas.manage.model.system.SysUser;
import com.base.saas.manage.service.system.SysLoginService;
import com.base.saas.common.AppConstant;
import com.base.saas.common.language.LocaleMessage;
import com.base.saas.common.logger.LoggerCommon;
import com.base.saas.util.response.ExceptionStackMessage;
import com.base.saas.common.userinfo.UserContextUtil;
import com.base.saas.common.userinfo.UserInfo;
import com.base.saas.util.HeaderUtil;
import com.base.saas.util.StringUtil;
import com.base.saas.util.redis.RedisKeyConstants;
import com.base.saas.util.redis.RedisUtil;
import com.base.saas.util.validatecode.ValidateCodeOperationUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Title :
 * Description : @类注释说明写在此处@
 * Create on : 2018年05月22日
 * Copyright (C)
 *
 * @author department:研发部
 * username:zh-pc
 * @version 修改历史:
 * 修改人 修改日期 修改描述
 * -------------------------------------------<
 */
@RestController
@RequestMapping("/api/syslogin")
@Api(value = "系统管理端登录相关操作")
public class SysLoginController {
    private static final String ENTITY_NAME = "syslogin";

    @Value("${duplicateLogin}")
    private String duplicateLogin;

    @Value("${sessionTimeout}")
    private String sessionTimeout;

    @Autowired
    private SysLoginService loginService;

    @Resource
    private RedisUtil redisUtil;

    @PostMapping("/dologin")
    @ApiOperation(value = "系统用户登陆", notes = "系统用户登陆")
    public ResponseEntity sysLogin(@RequestBody UserLoginRequest loginRequest, HttpServletRequest request) {

        String localeTipMsg;
        // 校验验证码
        if (!ValidateCodeOperationUtils.check(redisUtil, loginRequest.getKey(), loginRequest.getCode())) {
            localeTipMsg = LocaleMessage.get("message.validatecode.checkfail");
            LoggerCommon.info(this.getClass(), "管理端用户登录失败：" + localeTipMsg);
            return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(localeTipMsg)).body(null);
        }

        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        HttpSession session = request.getSession();
        ReturnMap<SysUser> returnMap;

        try {
            returnMap = loginService.login(username.trim(), password.trim());
        } catch (Exception e) {
            localeTipMsg = LocaleMessage.get("system.server.exception");
            LoggerCommon.info(this.getClass(), "系统用户登陆异常：" + ExceptionStackMessage.collectExceptionStackMsg(e));
            return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(localeTipMsg)).body(null);
        }

        localeTipMsg = LocaleMessage.get(returnMap.getMsg());
        if (returnMap.getCode() == 0) {

            LoggerCommon.info(this.getClass(), "登录失败：" + localeTipMsg);
            return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(localeTipMsg)).body(null);

        } else {//系统登录成功
            String lastLoginIp = RedisUtil.get(RedisKeyConstants.LOGIN_PREFIX + username);
            String loginIp = request.getRemoteAddr();

            if ("0".equals(duplicateLogin) && lastLoginIp != null && !loginIp.equals(lastLoginIp)) {
                localeTipMsg = LocaleMessage.get("message.user.has.logining");

                LoggerCommon.info(this.getClass(), "登录失败：" + localeTipMsg);

                return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(localeTipMsg)).body(null);
            }

            UserInfo userInfo = new UserInfo();
            userInfo.setAccount(username);
            userInfo.setOrgId(returnMap.getT().getOrgId());
            userInfo.setRealName(returnMap.getT().getRealName());
            userInfo.setUserId(returnMap.getT().getUserId());
            userInfo.setUserType(1);

            //数据根据sessionId作为key存储在redis里面
            UserContextUtil.setUserInfo(session.getId(), userInfo);

            System.out.println("set user key:" + session.getId());

            //查询用户角色
            Map<String, Object> roleInfo = null;
            try {
                roleInfo = loginService.getUserRoleInfo(userInfo.getUserId());
            } catch (Exception e) {
                localeTipMsg = LocaleMessage.get("system.server.exception");
                LoggerCommon.info(this.getClass(), "系统用户登陆查询用户角色异常：" + ExceptionStackMessage.collectExceptionStackMsg(e));
                return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(localeTipMsg)).body(null);
            }

            if (ObjectUtils.isEmpty(roleInfo)) {//用户未分配角色，请联系管理员

                localeTipMsg = LocaleMessage.get("message.user.not.assigned.role");
                LoggerCommon.info(this.getClass(), "登录失败：" + localeTipMsg);
                return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(localeTipMsg)).body(null);

            } else if (1 != Short.valueOf(roleInfo.get("status").toString())) {//用户角色已停用，请联系管理员！

                localeTipMsg = LocaleMessage.get("message.user.not.assigned.role");
                LoggerCommon.info(this.getClass(), "登录失败：" + localeTipMsg);
                return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(localeTipMsg)).body(null);
            }

            //存储用户信息 -> 用户登录ip地址
            RedisUtil.set(RedisKeyConstants.LOGIN_PREFIX + username, loginIp, Integer.parseInt(sessionTimeout));

            LocaleMessage.setLocale(session.getId());
            LoggerCommon.info(this.getClass(), "登录成功");

            //sessionid作为token存储在header中
            return ResponseEntity.ok().headers(HeaderUtil.createToken(session.getId())).body(userInfo);

        }
    }

    @GetMapping("/index")
    @ApiOperation(value = "加载系统菜单", notes = "加载系统菜单")
    public ResponseEntity index(HttpServletRequest request) {

        HttpSession session = request.getSession();
        UserInfo userInfo = UserContextUtil.getUserInfo(session.getId());
        String userId = userInfo.getUserId();

        ReturnMap<List<EntMenu>> returnMap;
        try {
            returnMap = loginService.getMenuList(userId);

            //更新用户最后一次登录ip地址
            loginService.updateLoginInfo(userId, UserContextUtil.getHttpServletRequest().getRemoteAddr());
        } catch (Exception e) {
            String logmsg = LocaleMessage.get("message.query.errorMessage");
            LoggerCommon.info(this.getClass(), "加载系统菜单异常：" + ExceptionStackMessage.collectExceptionStackMsg(e));
            return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(logmsg)).body(null);
        }

        return ResponseEntity.ok().headers(HeaderUtil.createToken(session.getId())).body(returnMap.getT());
    }


    /**
     * 退出
     *
     * @return
     * @throws IOException
     */
    @ApiOperation(value = "退出登录", httpMethod = "POST", notes = "退出")
    @PostMapping("/logout")
    public ResponseEntity logout(HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            subject.logout();
        }

        HttpSession session = request.getSession();
        if (UserContextUtil.getUserInfo(session.getId()) != null) {
            RedisUtil.del(RedisKeyConstants.LOGIN_PREFIX + UserContextUtil.getUserInfo().getAccount());
            RedisUtil.del(AppConstant.APP_USER_INFO + session.getId());
        }
        return ResponseEntity.ok().body(null);
    }


    /**
     * 修改密码
     *
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "修改密码", httpMethod = "POST", notes = "修改密码")
    @PostMapping("/updateUserPwd")
    public ResponseEntity updateUserPwd(@RequestBody Map data) {
        String localMsg = null;
        if (null == data.get("oldPwd") || StringUtil.isEmpty(data.get("oldPwd").toString())) {
            localMsg = LocaleMessage.get("message.oldpassword.isEmpty");
            return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(localMsg)).body(null);
        }
        if (null == data.get("newPwd") || StringUtil.isEmpty(data.get("newPwd").toString())) {
            localMsg = LocaleMessage.get("message.newpassword.isEmpty");
            return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(localMsg)).body(null);
        }
        String newPwd = data.get("newPwd").toString();
        String oldPwd = data.get("oldPwd").toString();

        try {
            ReturnMap respMap = loginService.updateUserPwd(UserContextUtil.getUserInfo().getAccount(), oldPwd, newPwd);

            localMsg = LocaleMessage.get(respMap.getMsg());
            if (respMap.getCode() == 1) {
                LoggerCommon.info(this.getClass(), localMsg);
                return ResponseEntity.ok().headers(HeaderUtil.createToken(UserContextUtil.getUserTokenId())).body(null);
            } else {
                return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(localMsg)).body(null);
            }
        } catch (Exception e) {
            localMsg = LocaleMessage.get("message.system.update.fail");
            LoggerCommon.info(this.getClass(), "修改密码异常信息：" + ExceptionStackMessage.collectExceptionStackMsg(e));
            return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(localMsg)).body(null);
        }
    }
}
