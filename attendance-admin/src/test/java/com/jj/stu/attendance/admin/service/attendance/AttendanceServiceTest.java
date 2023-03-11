package com.jj.stu.attendance.admin.service.attendance;

import com.jj.stu.attendance.admin.BaseTest;
import com.jj.stu.attendance.admin.service.AttendanceService;
import com.jj.stu.attendance.dao.mapper.*;
import com.jj.stu.attendance.dao.model.Attendance;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AttendanceServiceTest extends BaseTest {
    @Resource
    private AttendanceService attendanceService;
    @Resource
    private AttendanceMapper attendanceMapper;
    @Resource
    private ClazzMapper clazzMapper;
    @Resource
    private CourseMapper courseMapper;
    @Resource
    private LeaveMapper leaveMapper;
    @Resource
    private SelectedCourseMapper selectedCourseMapper;
    @Resource
    private StudentMapper studentMapper;
    @Resource
    private TeacherMapper teacherMapper;
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

   /* @Test
    public void batchDelete(){
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            list.add(i);
        }
        attendanceMapper.deleteBatchIds(list);
        clazzMapper.deleteBatchIds(list);
        courseMapper.deleteBatchIds(list);
        leaveMapper.deleteBatchIds(list);
        selectedCourseMapper.deleteBatchIds(list);
        studentMapper.deleteBatchIds(list);
        teacherMapper.deleteBatchIds(list);
    }*/
}
