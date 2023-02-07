package com.jj.stu.attendance.admin.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.jj.stu.attendance.admin.config.OssConfig;
import com.jj.stu.attendance.base.basic.StringValue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * 上传文件工具类
 *
 * @author LENOVO
 * @date 2023/01/20
 */
@Component
public class UploadUtil {

    private static final String ENDPOINT = OssConfig.END_POINT;

    private static final String ACCESS_KEY_ID = OssConfig.ACCESS_KEY_ID;

    private static final String ACCESS_KEY_SECRET = OssConfig.ACCESS_KEY_SECRET;

    private static final String BUCKET_NAME = OssConfig.BUCKET_NAME;

    private OSS ossClient;

    @Bean
    void initOssBean(){
         ossClient = new OSSClient(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
    }

    public StringValue uploadFile(MultipartFile file){
        try {
            InputStream inputStream = file.getInputStream();
            String fileName = file.getOriginalFilename();
            ossClient.putObject(BUCKET_NAME,fileName,inputStream);
            ossClient.shutdown();
            String url = "https://"+BUCKET_NAME+"."+ENDPOINT+"/"+fileName;
            return new StringValue(url);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
