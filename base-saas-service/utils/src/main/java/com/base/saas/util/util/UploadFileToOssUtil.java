package com.base.saas.util.util;

import cn.fintecher.util.DateConversionUtil;
import cn.fintecher.util.MD5Util;
import cn.fintecher.util.StringUtil;
import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.common.utils.DateUtil;
import com.aliyun.oss.common.utils.IOUtils;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Blob;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author: wangtao
 * @Date: 2018/05/15 09:31
 * @Description: 文件上传到oss
 */
public class UploadFileToOssUtil {

    /**
     * 保存客户端上传文件到本地服务器
     *
     * @param request http request
     * @param saveDir 文件存储的路径
     * @param fileName 文件名
     * @return
     * @throws Exception
     */
    public static Map<String, Object> getFile(HttpServletRequest request, String saveDir, String fileName) throws Exception {
        // 上传配置
        // 3MB
        int memoryThreshold = 1024 * 1024 * 3;
        // 40MB
        int maxFileSize = 1024 * 1024 * 40;
        // 50MB
        int maxRequestSize = 1024 * 1024 * 50;

        // 配置上传参数
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
        factory.setSizeThreshold(memoryThreshold);
        // 设置临时存储目录
        File saveDirFile = new File(saveDir);
        // if (saveDirFile.isDirectory()) {
        if (!saveDirFile.exists()) {
            saveDirFile.mkdirs();
        }
        /*
         * } else { saveDir = System.getProperty("java.io.tmpdir"); }
         */
        factory.setRepository(new File(saveDir));

        ServletFileUpload upload = new ServletFileUpload(factory);

        // 设置最大文件上传值
        upload.setFileSizeMax(maxFileSize);

        // 设置最大请求值 (包含文件和表单数据)
        upload.setSizeMax(maxRequestSize);
        Map<String, Object> map = new HashMap();
        List<FileItem> formItems = upload.parseRequest(request);
        List<Map<String, Object>> fileList = new ArrayList<Map<String, Object>>();
        if (formItems != null && formItems.size() > 0) {
            // 迭代表单数据
            for (FileItem item : formItems) {
                // 处理不在表单中的字段
                if (!item.isFormField()) {
                    Map<String, Object> fileModel = new HashMap<String, Object>();
                    String path = saveDir + File.separator + fileName+".jpg";
                    File file = new File(path);
                    if (file.exists()) {
                        file.delete();
                    }
                    item.write(file);
                    fileModel.put("originalName", item.getName());
                    fileModel.put("file", file);
                    fileModel.put("Name", fileName+".jpg");
                    fileList.add(fileModel);
                } else {
                    String name = item.getFieldName(); // 获取name属性的值
                    String value = item.getString("utf-8"); // 获取value属性的值
                    map.put(name, value);
                }
                item.delete();
            }
        }
        map.put("fileList", fileList);
        return map;
    }

    /**
     * 保存客户端上传文件到本地服务器
     *
     * @param request http request
     * @param saveDir 文件存储的服务器路径
     * @param fileName 文件名
     * @param dir oss图片目录
     * @return
     * @throws Exception
     */
    public static Map<String, Object> getFileOss(HttpServletRequest request, String saveDir, String fileName, String dir, String bucketName, String endpoint, String accessKeyId, String accessKeySecret,String uploadType,FastFileStorageClient storageClient,String webUrl) throws Exception {
        if (StringUtil.isEmpty(dir)) {
            dir = "alp06";
        }
        // 上传配置
        // 3MB
        int memoryThreshold = 1024 * 1024 * 3;
        // 40MB
        int maxFileSize = 1024 * 1024 * 40;
        // 50MB
        int maxRequestSize = 1024 * 1024 * 50;

        // 配置上传参数
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
        factory.setSizeThreshold(memoryThreshold);
        // 设置临时存储目录
        File saveDirFile = new File(saveDir);
        // if (saveDirFile.isDirectory()) {
        if (!saveDirFile.exists()) {
            saveDirFile.mkdirs();
        }
        /*
         * } else { saveDir = System.getProperty("java.io.tmpdir"); }
         */
        factory.setRepository(new File(saveDir));

        ServletFileUpload upload = new ServletFileUpload(factory);

        // 设置最大文件上传值
        upload.setFileSizeMax(maxFileSize);

        // 设置最大请求值 (包含文件和表单数据)
        upload.setSizeMax(maxRequestSize);
        Map<String, Object> map = new HashMap();
        List<FileItem> formItems = upload.parseRequest(request);
        List<Map<String, Object>> fileList = new ArrayList<Map<String, Object>>();
        if (formItems != null && formItems.size() > 0) {
            // 迭代表单数据
            for (FileItem item : formItems) {
                // 处理不在表单中的字段
                if (!item.isFormField()) {
                    Map<String, Object> fileModel = new HashMap<String, Object>();
                    //获取后缀名
                    String suffixName=item.getName();
                    suffixName = suffixName.substring(suffixName.lastIndexOf(".") + 1);
                    String path = saveDir + File.separator + fileName + "." + suffixName;
                    File file = new File(path);
                    if (file.exists()) {
                        file.delete();
                    }
                    item.write(file);
                    String appUrl = "";
                    if (StringUtil.isNotEmpty(uploadType) && uploadType.equals("oss")){
                        appUrl = appOss(file,dir,bucketName, endpoint, accessKeyId, accessKeySecret);
                    }else {
                        FileInputStream tempFile = new FileInputStream(file);
                        StorePath storePath = storageClient.uploadFile(tempFile,file.length(),suffixName,null);
                        appUrl = webUrl+storePath.getFullPath();
                    }
                    fileModel.put("originalName", item.getName());
                    fileModel.put("file", file);
                    fileModel.put("Name", appUrl);
                    fileList.add(fileModel);
                    //本地文件删除
                    file.delete();
                } else {
                    String name = item.getFieldName(); // 获取name属性的值
                    String value = item.getString("utf-8"); // 获取value属性的值
                    map.put(name, value);
                }
                item.delete();
            }
        }
        map.put("fileList", fileList);
        return map;
    }

    public static Map<String, Object> getFileUrlByUploadType(HttpServletRequest request, String saveDir, String fileName, String dir, String bucketName, String endpoint, String accessKeyId, String accessKeySecret, String uploadType, FastFileStorageClient storageClient, String webUrl, MultipartFile multipartFile) throws Exception {
        if (StringUtil.isEmpty(dir)) {
            dir = "alp06";
        }
        // 上传配置
        // 3MB
        int memoryThreshold = 1024 * 1024 * 3;
        // 40MB
        int maxFileSize = 1024 * 1024 * 40;
        // 50MB
        int maxRequestSize = 1024 * 1024 * 50;

        // 配置上传参数
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
        factory.setSizeThreshold(memoryThreshold);
        // 设置临时存储目录
        File saveDirFile = new File(saveDir);
        // if (saveDirFile.isDirectory()) {
        if (!saveDirFile.exists()) {
            saveDirFile.mkdirs();
        }
        /*
         * } else { saveDir = System.getProperty("java.io.tmpdir"); }
         */
        factory.setRepository(new File(saveDir));

        ServletFileUpload upload = new ServletFileUpload(factory);

        // 设置最大文件上传值
        upload.setFileSizeMax(maxFileSize);

        // 设置最大请求值 (包含文件和表单数据)
        upload.setSizeMax(maxRequestSize);
        Map<String, Object> map = new HashMap();
        List<Map<String, Object>> fileList = new ArrayList<Map<String, Object>>();

        Map<String, Object> fileModel = new HashMap<String, Object>();
        //获取后缀名
        String suffixName=multipartFile.getOriginalFilename();
        suffixName = suffixName.substring(suffixName.lastIndexOf(".") + 1);
        String path = saveDir + File.separator + fileName + "." + suffixName;
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
        multipartFile.transferTo(file);
        String appUrl = "";
        String fileId = "";
        if (StringUtil.isNotEmpty(uploadType) && uploadType.equals("oss")){
            appUrl = appOss(file,dir,bucketName, endpoint, accessKeyId, accessKeySecret);
        }else {
            FileInputStream tempFile = new FileInputStream(file);
            StorePath storePath = storageClient.uploadFile(tempFile,file.length(),suffixName,null);
            appUrl = webUrl+storePath.getFullPath();
            for (int i=0;i<60;i++){
                Thread.sleep(500);
                System.out.println("第"+i+"次");
                boolean exists = new UrlResource(appUrl).exists();
                if (exists){
                    System.out.println(i);
                    break;
                }
            }
            fileId = UUID.randomUUID().toString();
        }
        fileModel.put("originalName", multipartFile.getName());
        fileModel.put("file", file);
        fileModel.put("fileId", fileId);
        fileModel.put("Name", appUrl);
        fileList.add(fileModel);
        //本地文件删除
        file.delete();

        map.put("fileList", fileList);
        return map;
    }



    /**
     * app上传图片至OSS
     */
    /**
     * @Author: wangtao
     * @Date: 2018/05/15 19:04
     * @Params: srcMap 图片base64
     * @Params: path 项目根路径
     * @Params: fileName 图片文件名
     * @Params: bucketName 、endpoint、accessKeyId、accessKeySecret ---oss配置
     * @Description: app上传图片至OSS 可设置链接有效时间
     * @return: Map
     */
    public static Map uploadToOssSetEffectiveTime(Map<String , String> srcMap,String path,String fileName,
                                                  String bucketName, String endpoint, String accessKeyId, String accessKeySecret) throws Exception {
        //解密
        Map<String , String> photoMap = new HashMap<String , String>();
        BASE64Decoder decoder = new BASE64Decoder();
        for(Map.Entry<String , String> entry : srcMap.entrySet()){
            String str = entry.getValue();
            String key = entry.getKey();
            str = str.replace("data:image/png;base64,", "").replace("}", "").replace(" ","+");
            byte[] b = decoder.decodeBuffer(str);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            File saveDirFile = new File(path);
            if (!saveDirFile.exists()) {
                saveDirFile.mkdirs();
            }
            String imgFilePath = path+fileName + ".jpg";//新生成的本地图片
            File file = new File(imgFilePath);
            OutputStream out = new FileOutputStream(file);
            out.write(b);
            out.flush();
            out.close();
            //压缩图片
            //file=new File(ImageZipUtil.zipImageFile(file,file,800,800));
            String appUrl = appOss(file,"alp06" ,bucketName, endpoint, accessKeyId, accessKeySecret);

            //调用获取签名方法
            Map signMap=new HashMap();
            signMap.put("endpoint",endpoint);
            signMap.put("accessId",accessKeyId);
            signMap.put("accessKey",accessKeySecret);
            signMap.put("bucketName",bucketName);
            signMap.put("key","alp06/"+fileName+".jpg");
            //设置失效时间
            String resultStr= getObjectFromOSS(signMap);
            //oss返回的图片地址
            photoMap.put(key,resultStr);
        }
        return photoMap;
    }

    //Blob 转byte[]
    public static byte[] blobToByte(Blob blob) throws Exception {
        byte[] bytes = null;
        try {
            InputStream in=blob.getBinaryStream();
            BufferedInputStream inBuffered = new BufferedInputStream(in);
            ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
            byte[] temp = new byte[1024];
            int size = 0;
            while ((size = inBuffered.read(temp)) != -1) {
                out.write(temp, 0, size);
            }
            inBuffered.close();
            in.close();
            bytes = out.toByteArray();
        } catch(Exception ex){
            ex.printStackTrace();
        }
        return bytes;
    }

    /**
     * 16进制字符串转化为二进制数据
     *
     * @param hex
     * @return
     */
    public static byte[] hexStringToByte(String hex) {
        int len = (hex.length() / 2);
        byte[] result = new byte[len];
        char[] achar = hex.toCharArray();
        for (int i = 0; i < len; i++) {
            int pos = i * 2;
            result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
        }
        return result;
    }

    private static byte toByte(char c) {
        return (byte) "0123456789abcdef".indexOf(c);
    }

    /**
     * @Author: wangtao
     * @Date: 2018/05/15 10:51
     * @Params: srcMap(图片的map)
     * @Params: imgPath(保存图片路径)
     * @Params: dir 图片保存到oss的目录
     * @Params: bucketName，endpoint，accessKeyId，accessKeySecret ---oss配置
     * @Description: app上传图片至OSS
     * @return: Map
     */
    public static Map uploadToOSS(Map<String , String> srcMap, String imgPath, String dir, String bucketName, String endpoint, String accessKeyId, String accessKeySecret,String uploadType,FastFileStorageClient storageClient,String webUrl) throws Exception {
        //解密
        Map<String , String> photoMap = new HashMap<String , String>();
        BASE64Decoder decoder = new BASE64Decoder();
        for(Map.Entry<String , String> entry : srcMap.entrySet()){
            String str = entry.getValue();
            String key = entry.getKey();
            str = str.replace("data:image/png;base64,", "").replace("}", "").replace(" ","+");
            byte[] b = decoder.decodeBuffer(str);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            String id = UUID.randomUUID().toString();
            File saveDirFile = new File(imgPath);
            if (!saveDirFile.exists()) {
                saveDirFile.mkdirs();
            }
            //新生成的图片
            String imgFilePath = imgPath + id + ".jpg";
            File file = new File(imgFilePath);
            OutputStream out = new FileOutputStream(file);
            out.write(b);
            out.flush();
            out.close();
            if(StringUtil.isEmpty(dir)){
                dir = "alp06";
            }
            String appUrl = "";
            if (StringUtil.isNotEmpty(uploadType) && uploadType.equals("oss")){
                appUrl = appOss(file,dir,bucketName, endpoint, accessKeyId, accessKeySecret);
            }else {
                FileInputStream tempFile = new FileInputStream(file);
                StorePath storePath = storageClient.uploadFile(tempFile,file.length(),"jpg",null);
                appUrl = webUrl + storePath.getFullPath();
            }
            photoMap.put(key,appUrl);
        }
        return photoMap;
    }

    public static Map uploadToOSSForBlob(Map<String , Object> srcMap, String imgPath, String dir, String bucketName, String endpoint, String accessKeyId, String accessKeySecret,String uploadType,FastFileStorageClient storageClient,String webUrl) throws Exception {
        //解密
        Map<String , String> photoMap = new HashMap<String , String>();
        BASE64Decoder decoder = new BASE64Decoder();
        for(Map.Entry<String , Object> entry : srcMap.entrySet()){
            Object str = entry.getValue();
            String key = entry.getKey();
            byte[] b;
            //重点在于两次转换，先将blob转字节数组
            String pdfToImgString = new String(blobToByte((Blob) str));
            //然后再把16进制转成二进制数组
            byte[] bytes = hexStringToByte(pdfToImgString);
            //转base64
            String strBase64 = new BASE64Encoder().encode(bytes);
            strBase64 = strBase64.replace("data:image/png;base64,", "").replace("}", "").replace(" ","+");
            b = decoder.decodeBuffer(strBase64);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }

            String id = UUID.randomUUID().toString();
            File saveDirFile = new File(imgPath);
            if (!saveDirFile.exists()) {
                saveDirFile.mkdirs();
            }
            //新生成的图片
            String imgFilePath = imgPath + id + ".jpg";
            File file = new File(imgFilePath);
            OutputStream out = new FileOutputStream(file);
            out.write(b);
            out.flush();
            out.close();
            if(StringUtil.isEmpty(dir)){
                dir = "alp06";
            }
            String appUrl = "";
            if (StringUtil.isNotEmpty(uploadType) && uploadType.equals("oss")){
                appUrl = appOss(file,dir,bucketName, endpoint, accessKeyId, accessKeySecret);
            }else {
                FileInputStream tempFile = new FileInputStream(file);
                StorePath storePath = storageClient.uploadFile(tempFile,file.length(),"jpg",null);
                appUrl = webUrl + storePath.getFullPath();
            }
            photoMap.put(key,appUrl);
        }
        return photoMap;
    }

    /**
     * @Author: wangtao
     * @Date: 2018/05/15 10:51
     * @Params: srcMap(图片的map)
     * @Params: imgPath(保存图片路径)
     * @Params: dir 图片保存到oss的目录
     * @Params: bucketName，endpoint，accessKeyId，accessKeySecret ---oss配置
     * @Description: 上传视频至OSS
     * @return: Map
     */
    public static Map uploadVIdeoToOSS(Map<String , String> srcMap, String imgPath, String dir, String bucketName, String endpoint, String accessKeyId, String accessKeySecret,String uploadType,FastFileStorageClient storageClient,String webUrl) throws Exception {
        //解密
        Map<String , String> photoMap = new HashMap<String , String>();
        BASE64Decoder decoder = new BASE64Decoder();
        for(Map.Entry<String , String> entry : srcMap.entrySet()){
            String str = entry.getValue();
            String key = entry.getKey();
            str = str.replace("data:image/png;base64,", "").replace("}", "").replace(" ","+");
            byte[] b = decoder.decodeBuffer(str);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            String id = UUID.randomUUID().toString();
            File saveDirFile = new File(imgPath);
            if (!saveDirFile.exists()) {
                saveDirFile.mkdirs();
            }
            //新生成的图片
            String imgFilePath = imgPath + id + ".mp4";
            File file = new File(imgFilePath);
            OutputStream out = new FileOutputStream(file);
            out.write(b);
            out.flush();
            out.close();
            if(StringUtil.isEmpty(dir)){
                dir = "alp06";
            }
            String appUrl = "";
            if (StringUtil.isNotEmpty(uploadType) && uploadType.equals("oss")){
                appUrl = appOss(file,dir,bucketName, endpoint, accessKeyId, accessKeySecret);
            }else {
                FileInputStream tempFile = new FileInputStream(file);
                StorePath storePath = storageClient.uploadFile(tempFile,file.length(),"mp4",null);
                appUrl = webUrl + storePath.getFullPath();
            }
            photoMap.put(key,appUrl);
        }
        return photoMap;
    }

    /**
     * @Author: wangtao
     * @Date: 2018/05/15 17:18
     * @Params: file 上传文件
     * @Params: sign 签名
     * @Params: bucketName、endpoint、accessKeyId、accessKeySecret ---oss配置
     * @Description: 上传oss
     * @return:String
     */
    public static String appOss(File file,String dir,String bucketName, String endpoint, String accessKeyId, String accessKeySecret) {
        OSSClient ossClient = new OSSClient("https://" + endpoint, accessKeyId, accessKeySecret);
        String resultStr = null;
        try {
            if(!file.exists()){
                return null;
            }
            String fileName =dir+"/"+file.getName();
            ossClient.putObject(bucketName, fileName, file);
            ossClient.shutdown();
            resultStr = "https://" + bucketName + "." + endpoint + "/" + fileName;
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultStr;
    }


    public static String getObjectFromOSS(Map map) {
        URL signedUrl=null;

        String endpoint="";
        String accessId="";
        String accessKey="";
        String bucketName="";
        String key = "";//文件名称
        if(map!=null){
            endpoint=String.valueOf(map.get("endpoint"));
            accessId=String.valueOf(map.get("accessId"));
            accessKey=String.valueOf(map.get("accessKey"));
            bucketName=String.valueOf(map.get("bucketName"));
            key=String.valueOf(map.get("key"));
        }

        OSS client = new OSSClientBuilder().build(endpoint, accessId, accessKey);
        try {
            Calendar cd = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            cd.setTime(cd.getTime());
            cd.add(Calendar.SECOND,20);
            String expirate=sdf.format(cd.getTime());
            Date expiration = DateConversionUtil.strConvertToDate(expirate,DateConversionUtil.STYLE_1);
            GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(bucketName, key, HttpMethod.GET);
            request.setExpiration(expiration);
            signedUrl = client.generatePresignedUrl(request);
            Map<String, String> customHeaders = new HashMap<String, String>();
            customHeaders.put("Range", "bytes=100-1000");
            OSSObject object = client.getObject(signedUrl, customHeaders);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            String expectedETag = null;
            try {
                int bufSize = 1024 * 4;
                byte[] buffer = new byte[bufSize];
                int bytesRead;
                while ((bytesRead = object.getObjectContent().read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                IOUtils.safeClose(outputStream);
                IOUtils.safeClose(object.getObjectContent());
            }
            ObjectMetadata metadata = client.getObjectMetadata(bucketName, key);
            String actualETag = metadata.getETag();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            client.shutdown();
        }
        return String.valueOf(signedUrl);
    }

    public static void putObjectToOSS(Map map) {

        String endpoint = "";
        String accessId = "";
        String accessKey = "";
        String bucketName = "";
        String key = "";//文件名
        String filePath = "";//本地文件路径

        if(map!=null){
            endpoint=String.valueOf(map.get("endpoint"));
            accessId=String.valueOf(map.get("accessId"));
            accessKey=String.valueOf(map.get("accessKey"));
            bucketName=String.valueOf(map.get("bucketName"));
            key=String.valueOf(map.get("key"));
            filePath=String.valueOf(map.get("filePath"));
        }

        OSS client = new OSSClientBuilder().build(endpoint, accessId, accessKey);
        FileInputStream fin = null;
        try {
            Calendar cd = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.US);
            sdf.setTimeZone(TimeZone.getTimeZone("GMT+8")); // 设置时区为GMT+8
            String expirate=sdf.format(cd.getTime());

            Date expiration = DateUtil.parseRfc822Date(expirate);
            GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(bucketName, key, HttpMethod.PUT);
            request.setExpiration(expiration);
            request.setContentType("application/octet-stream");
            request.addUserMetadata("author", "aliy");
            URL signedUrl = client.generatePresignedUrl(request);

            File f = new File(filePath);
            fin = new FileInputStream(f);
            Map<String, String> customHeaders = new HashMap<String, String>();
            customHeaders.put("Content-Type", "application/octet-stream");
            customHeaders.put("x-oss-meta-author", "aliy");
            PutObjectResult result = client.putObject(signedUrl, fin, f.length(), customHeaders);

            fin = new FileInputStream(f);
            byte[] binaryData = IOUtils.readStreamAsByteArray(fin);
            String expectedETag = BinaryUtil.encodeMD5(binaryData);
            String actualETag = result.getETag();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            client.shutdown();
            if(fin != null){
                try {
                    fin.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 生成文件夹
     */
    public static String getFilePackage(String tel,String type){

        String fileName="";
        try {
            String pTel = MD5Util.GetMD5Code(tel);
            SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmssSSS");
            String time=sdf.format(new Date());

            if("YX".equals(type)){
                fileName=pTel+"_"+time;
            }else if ("RL".equals(type)){
                fileName=pTel+"_"+time;
            }else if ("YH".equals(type)){
                fileName=pTel+"_"+time;
            }else if ("HT".equals(type)){
                fileName=pTel+"_"+time;
            }else if ("TX".equals(type)){
                fileName=pTel+"_"+time;
            }else  if("ZM".equals(type)){
                fileName=pTel+"_0_"+time;
            }else if ("FM".equals(type)){
                fileName=pTel+"_1_"+time;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return fileName;
    }


    /**
     * 图片链接失效重新生成新的链接
     * @param map
     * @return String
     */
    public static String getNewSign(Map map){

        String endpoint="";
        String accessId="";
        String accessKey="";
        String bucketName="";
        String key = "";//文件名称
        if(map!=null){
            endpoint=String.valueOf(map.get("endpoint"));
            accessId=String.valueOf(map.get("accessId"));
            accessKey=String.valueOf(map.get("accessKey"));
            bucketName=String.valueOf(map.get("bucketName"));
            key=String.valueOf(map.get("key"));
            key=key.substring(key.indexOf(".com")+5,key.indexOf("?"));
        }
        OSS client = new OSSClientBuilder().build(endpoint, accessId, accessKey);
        Calendar cd = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        cd.setTime(cd.getTime());
        if(map.containsKey("oss")){
            cd.add(Calendar.SECOND,60);
        }else{
            cd.add(Calendar.SECOND,20);
        }
        String expirate=sdf.format(cd.getTime());
        URL signedUrl=null;
        try{
            Date expiration = DateConversionUtil.strConvertToDate(expirate,DateConversionUtil.STYLE_1);
            GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(bucketName, key, HttpMethod.GET);
            request.setExpiration(expiration);
            signedUrl = client.generatePresignedUrl(request);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            client.shutdown();
        }
        return String.valueOf(signedUrl);
    }


//    public static void main(String args[]) throws ParseException {
//        String url="http://ronghuialp.oss-cn-shenzhen.aliyuncs.com/alp06/ef1d2f5bbdfacecd83f31415fa567cd1_1_20180213151220625.jpg?Expires=1518563541&OSSAccessKeyId=LTAIvE2cfCGXURWc&Signature=VMWKUwKlGemyusX%2FR%2BzYfyxvvM0%3D";
//        String a=url.substring(url.indexOf(".com")+5,url.indexOf("?"));
//        String expire=url.substring(url.indexOf("Expires=")+8,url.indexOf("&OSSAccessKeyId"));
//        System.out.println("********************");
//        System.out.println(a);
//        System.out.println("********************");
//        System.out.println(expire);
//        long time=System.currentTimeMillis();
//        String timeStr=String.valueOf(time);
//        System.out.println(timeStr.substring(0,timeStr.length()-3));
//
//    }


    /**
     * 判断签名是否失效
     * @param url
     * @return true(失效) false（未失效）
     */
    public static boolean isExpired(String url){

        if(url!=null&&url.length()>0&&!"null".equals(url)){
            int index=0;
            if(url.indexOf("&OSSAccessKeyId")>-1){
                index=url.indexOf("&OSSAccessKeyId");
            }else if(url.indexOf("&amp;OSSAccessKeyId")>-1){
                index=url.indexOf("&amp;OSSAccessKeyId");
            }
            String expire=url.substring(url.indexOf("Expires=")+8,index);
            long time=System.currentTimeMillis();
            String timeStr=String.valueOf(time);
            timeStr=timeStr.substring(0,timeStr.length()-3);
            if(Long.valueOf(timeStr)-Long.valueOf(expire)>0){
                return true;//失效
            }else {
                return false;
            }
        }else{
            return false;
        }

    }


    public static void main(String args[]){
//        saveUrlAs();
    }


    /**
     * 从OSS写文件到本地
     * @param url  OSS路径
     * @param filePath  文件本地文件夹
     * @param method  请求方式(要求大写 POST不行时，使用GET)
     * @param ext  文件扩展名：.png/.jpg/.pdf
     * @return 文件本地路径
     */
    public static String saveUrlAs(String url, String filePath, String method,String ext) {
        //创建不同的文件夹目录
        File file = new File(filePath);
        String fileName = "";
        //判断文件夹是否存在
        if (!file.exists()) {
            //如果文件夹不存在，则创建新的的文件夹
            file.mkdirs();
        }
        FileOutputStream fileOut = null;
        HttpURLConnection conn = null;
        InputStream inputStream = null;
        try {
            // 建立链接
            URL httpUrl = new URL(url);
            conn = (HttpURLConnection) httpUrl.openConnection();
            //以Post方式提交表单，默认get方式
            conn.setRequestMethod(method);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            // post方式不能使用缓存
            conn.setUseCaches(false);
            //连接指定的资源
            conn.connect();
            //获取网络输入流
            inputStream = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(inputStream);
            //判断文件的保存路径后面是否以/结尾
            if (!filePath.endsWith("/")) {
                filePath += "/";
            }
            //写入到文件（注意文件保存路径的后面一定要加上文件的名称）
            fileName = filePath + UUID.randomUUID().toString() + ext;
            fileOut = new FileOutputStream(fileName);
            BufferedOutputStream bos = new BufferedOutputStream(fileOut);
            byte[] buf = new byte[4096];
            int length = bis.read(buf);
            //保存文件
            while (length != -1) {
                bos.write(buf, 0, length);
                length = bis.read(buf);
            }
            bos.close();
            bis.close();
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileName;

    }
}
