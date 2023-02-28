package com.jj.stu.attendance.admin;

import com.jj.stu.attendance.admin.util.UploadUtil;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import javax.annotation.Resource;

public class UploadTest extends BaseTest{
    @Resource
    private UploadUtil uploadUtil;
    @Resource
    private ApplicationContext context;
    @Test
    public void uploadTest(){

    }
}
