package com.base.saas.system.controller;

import com.base.saas.common.language.LocaleMessage;
import com.base.saas.common.logger.LoggerCommon;
import com.base.saas.common.response.BaseResponse;
import com.base.saas.common.response.ExceptionEnum;
import com.base.saas.common.response.ExceptionStackMessage;
import com.base.saas.common.response.ResponseLogMessageHandel;
import com.base.saas.common.upload.impl.UploadFileToOss;
import com.base.saas.common.userinfo.UserContextUtil;
import com.base.saas.common.userinfo.UserInfo;
import com.base.saas.system.service.SysParaService;
import com.base.saas.util.CreateIDUtil;
import com.base.saas.util.HeaderUtil;
import com.alibaba.fastjson.JSONObject;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Title :
 * Description : @富文本编辑器@
 * Create on : 2018年06月05日
 * Copyright (C) zw.FinTec
 *
 * @author department:研发部
 *         username:chengrui
 * @version 修改历史:
 *          修改人 修改日期 修改描述
 *          -------------------------------------------<
 */
@RestController
@RequestMapping("/api/rich")
@Api(value = "/api/rich", description = "富文本编辑器")
public class RichController {

    private static final String ENTITY_NAME = "rich";

    @Autowired
    private UploadFileToOss uploadFileToOss;

    @Autowired
    private SysParaService sysParaService;

    @Autowired
    private FastFileStorageClient storageClient;

//    @RequestMapping("/toPage")
//    public String toPage() {return "SystemManage/Richtext";}

    @PostMapping("/uploadFile")
    @ApiOperation(value = "富文本编辑器上传文件", httpMethod = "POST",notes = "富文本编辑器上传文件")
    public BaseResponse uploadFile(HttpServletRequest request) {
        UserInfo userInfo = UserContextUtil.getUserInfo();
        JSONObject jsonObject = new JSONObject();
        //获取根目录
        String url = request.getSession().getServletContext().getRealPath("/fintecher_file");
        //给图片命名
        String id = CreateIDUtil.getId();
        String logmsg =null;
        try {
            //oss参数, 后面写入配置文件中
            String uploadType = sysParaService.getParaByCompanyAndKey(userInfo.getCompanyCode(), "uploadType");
            String webUrl = sysParaService.getParaByCompanyAndKey(userInfo.getCompanyCode(), "webUrl");
            String bucketName = sysParaService.getParaByCompanyAndKey(userInfo.getCompanyCode(), "oss_bucketName");
            String endpoint = sysParaService.getParaByCompanyAndKey(userInfo.getCompanyCode(), "oss_endpoint");
            String accessKeyId = sysParaService.getParaByCompanyAndKey(userInfo.getCompanyCode(), "oss_accessKeyId");
            String accessKeySecret = sysParaService.getParaByCompanyAndKey(userInfo.getCompanyCode(), "oss_accessKeySecret");
            //上传文件
            Map map = uploadFileToOss.uploadFile(request, url, id, "aaa", bucketName, endpoint, accessKeyId, accessKeySecret,uploadType,storageClient,webUrl);
            Map<String,Object>resultMap= new HashMap();
            if (null != map && map.size() > 0) {
                List<Map> fileList = (List<Map>) map.get("fileList");
                resultMap.put("error", 0);
                resultMap.put("url", fileList);
                return BaseResponse.ok().body(ResponseLogMessageHandel.initSuccessResponseData(resultMap));
            } else {
                logmsg=LocaleMessage.get("message.upload.error");
                return BaseResponse.badRequest().headers(HeaderUtil.createAlert(logmsg,ENTITY_NAME)).body(null);
            }
        }catch (Exception e){
            logmsg = LocaleMessage.get("message.upload.error");
            LoggerCommon.info(this.getClass(),"富文本编辑器上传文件异常："+ExceptionStackMessage.collectExceptionStackMsg(e));
            return BaseResponse.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME,"system.server.exception",
                    logmsg)).body(ResponseLogMessageHandel.initResponseData(HttpStatus.BAD_REQUEST.value(), ExceptionEnum.EXCEPTION_CODE.code(),
                    LocaleMessage.get("system.server.exception"), ExceptionStackMessage.collectExceptionStackMsg(e),null));
        }

    }

    @PostMapping("/upload")
    @ApiOperation(value = "上传文件", httpMethod = "POST", notes = "上传文件")
    public BaseResponse upload(HttpServletRequest request) {
        UserInfo userInfo = UserContextUtil.getUserInfo();
        //获取根目录
        String url = request.getSession().getServletContext().getRealPath("/fintecher_file");
        //给图片命名
        String id = CreateIDUtil.getId();
        //oss参数, 后面写入配置文件中
        String logmsg =null;
        Map map = new HashMap();
        try {
            String uploadType = sysParaService.getParaByCompanyAndKey(userInfo.getCompanyCode(), "uploadType");
            String webUrl = sysParaService.getParaByCompanyAndKey(userInfo.getCompanyCode(), "webUrl");
            String bucketName = sysParaService.getParaByCompanyAndKey(userInfo.getCompanyCode(), "oss_bucketName");
            String endpoint = sysParaService.getParaByCompanyAndKey(userInfo.getCompanyCode(), "oss_endpoint");
            String accessKeyId = sysParaService.getParaByCompanyAndKey(userInfo.getCompanyCode(), "oss_accessKeyId");
            String accessKeySecret = sysParaService.getParaByCompanyAndKey(userInfo.getCompanyCode(), "oss_accessKeySecret");
            //上传文件
            map = uploadFileToOss.uploadFile(request, url, id, "hlt", bucketName, endpoint, accessKeyId, accessKeySecret,uploadType,storageClient,webUrl);
            return BaseResponse.ok().body(ResponseLogMessageHandel.initSuccessResponseData(map));
        }catch (Exception e){
            logmsg = LocaleMessage.get("message.upload.error");
            LoggerCommon.info(this.getClass(),"上传文件异常："+ExceptionStackMessage.collectExceptionStackMsg(e));
            return BaseResponse.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME,"system.server.exception",
                    logmsg)).body(ResponseLogMessageHandel.initResponseData(HttpStatus.BAD_REQUEST.value(), ExceptionEnum.EXCEPTION_CODE.code(),
                    LocaleMessage.get("system.server.exception"), ExceptionStackMessage.collectExceptionStackMsg(e),null));
        }
    }

}
