package com.jj.stu.attendance.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jj.stu.attendance.admin.constants.AttendanceTypeEnum;
import com.jj.stu.attendance.admin.service.AttendanceService;
import com.jj.stu.attendance.base.basic.Result;
import com.jj.stu.attendance.base.exception.ApiException;
import com.jj.stu.attendance.dao.mapper.*;
import com.jj.stu.attendance.dao.model.*;
import com.jj.stu.attendance.dao.request.attendance.ManageAttendanceRequest;
import com.jj.stu.attendance.dao.request.attendance.PageAttendanceRequest;
import com.jj.stu.attendance.dao.request.attendance.PunchTheClockRequest;
import com.jj.stu.attendance.dao.response.attendance.PageAttendanceResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 出席服务impl
 *
 * @author 张俊杰
 * @date 2022/11/19
 */
@Service
public class AttendanceServiceImpl extends ServiceImpl<AttendanceMapper, Attendance> implements AttendanceService {
    @Resource
    private AttendanceMapper attendanceMapper;
    @Resource
    private CourseMapper courseMapper;
    @Resource
    private StudentMapper studentMapper;
    @Resource
    private SelectedCourseMapper selectedCourseMapper;

    @Override
    public PageAttendanceResponse pageAttendanceList(PageAttendanceRequest request) {
        Page<Object> page = PageHelper.startPage(request.getPageNum(), request.getPageSize());
        List<Attendance> attendanceList = attendanceMapper.selectList(new QueryWrapper<>());
        return new PageAttendanceResponse()
                .setTotalSize(page.getTotal())
                .setAttendanceList(attendanceList);
    }

    @Override
    public void batchDeleteAttendanceList(List<Integer> attendanceIds) {
        attendanceMapper.deleteBatchIds(attendanceIds);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void updateAttendanceInfo(ManageAttendanceRequest request) {
        int result;
        if (attendanceMapper.selectById(request.getAttendance().getId()) == null) {
            result = attendanceMapper.insert(request.getAttendance());
        } else {
            result = attendanceMapper.updateByPrimaryKeySelective(request.getAttendance());
        }
        if (result != 1) {
            throw new ApiException("修改考勤信息失败");
        }
    }

    @Override
    public Result punchTheClock(PunchTheClockRequest request) {
        Course course = courseMapper.selectByPrimaryKey(request.getCourseId());
        Student student = studentMapper.selectOne(new QueryWrapper<Student>()
                .lambda().eq(Student::getAdminId, request.getAdminId()));
        if (student == null) {
            throw new ApiException("用户不存在，打卡失败！");
        }
        // 添加出勤记录
        Attendance attendance = new Attendance();
        attendance.setDate(request.getClockingTime());
        attendance.setCourseId(request.getCourseId());
        attendance.setStudentId(student.getId());
        checkAttendanceState(attendance, course);
        attendanceMapper.insert(attendance);
        return null;
    }

    /**
     * 检查出勤状态
     *
     * @param attendance 出席
     */
    private void checkAttendanceState(Attendance attendance, Course course){
        // 通过课程时间检查考勤状态
        attendance.setType(AttendanceTypeEnum.ATTENDANCE.getType());

    }
}
