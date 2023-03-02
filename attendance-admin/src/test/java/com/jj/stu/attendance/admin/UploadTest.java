package com.jj.stu.attendance.admin;

import com.jj.stu.attendance.admin.util.UploadUtil;
import com.jj.stu.attendance.task.event.ExportTaskEvent;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import javax.annotation.Resource;
import java.util.Date;

public class UploadTest extends BaseTest{
    @Resource
    private UploadUtil uploadUtil;
    @Resource
    private ApplicationContext context;
    @Test
    public void uploadTest(){
        System.out.println("upload start !");
        context.publishEvent(new ExportTaskEvent(context, "test",  new Date()));
        System.out.println("upload end !");
    }
}
