package com.base.saas.common.upload;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Map;

/**
 * Title :
 * Description : @上传下载文件接口@
 * Create on : 2018年06月04日
 * Copyright (C) zw.FinTec
 *
 * @author department:研发部
 *         username:chengrui
 * @version 修改历史:
 *          修改人 修改日期 修改描述
 *          -------------------------------------------<
 */
public interface UploadFileService {

    /**
    * @Description: 上传文件
    * @Param:
     * * @param srcMap 图片base64
      * @param str 参数
    * @return: java.util.Map
    * @Date: 2018/6/4
    */
    Map getFileUrlByUploadType(Object... objs);

    Map uploadFile(Object... objs);


    /**
    * @Description: 下载文件
    * @Param:  * @param str 下载需要的参数
    * @return: java.lang.String
    * @Date: 2018/6/4
    */
    String downloadFile(Object... objs);

    /**
     * 调用电信上传接口
     * @return
     */
    String dxUpload(Map dxMap, File file, MultipartFile multipart, String type, String dxUrl);
}