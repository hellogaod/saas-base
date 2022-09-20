package com.base.saas.common.upload.impl;

import com.alibaba.fastjson.JSONObject;
import com.base.saas.common.log.TraceLogUtil;
import com.base.saas.common.upload.UploadFileService;
import com.base.saas.util.uploadfile.util.UploadFileToOssUtil;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Title :
 * Description : @阿里云上传下载@
 * Create on : 2018年06月04日
 * Copyright (C) zw.FinTec
 *
 * @author department:研发部
 *         username:chengrui
 * @version 修改历史:
 *          修改人 修改日期 修改描述
 *          -------------------------------------------<
 */
@Slf4j
@Service
public class UploadFileToOss implements UploadFileService {

    @Override
    public Map getFileUrlByUploadType(Object... objs) {
        Map map = new HashMap();
        try {
            map = UploadFileToOssUtil.getFileUrlByUploadType((HttpServletRequest) objs[0], String.valueOf(objs[1]), String.valueOf(objs[2]), String.valueOf(objs[3]),String.valueOf(objs[4]), String.valueOf(objs[5]), String.valueOf(objs[6]), String.valueOf(objs[7]), String.valueOf(objs[8]),(FastFileStorageClient)objs[9],String.valueOf(objs[10]), (MultipartFile) objs[11]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @Override
    public Map uploadFile(Object... objs) {
        Map map = new HashMap();
        try {
            map = UploadFileToOssUtil.getFileOss((HttpServletRequest) objs[0], String.valueOf(objs[1]), String.valueOf(objs[2]), String.valueOf(objs[3]),String.valueOf(objs[4]), String.valueOf(objs[5]), String.valueOf(objs[6]), String.valueOf(objs[7]), String.valueOf(objs[8]),(FastFileStorageClient)objs[9],String.valueOf(objs[10]));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @Override
    public String downloadFile(Object... objs) {
        String fileName = UploadFileToOssUtil.saveUrlAs(String.valueOf(objs[0]), String.valueOf(objs[1]), String.valueOf(objs[2]), String.valueOf(objs[3]));
        return fileName;
    }

    @Override
    public String dxUpload(Map dxMap,File file, MultipartFile multipart,String type,String dxUrl) {
        String imgId = "";
        try{
//            dxMap.put("requestSystem", AppConstant.CT_REQUEST_SYSTEM);
            TraceLogUtil.setTraceLogId(null);
            String traceLogId = TraceLogUtil.getTraceLogId();
            dxMap.put("traceLogId", traceLogId);
            dxMap.put("fileName", multipart.getOriginalFilename());
            dxMap.put("imageStream", new FileInputStream(file));
            dxMap.put("imageType", type);
            log.info("开始请求电信图片上传接口，入参："+dxMap);
            String s = "";
//            String s = HttpClientDXUtil.getInstance().sendHttpPost(dxUrl, dxMap);
            log.info("结束请求电信图片上传接口，出参："+s+",traceLogId:"+traceLogId);
            JSONObject jsonObject = JSONObject.parseObject(s);
            Boolean success = jsonObject.getBoolean("success");
            if (success){
                imgId = jsonObject.getJSONObject("result").getString("imageId");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return imgId;
    }
}
