package com.base.saas.manage.controller.enterprise;/**
 * Created by win7 on 2018/5/24.
 */

import com.base.saas.common.language.LocaleMessage;
import com.base.saas.common.logger.LoggerCommon;
import com.base.saas.common.response.BaseResponse;
import com.base.saas.util.response.ExceptionStackMessage;
import com.base.saas.common.response.ResponseLogMessageHandel;
import com.base.saas.common.userinfo.UserContextUtil;
import com.base.saas.common.userinfo.UserInfo;
import com.base.saas.manage.model.UserDataPermission;
import com.base.saas.manage.service.enterprise.OrganizationService;
import com.base.saas.manage.service.UserDataPermissionService;
import com.base.saas.util.HeaderUtil;
import com.base.saas.util.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;


/**
 * Title :
 * Description : @组织架构管理@
 * Create on : 2018年05月24日
 * Copyright (C) zw.FinTec
 *
 * @author department:研发部
 * username:hanxiaoxue
 * @version 修改历史:
 * 修改人 修改日期 修改描述
 * -------------------------------------------<
 */
@RestController
@RequestMapping("/api/organization")
@Api(value = "/api/organization", description = "组织架构管理")
public class OrganizationController {
    private static final String ENTITY_NAME = "organization";
    @Resource
    private OrganizationService organizationService;

    @Autowired
    private UserDataPermissionService userDataPermissionService;

    /**
     * 数据列表
     *
     * @return
     */
    @GetMapping("/getOrgList")
    @ApiOperation(value = "组织机构数据列表", notes = "组织机构数据列表")
    public BaseResponse getOrgList() {
        Map map = new HashMap();
        map.put("companyCode", UserContextUtil.getUserInfo().getCompanyCode());
        try {
            List<EntOrganization> orgList = organizationService.getOrgList(map);
            return BaseResponse.ok().body(ResponseLogMessageHandel.initSuccessResponseData(orgList));
        } catch (Exception e) {
            String localeTipMsg = LocaleMessage.get("message.query.errorMessage");
            LoggerCommon.info(this.getClass(), "查询组织机构数据列表异常：" + ExceptionStackMessage.collectExceptionStackMsg(e));
            return BaseResponse.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "system.server.exception",
                    localeTipMsg)).body(ResponseLogMessageHandel.initResponseData(HttpStatus.BAD_REQUEST.value(), ExceptionEnum.EXCEPTION_CODE.code(),
                    LocaleMessage.get("system.server.exception"), ExceptionStackMessage.collectExceptionStackMsg(e), null));
        }
    }

    @GetMapping("/getOrgByOrgId")
    @ApiOperation(value = "用户管理数据权限中获取组织架构的菜单树", notes = "用户管理数据权限中获取组织架构的菜单树")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orgId", value = "组织架构id(用户本身的orgId)", dataType = "String", paramType = "query", required = false),
            @ApiImplicitParam(name = "userId", value = "用户id", dataType = "String", paramType = "query", required = false),
            @ApiImplicitParam(name = "menuId", value = "菜单id", dataType = "String", paramType = "query", required = false),
    })
    public BaseResponse getListByOrgId(@RequestParam(value = "orgId", required = false) String orgId,
                                       @RequestParam(value = "userId", required = false) String userId,
                                       @RequestParam(value = "menuId", required = false) String menuId) {
        //查询用户关联的数据权限
        UserDataPermission param = new UserDataPermission();
        UserDataPermission userDataPermission = null;
        String localeTipMsg = null;
        if (StringUtil.isNotEmpty(userId) && StringUtil.isNotEmpty(menuId)) {
            param.setMenuId(menuId);
            param.setUserId(userId);
            try {
                userDataPermission = userDataPermissionService.getUserDataPermisson(param);
            } catch (Exception e) {
                localeTipMsg = LocaleMessage.get("message.query.errorMessage");
                LoggerCommon.info(this.getClass(), "查询用户关联的数据权限异常：" + ExceptionStackMessage.collectExceptionStackMsg(e));
                return BaseResponse.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "system.server.exception",
                        localeTipMsg)).body(ResponseLogMessageHandel.initResponseData(HttpStatus.BAD_REQUEST.value(), ExceptionEnum.EXCEPTION_CODE.code(),
                        LocaleMessage.get("system.server.exception"), ExceptionStackMessage.collectExceptionStackMsg(e), null));
            }

        }
        List<String> orgs = new ArrayList<>();
        if (userDataPermission != null && null != userDataPermission.getOrgPath() && StringUtil.isNotEmpty(userDataPermission.getOrgPath())) {
            orgs = Arrays.asList(userDataPermission.getOrgPath().split(","));
        }
        UserInfo userInfo = UserContextUtil.getUserInfo();
        if (StringUtil.isEmpty(orgId)) {
            orgId = userInfo.getOrgId();
        }
        //查询所有的组织架构
        List<EntOrganization> list = null;
        try {
            list = organizationService.getOrgListByOrgId(orgId, userInfo.getCompanyCode());
        } catch (Exception e) {
            localeTipMsg = LocaleMessage.get("message.query.errorMessage");
            LoggerCommon.info(this.getClass(), "查询所有的组织架构异常：" + ExceptionStackMessage.collectExceptionStackMsg(e));
            return BaseResponse.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "system.server.exception",
                    localeTipMsg)).body(ResponseLogMessageHandel.initResponseData(HttpStatus.BAD_REQUEST.value(), ExceptionEnum.EXCEPTION_CODE.code(),
                    LocaleMessage.get("system.server.exception"), ExceptionStackMessage.collectExceptionStackMsg(e), null));
        }
        //勾选用户关联的数据权限
        List<Map> maps = new ArrayList<>();
        for (EntOrganization org : list) {
            Map oMap = new HashMap();
            oMap.put("orgId", org.getOrgId());
            oMap.put("orgName", org.getOrgName());
            oMap.put("parentOrgId", org.getParentOrgId());
            oMap.put("companyCode", org.getCompanyCode());
            oMap.put("orgType", org.getOrgType());
            int checked = 0;
            for (String orgPath : orgs) {
                if (orgPath.contains(org.getOrgId())) {
                    checked++;
                    break;
                }
            }
            oMap.put("checkked", checked);
            maps.add(oMap);
        }
        return BaseResponse.ok().body(ResponseLogMessageHandel.initSuccessResponseData(maps));
    }


    /**
     * 保存
     *
     * @param org
     * @return
     */
    @PostMapping("/saveOrg")
    @ApiOperation(value = "组织机构保存", notes = "组织机构保存")
    public BaseResponse saveOrg(@RequestBody EntOrganization org) {
        if (StringUtil.isEmpty(org.getParentOrgId())) {
            return BaseResponse.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "The user have logined", "上级组织不可为空")).body(null);
        }
        UserInfo userInfo = UserContextUtil.getUserInfo();
        org.setCreateUser(userInfo.getAccount());//创建人
        org.setCompanyCode(userInfo.getCompanyCode());//公司编码
        String localeTipMsg = null;
        try {
            Map map = organizationService.saveOrgnization(org);
            localeTipMsg = LocaleMessage.get(map.get("msg").toString());
            boolean flag = (boolean) map.get("flag");
            if (flag) {
                LoggerCommon.info(this.getClass(), "保存组织机构成功");
                return BaseResponse.ok().headers(HeaderUtil.createAlert(localeTipMsg, ENTITY_NAME)).body(null);
            } else {
                LoggerCommon.info(this.getClass(), "保存组织机构失败：" + localeTipMsg);
                return BaseResponse.badRequest().headers(HeaderUtil.createAlert(localeTipMsg, ENTITY_NAME)).body(null);
            }
        } catch (Exception e) {
            localeTipMsg = LocaleMessage.get("message.system.save.fail");
            LoggerCommon.info(this.getClass(), "组织机构保存异常：" + ExceptionStackMessage.collectExceptionStackMsg(e));
            return BaseResponse.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "system.server.exception",
                    localeTipMsg)).body(ResponseLogMessageHandel.initResponseData(HttpStatus.BAD_REQUEST.value(), ExceptionEnum.EXCEPTION_CODE.code(),
                    LocaleMessage.get("system.server.exception"), ExceptionStackMessage.collectExceptionStackMsg(e), null));
        }

    }


    /**
     * 根据id查询信息
     *
     * @param orgId
     * @return
     */
    @GetMapping("/getOrgById")
    @ApiOperation(value = "根据id查询信息", notes = "根据id查询信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orgId", value = "机构id", dataType = "String", paramType = "query", required = true),
    })
    public BaseResponse getOrgById(@RequestParam("orgId") String orgId) {
        try {
            return BaseResponse.ok().body(ResponseLogMessageHandel.initSuccessResponseData(organizationService.getOrgById(orgId)));
        } catch (Exception e) {
            String localeTipMsg = LocaleMessage.get("message.query.errorMessage");
            LoggerCommon.info(this.getClass(), "根据id查询信息异常：" + ExceptionStackMessage.collectExceptionStackMsg(e));
            return BaseResponse.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "system.server.exception",
                    localeTipMsg)).body(ResponseLogMessageHandel.initResponseData(HttpStatus.BAD_REQUEST.value(), ExceptionEnum.EXCEPTION_CODE.code(),
                    LocaleMessage.get("system.server.exception"), ExceptionStackMessage.collectExceptionStackMsg(e), null));
        }

    }


    /**
     * 修改组织管理
     *
     * @param org
     * @return
     */
    @PostMapping("/updateOrg")
    @ApiOperation(value = "修改组织", notes = "修改组织")
    public BaseResponse updateOrg(@RequestBody EntOrganization org) {
        String localeTipMsg = LocaleMessage.get("message.system.request.param.exception");
        if (StringUtil.isEmpty(org.getParentOrgId())) {
            return BaseResponse.badRequest().headers(HeaderUtil.createAlert(localeTipMsg, ENTITY_NAME)).body(null);
        }
        UserInfo userInfo = UserContextUtil.getUserInfo();
        org.setUpdateUser(userInfo.getAccount());//修改人
        org.setCompanyCode(userInfo.getCompanyCode());//公司编码
        try {
            Map map = organizationService.updateOrgnization(org);
            boolean flag = (boolean) map.get("flag");
            localeTipMsg = LocaleMessage.get(map.get("msg").toString());
            if (flag) {
                LoggerCommon.info(this.getClass(), "修改组织成功");
                return BaseResponse.ok().headers(HeaderUtil.createAlert(localeTipMsg, ENTITY_NAME)).body(null);
            } else {
                LoggerCommon.info(this.getClass(), "修改组织失败：" + localeTipMsg);
                return BaseResponse.badRequest().headers(HeaderUtil.createAlert(localeTipMsg, ENTITY_NAME)).body(null);
            }
        } catch (Exception e) {
            localeTipMsg = LocaleMessage.get("message.system.update.fail");
            LoggerCommon.info(this.getClass(), "修改组织异常：" + ExceptionStackMessage.collectExceptionStackMsg(e));
            return BaseResponse.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "system.server.exception",
                    localeTipMsg)).body(ResponseLogMessageHandel.initResponseData(HttpStatus.BAD_REQUEST.value(), ExceptionEnum.EXCEPTION_CODE.code(),
                    LocaleMessage.get("system.server.exception"), ExceptionStackMessage.collectExceptionStackMsg(e), null));
        }
    }


    /**
     * 修改状态
     *
     * @param
     * @param data{"orgId":,"status":}
     * @return
     */
    @PostMapping("/updateOrgStatus")
    @ApiOperation(value = "修改状态", notes = "修改状态")
    public BaseResponse updateOrgStatus(@RequestBody Map data) {
        String localeTipMsg = LocaleMessage.get("message.system.request.param.exception");
        if (null == data.get("orgId") || StringUtil.isEmpty(data.get("orgId").toString())) {
            return BaseResponse.badRequest().headers(HeaderUtil.createAlert(localeTipMsg, ENTITY_NAME)).body(null);
        }
        if (null == data.get("status") || StringUtil.isEmpty(data.get("status").toString())) {
            return BaseResponse.badRequest().headers(HeaderUtil.createAlert(localeTipMsg, ENTITY_NAME)).body(null);
        }
        UserInfo userInfo = UserContextUtil.getUserInfo();
        EntOrganization org = new EntOrganization();
        //主键
        org.setOrgId(data.get("orgId").toString());
        //状态
        org.setStatus(Short.parseShort(data.get("status").toString()));
        //修改人
        org.setUpdateUser(userInfo.getAccount());
        try {
            Map map = organizationService.updateOrgStatus(org);
            boolean flag = (boolean) map.get("flag");
            localeTipMsg = LocaleMessage.get(map.get("msg").toString());
            if (flag) {
                LoggerCommon.info(this.getClass(), "修改组织机构状态成功");
                return BaseResponse.ok().headers(HeaderUtil.createAlert(localeTipMsg, ENTITY_NAME)).body(null);
            } else {
                LoggerCommon.info(this.getClass(), "修改组织机构状态失败：" + localeTipMsg);
                return BaseResponse.badRequest().headers(HeaderUtil.createAlert(localeTipMsg, ENTITY_NAME)).body(
                        ResponseLogMessageHandel.initResponseData(HttpStatus.BAD_REQUEST.value(), ExceptionEnum.EXCEPTION_CODE.code(),
                                "修改组织机构状态失败", "修改组织机构状态失败", null));
            }
        } catch (Exception e) {
            localeTipMsg = LocaleMessage.get("message.system.operation.fail");
            LoggerCommon.info(this.getClass(), "修改组织状态异常：" + ExceptionStackMessage.collectExceptionStackMsg(e));
            return BaseResponse.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "system.server.exception",
                    localeTipMsg)).body(ResponseLogMessageHandel.initResponseData(HttpStatus.BAD_REQUEST.value(), ExceptionEnum.EXCEPTION_CODE.code(),
                    LocaleMessage.get("system.server.exception"), ExceptionStackMessage.collectExceptionStackMsg(e), null));
        }
    }

}