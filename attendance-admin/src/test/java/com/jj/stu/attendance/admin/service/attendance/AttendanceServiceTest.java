package com.jj.stu.attendance.admin.service.attendance;

import com.jj.stu.attendance.admin.BaseTest;
import com.jj.stu.attendance.admin.service.AttendanceService;
import com.jj.stu.attendance.dao.mapper.AttendanceMapper;
import com.jj.stu.attendance.dao.model.Attendance;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AttendanceServiceTest extends BaseTest {
    @Resource
    private AttendanceService attendanceService;
    @Test
    public void batchAddAttendanceTest(){
        List<Attendance> attendanceList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Attendance attendance = new Attendance();
            attendance.setId(i);
            attendance.setDate(new Date());
            attendance.setCourseId(i);
            attendance.setStudentId(i);
            attendance.setType("attendance");
            attendance.setCreateTime(new Date());
            attendance.setCreateUserId(1);
            attendanceList.add(attendance);
        }
        attendanceService.saveBatch(attendanceList);
    }
}
