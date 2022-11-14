package com.base.saas.manage.controller.enterprise;

import com.alibaba.fastjson.JSON;

import com.base.saas.AppConstant;
import com.base.saas.language.LocaleMessage;
import com.base.saas.logger.LoggerCommon;
import com.base.saas.manage.domain.model.ReturnMap;
import com.base.saas.manage.domain.entity.enterprise.EntModule;
import com.base.saas.manage.domain.entity.enterprise.EntUser;
import com.base.saas.manage.domain.model.EntUserLoginRequest;
import com.base.saas.manage.domain.model.EntUserLoginResponse;
import com.base.saas.manage.domain.model.KeyScriptRequest;
import com.base.saas.util.ExceptionStackUtils;
import com.base.saas.userinfo.UserContextUtil;
import com.base.saas.userinfo.UserInfo;
import com.base.saas.manage.service.enterprise.EntLoginService;
import com.base.saas.util.HeaderUtil;
import com.base.saas.util.IPUtil;
import com.base.saas.util.RSAUtils;

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
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/entlogin")
@Api(tags = "企业管理端登录")
public class EntLoginController {

    @Autowired
    private EntLoginService companyLoginService;

    @Value("${sessionTimeout}")
    private String sessionTimeout;

    @Resource
    private RedisUtil redisUtil;

    @ApiOperation(value = "企业用户登录", notes = "企业用户登录")
    @PostMapping("/doLogin")
    public ResponseEntity doLogin(@RequestBody KeyScriptRequest keyScriptRequest, HttpServletRequest request) {

        String result = null;
        String localeTipMsg = null;
        try {
            result = RSAUtils.privateDecrypt(keyScriptRequest.getKeyScript());
        } catch (Exception e) {
            localeTipMsg = LocaleMessage.get("message.screte.key.error");
            return ResponseEntity.badRequest()
                    .headers(HeaderUtil.createErrorMsg(localeTipMsg))
                    .body(null);
        }
        EntUserLoginRequest userLogin = JSON.parseObject(result, EntUserLoginRequest.class);

        // 校验验证码
        if (!ValidateCodeOperationUtils.check(redisUtil, userLogin.getKey(), userLogin.getCode())) {
            localeTipMsg = LocaleMessage.get("message.validatecode.checkfail");
            LoggerCommon.info(this.getClass(), "企业端用户登录失败：" + localeTipMsg);
            return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(localeTipMsg)).body(null);
        }

        String username = userLogin.getUserName().trim();
        String password = userLogin.getPassword().trim();
        String companyCode = userLogin.getCompanyCode().trim();

        ReturnMap<EntUser> returnMap = null;

        try {
            returnMap = companyLoginService.entLogin(username, password, companyCode);
        } catch (Exception e) {
            localeTipMsg = LocaleMessage.get("system.server.exception");

            LoggerCommon.info(this.getClass(), "企业用户登录异常：" + ExceptionStackUtils.collectExceptionStackMsg(e));

            return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(localeTipMsg))
                    .body(null);
        }

        if (returnMap.getCode() == 0) {

            LoggerCommon.info(this.getClass(), "企业用户登录失败：" + localeTipMsg);
            return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(localeTipMsg)).body(null);
        } else {//登录成功
            EntUser entUser = returnMap.getT();

            String loginIp = request.getRemoteAddr();
            LoggerCommon.info(this.getClass(), "登录成功" + username);
            UserInfo userInfo = new UserInfo();
            userInfo.setAccount(username);
            userInfo.setCompanyCode(entUser.getCompanyCode());
            userInfo.setCompanyName(entUser.getCompanyName());
            userInfo.setOrgId(entUser.getOrgId());
            userInfo.setRealName(entUser.getRealName());
            userInfo.setUserId(entUser.getUserId());
            userInfo.setUserType(2);
            UserContextUtil.setUserInfo(UserContextUtil.getSession().getId(), userInfo);

            String session = UserContextUtil.getSession().getId();

            EntUserLoginResponse response = new EntUserLoginResponse();
            response.setToken(session);
            response.setUserInfo(userInfo);
            LocaleMessage.setLocale(response.getToken());
            RedisUtil.set(RedisKeyConstants.LOGIN_PREFIX + companyCode + "-" + username, loginIp, Integer.parseInt(sessionTimeout));
            return ResponseEntity.ok().headers(HeaderUtil.createToken(session)).body(response);
        }
    }


    @ApiOperation(value = "加载系统菜单", notes = "加载系统菜单")
    @GetMapping("/index")
    public ResponseEntity index() {
        UserInfo userInfo = UserContextUtil.getUserInfo();
        String userId = userInfo.getUserId();
        String companyCode = userInfo.getCompanyCode();
        List<EntModule> entModules = null;
        try {
            entModules = companyLoginService.getModuleAndMenuList(userId, companyCode);
            //获取当前登录用户的ip
            String ip = IPUtil.getIpAddr(UserContextUtil.getHttpServletRequest());
            companyLoginService.updateEntLoginIpInfo(userId, companyCode, ip);
        } catch (Exception e) {
            String logmsg = LocaleMessage.get("message.query.errorMessage");

            LoggerCommon.info(this.getClass(), "加载系统菜单异常：" + ExceptionStackUtils.collectExceptionStackMsg(e));

            return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(logmsg)).body(null);
        }
        return ResponseEntity.ok().body(entModules);
    }


    @ApiOperation(value = "修改密码", notes = "修改密码")
    @PostMapping("/updateUserPwd")
    public ResponseEntity updateComUserPwd(@RequestBody Map map) {
        String oldPwd = map.get("oldPwd").toString();
        String newPwd = map.get("newPwd").toString();
        UserInfo userInfo = UserContextUtil.getUserInfo();
        String localeTipMsg = null;
        try {
            ReturnMap respMap = companyLoginService.updateComUserPwd(userInfo.getAccount(), userInfo.getCompanyCode(), oldPwd, newPwd);

            localeTipMsg = LocaleMessage.get(respMap.getMsg());
            if (respMap.getCode() == 1) {
                LoggerCommon.info(this.getClass(), "修改密码成功");
                return ResponseEntity.ok().body(null);
            } else {
                LoggerCommon.info(this.getClass(), "修改密码失败：" + localeTipMsg);
                return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(localeTipMsg)).body(null);
            }
        } catch (Exception e) {
            localeTipMsg = LocaleMessage.get("message.system.update.fail");
            LoggerCommon.info(this.getClass(), "修改密码：" + localeTipMsg);
            return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(localeTipMsg)).body(null);
        }
    }

    @ApiOperation(value = "退出", httpMethod = "POST", notes = "退出")
    @PostMapping("/logout")
    public ResponseEntity logout() {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            subject.logout();
        }
        if (UserContextUtil.getUserInfo() != null) {
            RedisUtil.del(RedisKeyConstants.LOGIN_PREFIX + UserContextUtil.getUserInfo().getCompanyCode() + "-" + UserContextUtil.getUserInfo().getAccount());
            RedisUtil.del(AppConstant.APP_USER_INFO + UserContextUtil.getUserTokenId());
        }
        return ResponseEntity.ok().body(null);
    }
}
