package com.base.saas.common.controller;

import com.base.saas.util.HeaderUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

/**
 * @className: UploadController
 * @author: 佛学徒
 * @date: 2022/10/31
 * @Description： 文件处理类，目前只存在文件上传
 **/
@RestController
@RequestMapping("/api/file")
@Api(tags = "上传文件")
@Slf4j
public class FileController {

    @Value("${file.staticAccessPath}")
    private String staticAccessPath;

    @Value("${file.uploadFolder}")
    private String uploadFolder;

    /**
     * 文件上传
     *
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public ResponseEntity<String> upload(MultipartFile file, HttpServletRequest req) throws FileNotFoundException {

        //使用UUID生成唯一标识文件名
        String randomNumber = UUID.randomUUID().toString().replace("-", "");
        //获取文件的原始名
        String oldFilename = file.getOriginalFilename();
        //获取文件后缀 .pdf
        String extension = oldFilename.substring(oldFilename.lastIndexOf("."));
        //生成新的文件名
        String newFileName = randomNumber + extension;

        File dateDir = new File(uploadFolder);

        if (!dateDir.exists()) {
            //判断目录是否存在，不存在则直接创建
            dateDir.mkdirs();
        }
        try {
            file.transferTo(new File(dateDir, newFileName));
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().headers(HeaderUtil.createErrorMsg("upload error!")).body(null);
        }

        //上传完毕，可以把上传地址保存到数据库或者直接返回前端显示
        String virtualPath = "/api/file/";
        if (staticAccessPath.contains("**")) {
            virtualPath = staticAccessPath.replace("**", "");
        }
        String invented_address = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + virtualPath + newFileName;

        return ResponseEntity.ok().body(invented_address);
    }


}
