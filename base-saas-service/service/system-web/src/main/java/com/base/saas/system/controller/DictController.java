package com.base.saas.system.controller;

import com.base.saas.common.language.LocaleMessage;
import com.base.saas.common.logger.LoggerCommon;
import com.base.saas.common.response.BaseResponse;
import com.base.saas.common.response.ExceptionEnum;
import com.base.saas.common.response.ExceptionStackMessage;
import com.base.saas.common.response.ResponseLogMessageHandel;
import com.base.saas.common.userinfo.UserContextUtil;
import com.base.saas.common.userinfo.UserInfo;
import com.base.saas.system.model.DictDetail;
import com.base.saas.system.model.DictItem;
import com.base.saas.system.service.DictDetailService;
import com.base.saas.system.service.DictItemService;
import com.base.saas.system.start.DictCacheLoader;
import com.base.saas.util.HeaderUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Title :
 * Description : @字典管理@
 * Create on : 2018年05月21日
 * Copyright (C) zw.FinTec
 *
 * @author department:研发部
 *         username:hanxiaoxue
 * @version 修改历史:
 *          修改人 修改日期 修改描述
 *          -------------------------------------------<
 */
@RestController
@RequestMapping("/api/dict")
@Api(value = "/api/dict", description = "数据字典管理")
public class DictController {

    private static final String ENTITY_NAME = "dict";

    private static final Logger logger = LoggerFactory.getLogger(DictController.class);

    @Resource
    private DictDetailService dictDetailService;

    @Resource
    private DictItemService dictItemService;

//    @Autowired
//    @Qualifier("dictCacheLoader")
//    private AbsCacheLoader absCacheLoader;

    @Autowired
    @Qualifier("dictCacheLoader")
    private DictCacheLoader dictCacheLoader;

//    @GetMapping("/toPage")
//    public String toPage(){
//        return "DictionariesManage";
//    }
//

    /**
     * 查询数据字典大类数据列表
     * @param pageSize
     * @param pageIndex
     * @param itemName
     * @param itemCode
     * @return
     */
    @ApiOperation(value = "查询大类数据列表", notes = "查询大类数据列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize", value = "分页大小", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "pageIndex", value = "当前页码", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "itemName", value = "字典大类名称", dataType = "String", paramType = "query", required = false),
            @ApiImplicitParam(name = "itemCode", value = "字典大类编码", dataType = "String", paramType = "query", required = false),
    })
    @GetMapping("/getDictItemList")
    public BaseResponse getDictItemList(@RequestParam(value = "pageSize") Integer pageSize,
                                        @RequestParam(value = "pageIndex") Integer pageIndex,
                                        @RequestParam(value = "itemName",required = false) String itemName,
                                        @RequestParam(value = "itemCode",required = false) String itemCode){
        LoggerCommon.info(this.getClass(),"查询大类数据列表");
        UserInfo userInfo = UserContextUtil.getUserInfo();
        Map param = new HashMap();
        param.put("itemCode",itemCode);
        param.put("itemName",itemName);
        param.put("companyCode",userInfo.getCompanyCode());//企业编码
        PageHelper.startPage(pageIndex, pageSize, true);
        try {
            List<DictItem> dictItemList = dictItemService.getDictItemList(param);
            PageInfo<DictItem> pageInfo = new PageInfo<>(dictItemList);
            LoggerCommon.info(this.getClass(),"成功查询大类数据列表数据");
            return BaseResponse.ok().body(ResponseLogMessageHandel.initSuccessResponseData(pageInfo));
        }catch (Exception e){
            String logmsg = LocaleMessage.get("message.query.errorMessage");
            LoggerCommon.info(this.getClass(),"查询大类数据列表异常："+ ExceptionStackMessage.collectExceptionStackMsg(e));
            return BaseResponse.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME,"system.server.exception",
                    logmsg)).body(ResponseLogMessageHandel.initResponseData(HttpStatus.BAD_REQUEST.value(), ExceptionEnum.EXCEPTION_CODE.code(),
                    LocaleMessage.get("system.server.exception"), ExceptionStackMessage.collectExceptionStackMsg(e),null));
        }

    }


    /**
     * 查询数据字典明细列表
     * @param pageSize
     * @param pageIndex
     * @param itemCode
     * @return
     */
    @ApiOperation(value = "查询明细数据列表", notes = "查询明细数据列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize", value = "分页大小", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "pageIndex", value = "当前页码", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "itemCode", value = "字典大类编码", dataType = "String", paramType = "query", required = true),
    })
    @GetMapping("/getDictDetailList")
    public BaseResponse getDictDetailList(@RequestParam(value = "pageSize") Integer pageSize,
                                            @RequestParam(value = "pageIndex") Integer pageIndex,
                                            @RequestParam(value = "itemCode") String itemCode){
        UserInfo userInfo = UserContextUtil.getUserInfo();
        Map param = new HashMap();
        param.put("itemCode",itemCode);
        param.put("companyCode",userInfo.getCompanyCode());//企业编码
        PageHelper.startPage(pageIndex, pageSize, true);
        try {
            List<DictDetail> dictDetailList = dictDetailService.getDictDetailList(param);
            PageInfo<DictDetail> pageInfo = new PageInfo<>(dictDetailList);
            return BaseResponse.ok().body(ResponseLogMessageHandel.initSuccessResponseData(pageInfo));
        }catch (Exception e){
            String logmsg = LocaleMessage.get("message.query.errorMessage");
            LoggerCommon.info(this.getClass(),"查询明细数据列表异常："+ExceptionStackMessage.collectExceptionStackMsg(e));
            return BaseResponse.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME,"system.server.exception",
                    logmsg)).body(ResponseLogMessageHandel.initResponseData(HttpStatus.BAD_REQUEST.value(), ExceptionEnum.EXCEPTION_CODE.code(),
                    LocaleMessage.get("system.server.exception"), ExceptionStackMessage.collectExceptionStackMsg(e),null));
        }
    }

    /**
     *修改数据大类状态
     * @param data {id:,status:}
     * @return
     */
    @ApiOperation(value = "修改字典大类状态", notes = "修改字典大类状态")
    @PostMapping("/updateDictItemStatus")
    public BaseResponse updateDictItemStatus(@RequestBody Map data){
        if(null==data.get("id")||StringUtil.isEmpty(data.get("id").toString())){
            return BaseResponse.badRequest().header("传参异常",ENTITY_NAME).body(null);
        }
        if(null==data.get("status")||StringUtil.isEmpty(data.get("status").toString())){
            return BaseResponse.badRequest().header("传参异常",ENTITY_NAME).body(null);
        }
        UserInfo userInfo = UserContextUtil.getUserInfo();
        DictItem dictItem = new DictItem();
        dictItem.setId(data.get("id").toString());
        dictItem.setStatus(Short.valueOf(data.get("status").toString()));
        dictItem.setUpdateUser(userInfo.getAccount());
        String logmsg = null;
        try {
            Map repMap =dictItemService.updateDictItemStatus(dictItem);
            boolean flag = (boolean) repMap.get("flag");
            logmsg =LocaleMessage.get( repMap.get("msg").toString());
            if(flag){
                //刷新缓存
                dictCacheLoader.refresh();
                LoggerCommon.info(this.getClass(),"修改字典大类状态成功");
                return BaseResponse.ok().headers(HeaderUtil.createAlert(logmsg,ENTITY_NAME)).body(null);
            }else{
                LoggerCommon.info(this.getClass(),"修改字典大类状态失败:"+logmsg);
                return BaseResponse.badRequest().headers(HeaderUtil.createAlert(logmsg,ENTITY_NAME)).body(null);
            }
        }catch (Exception e){
            logmsg = LocaleMessage.get("message.system.operation.fail");
            LoggerCommon.info(this.getClass(),"修改字典大类状态异常："+ExceptionStackMessage.collectExceptionStackMsg(e));
            return BaseResponse.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME,"system.server.exception",
                    logmsg)).body(ResponseLogMessageHandel.initResponseData(HttpStatus.BAD_REQUEST.value(), ExceptionEnum.EXCEPTION_CODE.code(),
                    LocaleMessage.get("system.server.exception"), ExceptionStackMessage.collectExceptionStackMsg(e),null));
        }

    }


    /**
     * 根据id获取字典大类信息
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id获取字典大类信息", httpMethod = "GET", notes = "根据id获取字典大类信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "字典大类id", dataType = "String", paramType = "query", required = true)
    })
    @GetMapping("/getDictItemById")
    public BaseResponse getDictItemById(@RequestParam("id") String id){
        try {
            DictItem dictItem = dictItemService.getDictItemById(id);
            return BaseResponse.ok().body(ResponseLogMessageHandel.initSuccessResponseData(dictItem));
        }catch (Exception e){
            String logmsg = LocaleMessage.get("message.query.errorMessage");
            LoggerCommon.info(this.getClass(),"根据id获取字典大类信息异常："+ExceptionStackMessage.collectExceptionStackMsg(e));
            return BaseResponse.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME,"system.server.exception",
                    logmsg)).body(ResponseLogMessageHandel.initResponseData(HttpStatus.BAD_REQUEST.value(), ExceptionEnum.EXCEPTION_CODE.code(),
                    LocaleMessage.get("system.server.exception"), ExceptionStackMessage.collectExceptionStackMsg(e),null));
        }
    }

    /**
     *保存字典大类
     * @param dictItem
     * @return
     */
    @ApiOperation(value = "保存字典大类", httpMethod = "POST", notes = "保存字典大类")
    @PostMapping("/saveDictItem")
    public BaseResponse saveDictItem(@RequestBody DictItem dictItem){
        UserInfo userInfo = UserContextUtil.getUserInfo();
        dictItem.setCreateUser(userInfo.getAccount());//创建人
        dictItem.setCompanyCode(userInfo.getCompanyCode());
        String logmsg = null ;
        try{
            Map map = dictItemService.insertDictItem(dictItem);//保存
            boolean flag= (boolean) map.get("flag");
            logmsg= LocaleMessage.get(map.get("msg").toString());
            if(flag){//保存成功
                //刷新缓存
                dictCacheLoader.refresh();
                LoggerCommon.info(this.getClass(),"保存字典大类失败:"+logmsg);
                return BaseResponse.ok().headers(HeaderUtil.createAlert(logmsg,ENTITY_NAME)).body(null);
            }else{
                LoggerCommon.info(this.getClass(),"保存字典大类失败:"+logmsg);
                return BaseResponse.badRequest().headers(HeaderUtil.createAlert(logmsg,ENTITY_NAME)).body(null);
            }
        }catch (Exception e){
            logmsg = LocaleMessage.get("message.system.save.fail");
            LoggerCommon.info(this.getClass(),"保存字典大类异常："+ExceptionStackMessage.collectExceptionStackMsg(e));
            return BaseResponse.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME,"system.server.exception",
                    logmsg)).body(ResponseLogMessageHandel.initResponseData(HttpStatus.BAD_REQUEST.value(), ExceptionEnum.EXCEPTION_CODE.code(),
                    LocaleMessage.get("system.server.exception"), ExceptionStackMessage.collectExceptionStackMsg(e),null));
        }

    }

    /**
     * 修改字典大类
     * @return
     */
    @ApiOperation(value = "修改字典大类", httpMethod = "POST", notes = "修改字典大类")
    @PostMapping("/updateDictItem")
    public BaseResponse updateDictItem(@RequestBody DictItem dictItem){
        dictItem.setUpdateUser(UserContextUtil.getUserInfo().getRealName());//修改人
        String logmsg=null;
        try {
            Map map = dictItemService.updateDictItem(dictItem);//修改
            boolean flag = (boolean) map.get("flag");
            logmsg=LocaleMessage.get(map.get("msg").toString());
            if (flag) {
                //刷新缓存
                dictCacheLoader.refresh();
                LoggerCommon.info(this.getClass(),"修改字典大类状态成功");
                return BaseResponse.ok().headers(HeaderUtil.createAlert(logmsg, ENTITY_NAME)).body(null);
            } else {
                LoggerCommon.info(this.getClass(),"修改字典大类失败:"+logmsg);
                return BaseResponse.badRequest().headers(HeaderUtil.createAlert(logmsg, ENTITY_NAME)).body(null);
            }
        }catch (Exception e){
            logmsg = LocaleMessage.get("message.system.update.fail");
            LoggerCommon.info(this.getClass(),"修改字典大类异常："+ExceptionStackMessage.collectExceptionStackMsg(e));
            return BaseResponse.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME,"system.server.exception",
                    logmsg)).body(ResponseLogMessageHandel.initResponseData(HttpStatus.BAD_REQUEST.value(), ExceptionEnum.EXCEPTION_CODE.code(),
                    LocaleMessage.get("system.server.exception"), ExceptionStackMessage.collectExceptionStackMsg(e),null));
        }
    }



    /**
     * 修改字典明细状态
     * @param data {id:,status:}
     * @return
     */
    @ApiOperation(value = "修改字典明细状态", httpMethod = "POST", notes = "修改字典明细状态")
    @PostMapping("/updateDictDetailStatus")
    public BaseResponse updateDictDetailStatus(@RequestBody Map data){
        String logmsg=LocaleMessage.get("message.system.request.param.exception");
        if(null==data.get("id")||StringUtil.isEmpty(data.get("id").toString())){
            return BaseResponse.badRequest().headers(HeaderUtil.createAlert(logmsg,ENTITY_NAME)).body(null);
        }
        if(null==data.get("status")||StringUtil.isEmpty(data.get("status").toString())){
            return BaseResponse.badRequest().headers(HeaderUtil.createAlert(logmsg,ENTITY_NAME)).body(null);
        }
        UserInfo userInfo = UserContextUtil.getUserInfo();
        DictDetail dictDetail = new DictDetail();
        dictDetail.setUpdateUser(userInfo.getAccount());
        dictDetail.setId(data.get("id").toString());
        dictDetail.setStatus(Short.parseShort(data.get("status").toString()));
        try {
            Map map = dictDetailService.updateDictDetailStatus(dictDetail);
            boolean flag = (boolean) map.get("flag");
            logmsg=LocaleMessage.get(map.get("msg").toString());
            if(flag){
                //刷新缓存
                dictCacheLoader.refresh();
                LoggerCommon.info(this.getClass(),"修改字典明细状态成功");
                return BaseResponse.ok().headers(HeaderUtil.createAlert(logmsg,ENTITY_NAME)).body(null);
            }else{
                LoggerCommon.info(this.getClass(),"修改字典明细状态失败："+logmsg);
                return BaseResponse.badRequest().headers(HeaderUtil.createAlert(logmsg,ENTITY_NAME)).body(null);
            }
        }catch (Exception e){
            logmsg = LocaleMessage.get("message.system.update.fail");
            LoggerCommon.info(this.getClass(),"修改字典明细状态异常："+ExceptionStackMessage.collectExceptionStackMsg(e));
            return BaseResponse.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME,"system.server.exception",
                    logmsg)).body(ResponseLogMessageHandel.initResponseData(HttpStatus.BAD_REQUEST.value(), ExceptionEnum.EXCEPTION_CODE.code(),
                    LocaleMessage.get("system.server.exception"), ExceptionStackMessage.collectExceptionStackMsg(e),null));
        }

    }
    /**
     * 获取字典明细
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id查询字典明细", httpMethod = "GET", notes = "根据id查询字典明细")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "字典明细Id", dataType = "String", paramType = "query", required = true)
    })
    @GetMapping("/getDictDetailById")
    public BaseResponse getDictDetailById(@RequestParam("id") String id){
        try {
            Map dictDetail = dictDetailService.getDictDetailById(id);
            return BaseResponse.ok().body(ResponseLogMessageHandel.initSuccessResponseData(dictDetail));
        }catch (Exception e){
            String logmsg = LocaleMessage.get("message.query.errorMessage");
            LoggerCommon.info(this.getClass(),"根据id查询字典明细异常："+ExceptionStackMessage.collectExceptionStackMsg(e));
            return BaseResponse.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME,"system.server.exception",
                    logmsg)).body(ResponseLogMessageHandel.initResponseData(HttpStatus.BAD_REQUEST.value(), ExceptionEnum.EXCEPTION_CODE.code(),
                    LocaleMessage.get("system.server.exception"), ExceptionStackMessage.collectExceptionStackMsg(e),null));
        }
    }


    /**
     * 保存字典明细
     * @return
     */
    @ApiOperation(value = "保存字典明细", httpMethod = "POST", notes = "保存字典明细")
    @PostMapping("/saveDictDetail")
    public BaseResponse saveDictDetail(@RequestBody DictDetail dictDetail){
        //创建人
        dictDetail.setCreateUser(UserContextUtil.getUserInfo().getRealName());
        String logmsg =null;
        try {
            //新增
            Map map = dictDetailService.insertDictDetail(dictDetail);
            boolean flag = (boolean) map.get("flag");
            logmsg = LocaleMessage.get(map.get("msg").toString());
            if (flag) {
                //刷新缓存
                dictCacheLoader.refresh();
                LoggerCommon.info(this.getClass(),"保存字典明细成功");
                return BaseResponse.ok().headers(HeaderUtil.createAlert(logmsg, ENTITY_NAME)).body(null);
            } else {
                LoggerCommon.info(this.getClass(),"保存字典明细失败："+logmsg);
                return BaseResponse.badRequest().headers(HeaderUtil.createAlert(logmsg, ENTITY_NAME)).body(null);
            }
        }catch (Exception e){
            logmsg = LocaleMessage.get("message.system.save.fail");
            LoggerCommon.info(this.getClass(),"保存字典明细异常："+ExceptionStackMessage.collectExceptionStackMsg(e));
            return BaseResponse.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME,"system.server.exception",
                    logmsg)).body(ResponseLogMessageHandel.initResponseData(HttpStatus.BAD_REQUEST.value(), ExceptionEnum.EXCEPTION_CODE.code(),
                    LocaleMessage.get("system.server.exception"), ExceptionStackMessage.collectExceptionStackMsg(e),null));
        }
    }

    /***
     * 修改字典明细
     * @param dictDetail
     * @return
     */
    @ApiOperation(value = "修改字典明细", httpMethod = "POST", notes = "修改字典明细")
    @PostMapping("/updateDictDetail")
    public BaseResponse updateDictDetail(@RequestBody DictDetail dictDetail){
        //修改人
        dictDetail.setUpdateUser(UserContextUtil.getUserInfo().getRealName());
        String logmsg =null;
        try {
            //修改
            Map map = dictDetailService.updateDictDetail(dictDetail);
            boolean flag = (boolean) map.get("flag");
            logmsg=LocaleMessage.get(map.get("msg").toString());
            if (flag) {
                //刷新缓存
                dictCacheLoader.refresh();
                LoggerCommon.info(this.getClass(),"修改字典明细成功");
                return BaseResponse.ok().headers(HeaderUtil.createAlert(logmsg, ENTITY_NAME)).body(null);
            } else {
                LoggerCommon.info(this.getClass(),"修改字典明细失败："+logmsg);
                return BaseResponse.badRequest().headers(HeaderUtil.createAlert(logmsg, ENTITY_NAME)).body(null);
            }
        }catch (Exception e){
            logmsg = LocaleMessage.get("message.system.update.fail");
            LoggerCommon.info(this.getClass(),"修改字典明细异常："+ExceptionStackMessage.collectExceptionStackMsg(e));
            return BaseResponse.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME,"system.server.exception",
                    logmsg)).body(ResponseLogMessageHandel.initResponseData(HttpStatus.BAD_REQUEST.value(), ExceptionEnum.EXCEPTION_CODE.code(),
                    LocaleMessage.get("system.server.exception"), ExceptionStackMessage.collectExceptionStackMsg(e),null));
        }
    }


    @ApiOperation(value = "根据字典编码查询字典明细", httpMethod = "GET", notes = "根据字典编码查询字典明细")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "itemCode", value = "数据字典编码", dataType = "String", paramType = "query", required = true)
    })
    @GetMapping("/getDictDetailByItemCode")
    public BaseResponse getDictDetailByItemCode(@RequestParam String itemCode){
        try {
            List<Map> detailList = dictDetailService.getDictByItemCode(itemCode,UserContextUtil.getUserInfo().getCompanyCode());
            return BaseResponse.ok().body(ResponseLogMessageHandel.initSuccessResponseData(detailList));
        }catch (Exception e){
            String logmsg = LocaleMessage.get("message.query.errorMessage");
            LoggerCommon.info(this.getClass(),"根据字典编码查询字典明细异常："+ExceptionStackMessage.collectExceptionStackMsg(e));
            return BaseResponse.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME,"system.server.exception",
                    logmsg)).body(ResponseLogMessageHandel.initResponseData(HttpStatus.BAD_REQUEST.value(), ExceptionEnum.EXCEPTION_CODE.code(),
                    LocaleMessage.get("system.server.exception"), ExceptionStackMessage.collectExceptionStackMsg(e),null));
        }

    }

    @ApiOperation(value = "查询多个字典明细", httpMethod = "GET", notes = "查询多个字典明细")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "itemCodes", value = "查询多个字典明细", dataType = "String", paramType = "query", required = true)
    })
    @GetMapping("/getDicDetailByItemList")
    public BaseResponse getDicDetailByItemList(@RequestParam String itemCodes){
        LoggerCommon.info(this.getClass(),"查询多个字典明细");
        String localeTipMsg = LocaleMessage.get("message.system.request.param.exception");
        if(null==itemCodes|| StringUtil.isEmpty(itemCodes)){
            return BaseResponse.badRequest().headers(HeaderUtil.createAlert(localeTipMsg, ENTITY_NAME)).body(null);
        }
        Map map = new HashMap();
        String[] codeList = itemCodes.split(",");
        try {
            for (int i = 0; i < codeList.length; i++) {
                List<Map> detailList = dictDetailService.getDictByItemCode(codeList[i],UserContextUtil.getUserInfo().getCompanyCode());
                map.put(codeList[i],detailList);
            }
            LoggerCommon.info(this.getClass(),"查询多个字典明细成功");
            return BaseResponse.ok().body(ResponseLogMessageHandel.initSuccessResponseData(map));
        } catch (Exception e) {
            String logmsg = LocaleMessage.get("message.query.errorMessage");
            LoggerCommon.info(this.getClass(),"查询多个字典明细异常："+ExceptionStackMessage.collectExceptionStackMsg(e));
            return BaseResponse.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME,"system.server.exception",
                    logmsg)).body(ResponseLogMessageHandel.initResponseData(HttpStatus.BAD_REQUEST.value(), ExceptionEnum.EXCEPTION_CODE.code(),
                    LocaleMessage.get("system.server.exception"), ExceptionStackMessage.collectExceptionStackMsg(e),null));
        }
    }

}