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
        for (int i = 10000; i < 40000; i++) {
            Attendance attendance = new Attendance();
            attendance.setId(i);
            attendance.setDate(new Date());
            attendance.setCourseId(i);
            attendance.setStudentId(i);
            attendance.setType("出席");
            attendanceList.add(attendance);
        }
        attendanceService.saveBatch(attendanceList);
    }
}
