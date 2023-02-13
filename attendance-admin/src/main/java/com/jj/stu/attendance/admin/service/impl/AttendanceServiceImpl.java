package com.jj.stu.attendance.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jj.stu.attendance.admin.constants.AttendanceTypeEnum;
import com.jj.stu.attendance.admin.service.AttendanceService;
import com.jj.stu.attendance.base.basic.Result;
import com.jj.stu.attendance.base.exception.ApiException;
import com.jj.stu.attendance.dao.mapper.AttendanceMapper;
import com.jj.stu.attendance.dao.mapper.CourseMapper;
import com.jj.stu.attendance.dao.mapper.SelectedCourseMapper;
import com.jj.stu.attendance.dao.mapper.StudentMapper;
import com.jj.stu.attendance.dao.model.Attendance;
import com.jj.stu.attendance.dao.model.Course;
import com.jj.stu.attendance.dao.model.Student;
import com.jj.stu.attendance.meta.dto.AttendanceDTO;
import com.jj.stu.attendance.meta.request.ManageAttendanceRequest;
import com.jj.stu.attendance.meta.request.PageAttendanceRequest;
import com.jj.stu.attendance.meta.request.PunchTheClockRequest;
import com.jj.stu.attendance.meta.response.PageAttendanceResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

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
        List<Integer> studentIds = attendanceList.stream().map(Attendance::getStudentId).distinct().collect(Collectors.toList());
        List<Integer> courseIds = attendanceList.stream().map(Attendance::getCourseId).distinct().collect(Collectors.toList());

        Map<Integer, Course> courseIdToNameMap = new HashMap<>();
        Map<Integer, Student> studentIdToNameMap = new HashMap<>();

        if (!CollectionUtils.isEmpty(courseIds)) {
            courseIdToNameMap = courseMapper.selectList(new QueryWrapper<Course>().lambda().in(Course::getId, courseIds))
                    .stream().collect(Collectors.toMap(Course::getId, Function.identity(), (v2, v1) -> v1));
        }
        if (!CollectionUtils.isEmpty(studentIds)) {
            studentIdToNameMap = studentMapper.selectList(new QueryWrapper<Student>().lambda().in(Student::getId, studentIds))
                    .stream().collect(Collectors.toMap(Student::getId, Function.identity(), (v2, v1) -> v1));
        }
        List<AttendanceDTO> result = new ArrayList<>(attendanceList.size());
        for (Attendance attendance: attendanceList) {
            AttendanceDTO attendanceDTO = new AttendanceDTO();
            BeanUtil.copyProperties(attendance, attendanceDTO);
            Course course = courseIdToNameMap.get(attendance.getCourseId());
            if (course != null) {
                attendanceDTO.setCourseName(course.getName());
            }
            Student student = studentIdToNameMap.get(attendance.getStudentId());
            if (student != null){
                attendanceDTO.setNickname(student.getNickName());
                attendanceDTO.setUsername(student.getUsername());
                attendanceDTO.setType(AttendanceTypeEnum.getAttendanceMessage(attendance.getType()));
            }
            result.add(attendanceDTO);
        }
        return new PageAttendanceResponse()
                .setTotalSize(page.getTotal())
                .setAttendanceList(result);
    }

    @Override
    public void batchDeleteAttendanceList(List<Integer> attendanceIds) {
        attendanceMapper.deleteBatchIds(attendanceIds);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void updateAttendanceInfo(ManageAttendanceRequest request) {
        int result;
        Attendance attendance = covertToEntity(request);
        if (attendanceMapper.selectById(attendance.getId()) == null) {
            attendance.setCreateTime(new Date()).setCreateUserId(request.getUserId());
            result = attendanceMapper.insert(attendance);
        } else {
            attendance.setUpdateTime(new Date()).setUpdateUserId(request.getUserId());
            result = attendanceMapper.updateByPrimaryKeySelective(attendance);
        }
        if (result != 1) {
            throw new ApiException("修改考勤信息失败");
        }
    }

    private Attendance covertToEntity(ManageAttendanceRequest request) {
        return new Attendance().setId(request.getId())
                .setCourseId(request.getCourseId())
                .setStudentId(request.getStudentId())
                .setDate(request.getDate())
                .setType(request.getType());
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
