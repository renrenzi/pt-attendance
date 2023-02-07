package com.jj.stu.attendance.admin.controller;

import com.jj.stu.attendance.admin.util.UploadUtil;
import com.jj.stu.attendance.base.basic.StringValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RestController
public class UploadController {

    @Resource
    private UploadUtil uploadUtil;

    @PostMapping("/upload")
    public StringValue upload(MultipartFile file){
        return uploadUtil.uploadFile(file);
    }

}
