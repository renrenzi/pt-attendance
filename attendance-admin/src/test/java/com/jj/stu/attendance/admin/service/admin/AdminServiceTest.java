package com.jj.stu.attendance.admin.service.admin;

import cn.hutool.crypto.digest.DigestUtil;
import com.jj.stu.attendance.admin.BaseTest;
import com.jj.stu.attendance.admin.service.AdminService;
import com.jj.stu.attendance.dao.model.Admin;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdminServiceTest extends BaseTest {
    @Resource
    private AdminService adminService;

    @Test
    public void testBatchAdd(){
        List<Admin> res = new ArrayList<Admin>();
        for (int i = 0; i < 20; i++) {
            Admin admin = new Admin();
            admin.setCreateTime(new Date());
            admin.setCreateUser("1");
            admin.setCreateTime(new Date());
            admin.setUserName("测试用户");
            admin.setPassWord(DigestUtil.md5Hex("12345"+i));
            res.add(admin);
        }
        adminService.saveBatch(res);

    }
}
