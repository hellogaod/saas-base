package com.base.saas.manage.controller.enterprise;


import com.base.saas.language.LocaleMessage;
import com.base.saas.logger.LoggerCommon;
import com.base.saas.manage.domain.model.ReturnMap;
import com.base.saas.manage.domain.entity.enterprise.EntOrganization;
import com.base.saas.util.ExceptionStackUtils;
import com.base.saas.userinfo.UserContextUtil;

import com.base.saas.manage.service.enterprise.EntOrganizationService;
import com.base.saas.util.HeaderUtil;
import com.base.saas.util.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/api/entOrganization")
@Api(tags = "组织架构管理")
public class EntOrganizationController {
    private static final String ENTITY_NAME = "organization";

    @Autowired
    private EntOrganizationService organizationService;

    @GetMapping("/getOrgList")
    @ApiOperation(value = "组织机构数据列表", notes = "组织机构数据列表")
    public ResponseEntity getOrgList() {

        try {
            List<EntOrganization> orgList = organizationService.getOrgList(UserContextUtil.getUserInfo().getCompanyCode(), null, null, null, -1, -1);
            return ResponseEntity.ok().body(orgList);
        } catch (Exception e) {
            String localeTipMsg = LocaleMessage.get("message.query.errorMessage");

            LoggerCommon.info(this.getClass(), "查询组织机构数据列表异常：" + ExceptionStackUtils.collectExceptionStackMsg(e));

            return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(localeTipMsg)).body(null);
        }
    }

    @PostMapping("/saveOrg")
    @ApiOperation(value = "组织机构保存", notes = "组织机构保存")
    public ResponseEntity saveOrg(@RequestBody EntOrganization org) {
        if (StringUtil.isEmpty(org.getParentOrgId())) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(LocaleMessage.get("message.org.super.notExisted"))).body(null);
        }

        String localeTipMsg = null;
        try {
            ReturnMap map = organizationService.saveOrgnization(org);
            localeTipMsg = LocaleMessage.get(map.getMsg());
            boolean flag = map.getCode() == 1;
            if (flag) {
                LoggerCommon.info(this.getClass(), "保存组织机构成功");
                return ResponseEntity.ok().body(null);
            } else {
                LoggerCommon.info(this.getClass(), "保存组织机构失败：" + localeTipMsg);
                return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(localeTipMsg)).body(null);
            }
        } catch (Exception e) {
            localeTipMsg = LocaleMessage.get("message.system.save.fail");

            LoggerCommon.info(this.getClass(), "组织机构保存异常：" + ExceptionStackUtils.collectExceptionStackMsg(e));

            return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(localeTipMsg)).body(null);
        }

    }

    @GetMapping("/getOrgById")
    @ApiOperation(value = "根据id查询信息", notes = "根据id查询信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orgId", value = "机构id", dataType = "String", paramType = "query", required = true),
    })
    public ResponseEntity getOrgById(@RequestParam("orgId") String orgId) {
        try {
            return ResponseEntity.ok().body(organizationService.getOrgById(orgId));
        } catch (Exception e) {
            String localeTipMsg = LocaleMessage.get("message.query.errorMessage");

            LoggerCommon.info(this.getClass(), "根据id查询信息异常：" + ExceptionStackUtils.collectExceptionStackMsg(e));

            return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(localeTipMsg)).body(null);
        }

    }

    @PostMapping("/updateOrg")
    @ApiOperation(value = "修改组织", notes = "修改组织")
    public ResponseEntity updateOrg(@RequestBody EntOrganization org) {
        String localeTipMsg = LocaleMessage.get("message.system.request.param.exception");
        if (StringUtil.isEmpty(org.getParentOrgId())) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(localeTipMsg)).body(null);
        }

        try {
            ReturnMap map = organizationService.updateOrgnization(org);
            boolean flag = map.getCode() == 1;
            localeTipMsg = LocaleMessage.get(map.getMsg());
            if (flag) {
                LoggerCommon.info(this.getClass(), "修改组织成功");
                return ResponseEntity.ok().headers(HeaderUtil.createErrorMsg(localeTipMsg)).body(null);
            } else {
                LoggerCommon.info(this.getClass(), "修改组织失败：" + localeTipMsg);
                return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(localeTipMsg)).body(null);
            }
        } catch (Exception e) {
            localeTipMsg = LocaleMessage.get("message.system.update.fail");

            LoggerCommon.info(this.getClass(), "修改组织异常：" + ExceptionStackUtils.collectExceptionStackMsg(e));

            return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(localeTipMsg)).body(null);
        }
    }

    @PostMapping("/updateOrgStatus")
    @ApiOperation(value = "修改状态", notes = "修改状态")
    public ResponseEntity updateOrgStatus(@RequestBody EntOrganization data) {
        String localeTipMsg = LocaleMessage.get("message.system.request.param.exception");
        if (null == data || StringUtil.isEmpty(data.getOrgId())) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(localeTipMsg)).body(null);
        }

        try {
            ReturnMap map = organizationService.updateOrgStatus(data);
            boolean flag = map.getCode() == 1;
            localeTipMsg = LocaleMessage.get(map.getMsg());
            if (flag) {
                LoggerCommon.info(this.getClass(), "修改组织机构状态成功");
                return ResponseEntity.ok().headers(HeaderUtil.createErrorMsg(localeTipMsg)).body(null);
            } else {
                LoggerCommon.info(this.getClass(), "修改组织机构状态失败：" + localeTipMsg);
                return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(localeTipMsg)).body(null);
            }
        } catch (Exception e) {
            localeTipMsg = LocaleMessage.get("message.system.operation.fail");

            LoggerCommon.info(this.getClass(), "修改组织状态异常：" + ExceptionStackUtils.collectExceptionStackMsg(e));

            return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(localeTipMsg)).body(null);
        }
    }


}