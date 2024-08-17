package com.sipc.clockin.utils;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.junit.Test;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

public class UploadUtils {

    public static final String accessKey = "10DDkOS_h59xjxbKkA2r01OYGnJCWbYFxIcSGvpE";
    public static final String secretKey = "nZ1FZvyec8wH6h9C2OnyAVsNrt3A3-vzRZLuv_E9";
    public static final String bucket="sipc115-elite";
    public static final String bucket_url = "https://sipc115-elite.s3.cn-north-1.qiniucs.com";
    public static final Auth auth = Auth.create(accessKey, secretKey);
    private static final UploadManager uploadManager = new UploadManager(new Configuration());
    public static String upload(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();

        String ext = originalFilename.substring(originalFilename.lastIndexOf("."));
        String uuid = UUID.randomUUID().toString().replace("-", ""); //生成唯一标识符
        String newFileName = uuid + ext;
        String upToken = auth.uploadToken(bucket);
        try {
            Response response = uploadManager.put(file.getInputStream(), newFileName, upToken, null, null);
            if (response.isOK()) {
                DefaultPutRet putRet = response.jsonToObject(DefaultPutRet.class);
                System.out.println(putRet.key);
                System.out.println(putRet.hash);
            } else {
                System.err.println(response.toString());
            }
        } catch (QiniuException ex) {
            ex.printStackTrace();
            if (ex.response != null) {
                System.err.println(ex.response);
                try {
                    String body = ex.response.toString();
                    System.err.println(body);
                } catch (Exception ignored) {
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String url = bucket_url + "/" + newFileName;
        return url;
    }
    public static String downloadFile(String publicUrl) {
        String downloadUrl = null;
        try {
            downloadUrl = auth.privateDownloadUrl(publicUrl);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return downloadUrl;
    }

    @Test
    public void test() {

    }
}
