package com.jj.stu.attendance.admin.service.leave;

import com.jj.stu.attendance.admin.BaseTest;
import com.jj.stu.attendance.admin.service.LeaveService;
import com.jj.stu.attendance.dao.model.Leave;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LeaveServiceTest extends BaseTest {

    @Resource
    private LeaveService leaveService;

    @Test
    public void batchAddLeave() {
        List<Leave> leaveList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Leave leave = new Leave();
            leave.setId(i);
            leave.setStudentId(i);
            leave.setInfo("测试请假信息" + i);
            leave.setCreateDate(new Date());
            leave.setRemark("测试请假备注" + i);
            leave.setStatus(true);
            leaveList.add(leave);
        }
        leaveService.saveBatch(leaveList);
    }
}
