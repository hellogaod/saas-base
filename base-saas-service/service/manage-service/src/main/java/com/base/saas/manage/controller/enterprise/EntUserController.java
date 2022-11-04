package com.base.saas.manage.controller.enterprise;

<<<<<<< HEAD
import com.base.saas.language.LocaleMessage;
import com.base.saas.logger.LoggerCommon;
import com.base.saas.manage.domain.model.ReturnMap;
import com.base.saas.manage.domain.entity.enterprise.EntUser;
import com.base.saas.manage.service.enterprise.EntUserService;
import com.base.saas.util.ExceptionStackUtils;
import com.base.saas.userinfo.UserContextUtil;
import com.base.saas.userinfo.UserInfo;
=======
import com.base.saas.common.language.LocaleMessage;
import com.base.saas.common.logger.LoggerCommon;
import com.base.saas.manage.model.ReturnMap;
import com.base.saas.manage.model.enterprise.EntUser;
import com.base.saas.manage.service.enterprise.EntUserService;
import com.base.saas.util.response.ExceptionStackMessage;
import com.base.saas.common.userinfo.UserContextUtil;
import com.base.saas.common.userinfo.UserInfo;
>>>>>>> eb9a8c64842da76f204da857145ba23ff1c1240d
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/entUser")
<<<<<<< HEAD
@Api(tags = "用户管理")
=======
@Api(value = "用户管理")
>>>>>>> eb9a8c64842da76f204da857145ba23ff1c1240d
public class EntUserController {

    @Autowired
    private EntUserService userService;

    @Value("${spring.application.name}")
    private String serverName;

    /**
     * @Author:
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
    public ResponseEntity getUserList(@RequestParam("pageSize") Integer pageSize,
                                      @RequestParam("pageIndex") Integer pageIndex,
                                      @RequestParam(value = "account", required = false) String account,
                                      @RequestParam(value = "realName", required = false) String realName,
                                      @RequestParam(value = "tel", required = false) String tel) {
        UserInfo userInfo = UserContextUtil.getUserInfo();

        try {
            PageHelper.startPage(pageIndex, pageSize, true);
            List<EntUser> list = userService.getUserList(userInfo.getCompanyCode(), -1, account, realName, tel);
            PageInfo pageInfo = new PageInfo(list);
            return ResponseEntity.ok().body(pageInfo);
        } catch (Exception e) {
            String logmsg = LocaleMessage.get("message.query.errorMessage");
<<<<<<< HEAD
            LoggerCommon.info(this.getClass(), "获取用户列表异常：" + ExceptionStackUtils.collectExceptionStackMsg(e));
=======
            LoggerCommon.info(this.getClass(), "获取用户列表异常：" + ExceptionStackMessage.collectExceptionStackMsg(e));
>>>>>>> eb9a8c64842da76f204da857145ba23ff1c1240d
            return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(logmsg)).body(null);
        }
    }

    @PostMapping("/addUser")
    @ApiOperation(value = "添加用户", notes = "添加用户")
    public ResponseEntity addUser(@RequestBody EntUser user) {

        String logmsg = null;
        try {
            ReturnMap map = userService.addUser(user);
            boolean flag = map.getCode() == 1;
            logmsg = LocaleMessage.get(map.getMsg());
            if (flag) {
                return ResponseEntity.ok().body(null);
            } else {
                return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(logmsg)).body(null);
            }
        } catch (Exception e) {
            logmsg = LocaleMessage.get("message.system.save.fail");
<<<<<<< HEAD
            LoggerCommon.info(this.getClass(), "添加用户异常：" + ExceptionStackUtils.collectExceptionStackMsg(e));
=======
            LoggerCommon.info(this.getClass(), "添加用户异常：" + ExceptionStackMessage.collectExceptionStackMsg(e));
>>>>>>> eb9a8c64842da76f204da857145ba23ff1c1240d
            return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(logmsg)).body(null);
        }
    }


    @PostMapping("/updateStatus")
    @ApiOperation(value = "用户启用停用", notes = "用户启用停用")
    public ResponseEntity updateStatus(@RequestBody EntUser data) {
        String logmsg = LocaleMessage.get("message.system.request.param.exception");
        if (null == data || StringUtil.isEmpty(data.getUserId())) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(logmsg)).body(null);
        }
        try {
            ReturnMap map = userService.updateState(data);
            boolean flag = map.getCode() == 1;
            logmsg = LocaleMessage.get(map.getMsg());
            if (flag) {
                return ResponseEntity.ok().body(null);
            } else {
                return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(logmsg)).body(null);
            }
        } catch (Exception e) {
            logmsg = LocaleMessage.get("message.system.operation.fail");
<<<<<<< HEAD
            LoggerCommon.info(this.getClass(), "用户启用停用异常：" + ExceptionStackUtils.collectExceptionStackMsg(e));
=======
            LoggerCommon.info(this.getClass(), "用户启用停用异常：" + ExceptionStackMessage.collectExceptionStackMsg(e));
>>>>>>> eb9a8c64842da76f204da857145ba23ff1c1240d
            return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(logmsg)).body(null);
        }
    }

    @PostMapping("/resetPassword")
    @ApiOperation(value = "重置密码", notes = "重置密码")
    public ResponseEntity resetPassword(@RequestBody Map data) {
        if (null == data.get("userId") || StringUtil.isEmpty(data.get("userId").toString())) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg("传参异常")).body(null);
        }
        String userId = data.get("userId").toString();

        String logmsg = null;
        try {
            ReturnMap map = userService.resetPassword(userId);
            boolean flag = map.getCode() == 1;
            logmsg = LocaleMessage.get(map.getMsg());
            if (flag) {
                return ResponseEntity.ok().body(null);
            } else {
                return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(logmsg)).body(null);
            }
        } catch (Exception e) {
            logmsg = LocaleMessage.get("message.system.operation.fail");
<<<<<<< HEAD
            LoggerCommon.info(this.getClass(), "用户重置密码异常：" + ExceptionStackUtils.collectExceptionStackMsg(e));
=======
            LoggerCommon.info(this.getClass(), "用户重置密码异常：" + ExceptionStackMessage.collectExceptionStackMsg(e));
>>>>>>> eb9a8c64842da76f204da857145ba23ff1c1240d
            return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(logmsg)).body(null);
        }
    }

    @GetMapping("/getUserById")
    @ApiOperation(value = "查询单个用户", notes = "查询单个用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", dataType = "String", paramType = "query", required = true),
    })
    public ResponseEntity getUserById(@RequestParam String userId) {
        try {
            EntUser user = userService.getUserById(userId);
            return ResponseEntity.ok().body(user);
        } catch (Exception e) {
            String logmsg = LocaleMessage.get("message.query.errorMessage");
<<<<<<< HEAD
            LoggerCommon.info(this.getClass(), "查询单个用户异常：" + ExceptionStackUtils.collectExceptionStackMsg(e));
=======
            LoggerCommon.info(this.getClass(), "查询单个用户异常：" + ExceptionStackMessage.collectExceptionStackMsg(e));
>>>>>>> eb9a8c64842da76f204da857145ba23ff1c1240d
            return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(logmsg)).body(null);
        }
    }

    @PostMapping("/updateUser")
    @ApiOperation(value = "修改用户信息", notes = "修改用户信息")
    public ResponseEntity updateUser(@RequestBody EntUser user) {

        String logmsg = null;
        try {
            ReturnMap map = userService.updateUser(user);
            boolean flag = map.getCode() == 1;
            logmsg = LocaleMessage.get(map.getMsg());
            if (flag) {
                return ResponseEntity.ok().body(null);
            } else {
                return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(logmsg)).body(null);
            }
        } catch (Exception e) {
            logmsg = LocaleMessage.get("message.system.update.fail");
<<<<<<< HEAD
            LoggerCommon.info(this.getClass(), "修改用户信息异常：" + ExceptionStackUtils.collectExceptionStackMsg(e));
=======
            LoggerCommon.info(this.getClass(), "修改用户信息异常：" + ExceptionStackMessage.collectExceptionStackMsg(e));
>>>>>>> eb9a8c64842da76f204da857145ba23ff1c1240d
            return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(logmsg)).body(null);
        }
    }

}
