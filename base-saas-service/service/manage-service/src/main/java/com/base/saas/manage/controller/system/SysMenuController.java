package com.base.saas.manage.controller.system;

import com.base.saas.manage.domain.model.ReturnMap;
import com.base.saas.manage.domain.entity.system.SysMenu;
import com.base.saas.manage.service.system.SysMenuService;
import com.base.saas.language.LocaleMessage;
import com.base.saas.logger.LoggerCommon;
import com.base.saas.util.ExceptionStackUtils;
import com.base.saas.util.HeaderUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/api/sysMenu")
@Api(tags = "系统模块菜单管理")
public class SysMenuController {

    @Resource
    private SysMenuService sysModuleDetailService;

    @GetMapping("/getAllMenuList")
    @ApiOperation(value = "获取菜单列表详情", httpMethod = "GET", notes = "获取菜单列表详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "moduleId", value = "菜单所属模块id", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "parentId", value = "通过父菜单id获取其下的子菜单", dataType = "String", paramType = "query"),
    })
    public ResponseEntity getAllMenuList(@RequestParam String moduleId, @RequestParam(required = false) String parentId) {

        try {
            List<SysMenu> list = sysModuleDetailService.getAllMenuList(moduleId, parentId);
            return ResponseEntity.ok().body(list);
        } catch (Exception e) {
            String localeTipMsg = LocaleMessage.get("message.query.errorMessage");
            LoggerCommon.info(this.getClass(), "获取菜单列表详情异常：" + ExceptionStackUtils.collectExceptionStackMsg(e));
            return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(localeTipMsg)).body(null);
        }
    }

    @ApiOperation(value = "根据id查询编辑回显信息", httpMethod = "GET", notes = "根据id查询编辑回显信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "menuId", value = "平台系统菜单映射表id", dataType = "String", paramType = "query", required = true)
    })
    @GetMapping("/getMenuById")
    public ResponseEntity getMenuById(@RequestParam String menuId) {
        try {
            SysMenu moduleDetail = sysModuleDetailService.getMenuByMenuId(menuId);
            return ResponseEntity.ok().body(moduleDetail);
        } catch (Exception e) {
            String localeTipMsg = LocaleMessage.get("message.query.errorMessage");
            LoggerCommon.info(this.getClass(), "获取菜单列表详情异常：" + ExceptionStackUtils.collectExceptionStackMsg(e));
            return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(localeTipMsg)).body(null);
        }

    }

    @ApiOperation(value = "获取菜单树的集合", httpMethod = "GET", notes = "获取菜单树的集合")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "moduleId", value = "模块主键", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "parentId", value = "获取子级菜单", dataType = "String", paramType = "query", required = false)
    })
    @GetMapping("/getAllMenuTree")
    public ResponseEntity getAllMenuTree(@RequestParam String moduleId, @RequestParam String parentId) {
        try {
            List<SysMenu> list = sysModuleDetailService.getAllMenuTree(moduleId, parentId);
            return ResponseEntity.ok().body(list);
        } catch (Exception e) {
            String localeTipMsg = LocaleMessage.get("message.query.errorMessage");
            LoggerCommon.info(this.getClass(), "获取菜单树的集合异常：" + ExceptionStackUtils.collectExceptionStackMsg(e));
            return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(localeTipMsg)).body(null);
        }
    }

    /**
     * 增加菜单
     *
     * @param menu
     * @return
     */
    @PostMapping("/addMenu")
    @ApiOperation(value = "增加菜单", httpMethod = "POST", notes = "增加菜单")
    public ResponseEntity addMenu(@RequestBody SysMenu menu) {

        String localeTipMsg = null;
        try {
            ReturnMap resultMap = sysModuleDetailService.addMenu(menu);
            localeTipMsg = LocaleMessage.get(resultMap.getMsg());
            if (resultMap.getCode() == 0) {
                LoggerCommon.info(this.getClass(), "增加菜单失败：" + localeTipMsg);
                return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(localeTipMsg)).body(null);
            }
            LoggerCommon.info(this.getClass(), "增加菜单成功");
            return ResponseEntity.ok().body(null);
        } catch (Exception e) {
            localeTipMsg = LocaleMessage.get("message.system.save.fail");
            LoggerCommon.info(this.getClass(), "增加菜单异常：" + ExceptionStackUtils.collectExceptionStackMsg(e));
            return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(localeTipMsg)).body(null);
        }

    }

    /**
     * 编辑菜单
     *
     * @param menu
     * @return
     */
    @PostMapping("/editMenu")
    @ApiOperation(value = "编辑菜单", httpMethod = "POST", notes = "编辑菜单")
    public ResponseEntity editMenu(@RequestBody SysMenu menu) {

        String localeTipMsg = null;
        try {
            ReturnMap resultMap = sysModuleDetailService.editMenu(menu);
            localeTipMsg = LocaleMessage.get(resultMap.getMsg());
            if (resultMap.getCode() == 0) {
                LoggerCommon.info(this.getClass(), "编辑菜单失败：" + localeTipMsg);
                return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(localeTipMsg)).body(null);
            }
            LoggerCommon.info(this.getClass(), "编辑菜单成功");
            return ResponseEntity.ok().headers(HeaderUtil.createErrorMsg(localeTipMsg)).body(null);
        } catch (Exception e) {
            localeTipMsg = LocaleMessage.get("message.system.update.fail");
            LoggerCommon.info(this.getClass(), "编辑菜单异常：" + ExceptionStackUtils.collectExceptionStackMsg(e));
            return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(localeTipMsg)).body(null);
        }
    }

    /**
     * 启用,停用菜单
     *
     * @param moduleDetail {status 状态 0,停用,1启用 id 节点id}
     * @return
     */
    @PostMapping("/updateMenuStatus")
    @ApiOperation(value = "启用,停用菜单", httpMethod = "POST", notes = "启用,停用菜单")
    public ResponseEntity updateMenuStatus(@RequestBody SysMenu moduleDetail) {

        String localeTipMsg = null;
        try {
            ReturnMap resultMap = sysModuleDetailService.updateMenuStatus(moduleDetail.getMenuId(), moduleDetail.getStatus());
            localeTipMsg = LocaleMessage.get(resultMap.getMsg());
            if (resultMap.getCode() == 0) {
                LoggerCommon.info(this.getClass(), "操作菜单状态失败：" + localeTipMsg);
                return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(localeTipMsg)).body(null);
            }
            LoggerCommon.info(this.getClass(), "操作菜单状态成功");
            return ResponseEntity.ok().body(null);
        } catch (Exception e) {
            localeTipMsg = LocaleMessage.get("message.system.update.fail");
            LoggerCommon.info(this.getClass(), "启用,停用菜单异常：" + ExceptionStackUtils.collectExceptionStackMsg(e));
            return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(localeTipMsg)).body(null);
        }
    }
}