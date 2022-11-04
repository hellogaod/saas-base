package com.base.saas.manage.controller.enterprise;

import com.base.saas.language.LocaleMessage;
import com.base.saas.logger.LoggerCommon;
import com.base.saas.manage.domain.model.ReturnMap;
import com.base.saas.manage.domain.entity.enterprise.EntDictConfig;
import com.base.saas.manage.domain.entity.enterprise.EntDictItemConfig;
import com.base.saas.manage.start.EntDictCacheLoader;
import com.base.saas.util.ExceptionStackUtils;
import com.base.saas.userinfo.UserContextUtil;
import com.base.saas.userinfo.UserInfo;
import com.base.saas.manage.service.enterprise.EntDictItemConfigService;
import com.base.saas.manage.service.enterprise.EntDictConfigService;
import com.base.saas.util.HeaderUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/entDict")
@Api(tags = "数据字典管理")
public class EntDictConfigController {

    @Autowired
    private EntDictItemConfigService dictItemConfigService;

    @Autowired
    private EntDictConfigService dictConfigService;

    @Autowired
    @Qualifier("entDictCacheLoader")
    private EntDictCacheLoader dictCacheLoader;

    /**
     * 查询数据字典大类数据列表
     *
     * @param pageSize
     * @param pageIndex
     * @param itemName
     * @param itemCode
     * @return
     */
    @ApiOperation(value = "查询字典大类数据列表", notes = "查询大类数据列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize", value = "分页大小", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "pageIndex", value = "当前页码", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "status", value = "状态", dataType = "String", paramType = "query", required = false),
            @ApiImplicitParam(name = "itemName", value = "字典大类名称", dataType = "String", paramType = "query", required = false),
            @ApiImplicitParam(name = "itemCode", value = "字典大类编码", dataType = "String", paramType = "query", required = false),
    })
    @GetMapping("/getDictList")
    public ResponseEntity getDictList(@RequestParam(value = "pageSize") Integer pageSize,
                                      @RequestParam(value = "pageIndex") Integer pageIndex,
                                      @RequestParam(value = "status", required = false) int status,
                                      @RequestParam(value = "itemName", required = false) String itemName,
                                      @RequestParam(value = "itemCode", required = false) String itemCode) {
        LoggerCommon.info(this.getClass(), "查询大类数据列表");
        UserInfo userInfo = UserContextUtil.getUserInfo();

        PageHelper.startPage(pageIndex, pageSize, true);
        try {
            List<EntDictConfig> entDictConfigList = dictConfigService.getDictConfigList(status, userInfo.getCompanyCode(), itemCode, itemName);
            PageInfo<EntDictConfig> pageInfo = new PageInfo<>(entDictConfigList);
            LoggerCommon.info(this.getClass(), "成功查询大类数据列表数据");
            return ResponseEntity.ok().body(pageInfo);
        } catch (Exception e) {
            String logmsg = LocaleMessage.get("message.query.errorMessage");
            LoggerCommon.info(this.getClass(), "查询大类数据列表异常：" + ExceptionStackUtils.collectExceptionStackMsg(e));
            return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(logmsg)).body(null);
        }

    }

    @ApiOperation(value = "查询明细数据列表", notes = "查询明细数据列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize", value = "分页大小", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "pageIndex", value = "当前页码", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "itemCode", value = "字典大类编码", dataType = "String", paramType = "query", required = true),
    })
    @GetMapping("/getDictDetailList")
    public ResponseEntity getDictDetailList(@RequestParam(value = "pageSize") Integer pageSize,
                                            @RequestParam(value = "pageIndex") Integer pageIndex,
                                            @RequestParam(value = "itemCode") String itemCode) {
        UserInfo userInfo = UserContextUtil.getUserInfo();

        PageHelper.startPage(pageIndex, pageSize, true);
        try {
            List<EntDictItemConfig> entDictItemConfigList = dictItemConfigService.getDictItemConfigList(-1, userInfo.getCompanyCode(), itemCode, null, null);
            PageInfo<EntDictItemConfig> pageInfo = new PageInfo<>(entDictItemConfigList);
            return ResponseEntity.ok().body(pageInfo);
        } catch (Exception e) {
            String logmsg = LocaleMessage.get("message.query.errorMessage");
            LoggerCommon.info(this.getClass(), "查询明细数据列表异常：" + ExceptionStackUtils.collectExceptionStackMsg(e));
            return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(logmsg)).body(null);
        }
    }


    @ApiOperation(value = "保存字典大类", httpMethod = "POST", notes = "保存字典大类")
    @PostMapping("/saveDict")
    public ResponseEntity saveDict(@RequestBody EntDictConfig entDictConfig) {

        String logmsg = null;
        try {
            ReturnMap map = dictConfigService.insertDictConfig(entDictConfig);//保存

            logmsg = LocaleMessage.get(map.getMsg());
            if (map.getCode() == 1) {//保存成功
                //刷新缓存
                dictCacheLoader.refresh();
                LoggerCommon.info(this.getClass(), "保存字典大类失败:" + logmsg);
                return ResponseEntity.ok().body(null);
            } else {
                LoggerCommon.info(this.getClass(), "保存字典大类失败:" + logmsg);
                return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(logmsg)).body(null);
            }
        } catch (Exception e) {
            logmsg = LocaleMessage.get("message.system.save.fail");
            LoggerCommon.info(this.getClass(), "保存字典大类异常：" + ExceptionStackUtils.collectExceptionStackMsg(e));
            return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(logmsg)).body(null);
        }

    }

    @ApiOperation(value = "修改字典大类状态", notes = "修改字典大类状态")
    @PostMapping("/updateDictStatus")
    public ResponseEntity updateDictStatus(@RequestBody EntDictConfig entDictConfig) {

        String logmsg = null;
        try {
            ReturnMap repMap = dictConfigService.updateDictConfigStatus(entDictConfig);

            logmsg = LocaleMessage.get(repMap.getMsg());
            if (repMap.getCode() == 1) {
                //刷新缓存
                dictCacheLoader.refresh();
                LoggerCommon.info(this.getClass(), "修改字典大类状态成功");
                return ResponseEntity.ok().body(null);
            } else {
                LoggerCommon.info(this.getClass(), "修改字典大类状态失败:" + logmsg);
                return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(logmsg)).body(null);
            }
        } catch (Exception e) {
            logmsg = LocaleMessage.get("message.system.operation.fail");
            LoggerCommon.info(this.getClass(), "修改字典大类状态异常：" + ExceptionStackUtils.collectExceptionStackMsg(e));
            return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(logmsg)).body(null);
        }

    }

    @ApiOperation(value = "根据id获取字典大类信息", httpMethod = "GET", notes = "根据id获取字典大类信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "字典大类id", dataType = "String", paramType = "query", required = true)
    })
    @GetMapping("/getDictById")
    public ResponseEntity getDictById(@RequestParam("id") String id) {
        try {
            EntDictConfig entDictConfig = dictConfigService.getDictConfigById(id);
            return ResponseEntity.ok().body(entDictConfig);
        } catch (Exception e) {
            String logmsg = LocaleMessage.get("message.query.errorMessage");
            LoggerCommon.info(this.getClass(), "根据id获取字典大类信息异常：" + ExceptionStackUtils.collectExceptionStackMsg(e));
            return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(logmsg)).body(null);
        }
    }


    /**
     * 修改字典大类
     *
     * @return
     */
    @ApiOperation(value = "修改字典大类", httpMethod = "POST", notes = "修改字典大类")
    @PostMapping("/updateDict")
    public ResponseEntity updateDict(@RequestBody EntDictConfig entDictConfig) {
        entDictConfig.setUpdateUser(UserContextUtil.getUserInfo().getRealName());//修改人
        String logmsg = null;
        try {
            ReturnMap map = dictConfigService.updateDictConfig(entDictConfig);//修改

            logmsg = LocaleMessage.get(map.getMsg());
            if (map.getCode() == 1) {
                //刷新缓存
                dictCacheLoader.refresh();
                LoggerCommon.info(this.getClass(), "修改字典大类状态成功");
                return ResponseEntity.ok().body(null);
            } else {
                LoggerCommon.info(this.getClass(), "修改字典大类失败:" + logmsg);
                return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(logmsg)).body(null);
            }
        } catch (Exception e) {
            logmsg = LocaleMessage.get("message.system.update.fail");
            LoggerCommon.info(this.getClass(), "修改字典大类异常：" + ExceptionStackUtils.collectExceptionStackMsg(e));
            return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(logmsg)).body(null);
        }
    }

    /**
     * 保存字典明细
     *
     * @return
     */
    @ApiOperation(value = "保存字典明细", httpMethod = "POST", notes = "保存字典明细")
    @PostMapping("/saveDictDetail")
    public ResponseEntity saveDictDetail(@RequestBody EntDictItemConfig entDictItemConfig) {

        String logmsg = null;
        try {
            //新增
            ReturnMap map = dictItemConfigService.insertDictItemConfig(entDictItemConfig);

            logmsg = LocaleMessage.get(map.getMsg());
            if (map.getCode() == 1) {
                //刷新缓存
                dictCacheLoader.refresh();
                LoggerCommon.info(this.getClass(), "保存字典明细成功");
                return ResponseEntity.ok().body(null);
            } else {
                LoggerCommon.info(this.getClass(), "保存字典明细失败：" + logmsg);
                return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(logmsg)).body(null);
            }
        } catch (Exception e) {
            logmsg = LocaleMessage.get("message.system.save.fail");
            LoggerCommon.info(this.getClass(), "保存字典明细异常：" + ExceptionStackUtils.collectExceptionStackMsg(e));
            return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(logmsg)).body(null);
        }
    }

    @ApiOperation(value = "修改字典明细状态", httpMethod = "POST", notes = "修改字典明细状态")
    @PostMapping("/updateDictDetailStatus")
    public ResponseEntity updateDictDetailStatus(@RequestBody EntDictItemConfig data) {
        String logmsg = LocaleMessage.get("message.system.request.param.exception");
        if (data == null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(logmsg)).body(null);
        }

        try {
            ReturnMap map = dictItemConfigService.updateDictItemConfigStatus(data);

            logmsg = LocaleMessage.get(map.getMsg());
            if (map.getCode() == 1) {
                //刷新缓存
                dictCacheLoader.refresh();
                LoggerCommon.info(this.getClass(), "修改字典明细状态成功");
                return ResponseEntity.ok().body(null);
            } else {
                LoggerCommon.info(this.getClass(), "修改字典明细状态失败：" + logmsg);
                return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(logmsg)).body(null);
            }
        } catch (Exception e) {
            logmsg = LocaleMessage.get("message.system.update.fail");
            LoggerCommon.info(this.getClass(), "修改字典明细状态异常：" + ExceptionStackUtils.collectExceptionStackMsg(e));
            return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(logmsg)).body(null);
        }

    }

    @ApiOperation(value = "根据id查询字典明细", httpMethod = "GET", notes = "根据id查询字典明细")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "字典明细Id", dataType = "String", paramType = "query", required = true)
    })
    @GetMapping("/getDictDetailById")
    public ResponseEntity getDictDetailById(@RequestParam("id") String id) {
        try {
            EntDictItemConfig dictDetail = dictItemConfigService.getDictItemConfigById(id);
            return ResponseEntity.ok().body(dictDetail);
        } catch (Exception e) {
            String logmsg = LocaleMessage.get("message.query.errorMessage");
            LoggerCommon.info(this.getClass(), "根据id查询字典明细异常：" + ExceptionStackUtils.collectExceptionStackMsg(e));
            return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(logmsg)).body(null);
        }
    }

    @ApiOperation(value = "修改字典明细", httpMethod = "POST", notes = "修改字典明细")
    @PostMapping("/updateDictDetail")
    public ResponseEntity updateDictDetail(@RequestBody EntDictItemConfig entDictItemConfig) {

        String logmsg = null;
        try {
            //修改
            ReturnMap map = dictItemConfigService.updateDictItemConfig(entDictItemConfig);

            logmsg = LocaleMessage.get(map.getMsg());
            if (map.getCode() == 1) {
                //刷新缓存
                dictCacheLoader.refresh();
                LoggerCommon.info(this.getClass(), "修改字典明细成功");
                return ResponseEntity.ok().body(null);
            } else {
                LoggerCommon.info(this.getClass(), "修改字典明细失败：" + logmsg);
                return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(logmsg)).body(null);
            }
        } catch (Exception e) {
            logmsg = LocaleMessage.get("message.system.update.fail");
            LoggerCommon.info(this.getClass(), "修改字典明细异常：" + ExceptionStackUtils.collectExceptionStackMsg(e));
            return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(logmsg)).body(null);
        }
    }


    @ApiOperation(value = "根据字典编码查询字典明细", httpMethod = "GET", notes = "根据字典编码查询字典明细")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "itemCode", value = "数据字典编码", dataType = "String", paramType = "query", required = true)
    })
    @GetMapping("/getDictDetailByItemCode")
    public ResponseEntity getDictDetailByItemCode(@RequestParam String itemCode) {
        try {
            List<Map> detailList = dictItemConfigService.getDictByItemCode(itemCode, UserContextUtil.getUserInfo().getCompanyCode());
            return ResponseEntity.ok().body(detailList);
        } catch (Exception e) {
            String logmsg = LocaleMessage.get("message.query.errorMessage");
            LoggerCommon.info(this.getClass(), "根据字典编码查询字典明细异常：" + ExceptionStackUtils.collectExceptionStackMsg(e));
            return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(logmsg)).body(null);
        }

    }

    @ApiOperation(value = "查询多个字典明细", httpMethod = "GET", notes = "查询多个字典明细")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "itemCodes", value = "查询多个字典明细", dataType = "String", paramType = "query", required = true)
    })
    @GetMapping("/getDicDetailByItemList")
    public ResponseEntity getDicDetailByItemList(@RequestParam String itemCodes) {
        LoggerCommon.info(this.getClass(), "查询多个字典明细");
        String localeTipMsg = LocaleMessage.get("message.system.request.param.exception");
        if (null == itemCodes || StringUtil.isEmpty(itemCodes)) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(localeTipMsg)).body(null);
        }
        Map map = new HashMap();
        String[] codeList = itemCodes.split(",");
        try {
            for (int i = 0; i < codeList.length; i++) {
                List<Map> detailList = dictItemConfigService.getDictByItemCode(codeList[i], UserContextUtil.getUserInfo().getCompanyCode());
                map.put(codeList[i], detailList);
            }
            LoggerCommon.info(this.getClass(), "查询多个字典明细成功");
            return ResponseEntity.ok().body(map);
        } catch (Exception e) {
            String logmsg = LocaleMessage.get("message.query.errorMessage");
            LoggerCommon.info(this.getClass(), "查询多个字典明细异常：" + ExceptionStackUtils.collectExceptionStackMsg(e));
            return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg(logmsg)).body(null);
        }
    }

}