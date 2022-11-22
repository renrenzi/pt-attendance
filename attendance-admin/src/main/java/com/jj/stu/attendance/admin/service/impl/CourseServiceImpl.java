package com.jj.stu.attendance.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
;
import com.github.pagehelper.PageHelper;
import com.jj.stu.attendance.admin.service.CourseService;
import com.jj.stu.attendance.dao.dto.PageCourseDto;
import com.jj.stu.attendance.dao.mapper.CourseMapper;
import com.jj.stu.attendance.dao.mapper.TeacherMapper;
import com.jj.stu.attendance.dao.model.Course;
import com.jj.stu.attendance.dao.model.Teacher;
import com.jj.stu.attendance.dao.request.course.EditCourseRequest;
import com.jj.stu.attendance.dao.request.course.PageCourseListRequest;
import com.jj.stu.attendance.dao.response.course.PageCourseListResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * 当然服务impl
 *
 * @author 张俊杰
 * @date 2022/11/19
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Resource
    private CourseMapper courseMapper;
    @Resource
    private TeacherMapper teacherMapper;


    @Override
    public void editCourseDetail(EditCourseRequest request) {
        Course course = request.getCourse();
        courseMapper.updateByPrimaryKeySelective(course);
    }

    @Override
    public void batchDeleteCourseByIds(List<Integer> courseIds) {
        courseMapper.deleteBatchIds(courseIds);
    }

    @Override
    public PageCourseListResponse pageCourseList(PageCourseListRequest request) {
        PageCourseListResponse response = new PageCourseListResponse();
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        if (request.getCourse() != null && request.getCourse().getName() != null) {
            // 根据课程名字模糊查询, 按时间逆序排序
            wrapper.lambda().like(Course::getName, request.getCourse().getName());
        }
        wrapper.lambda().orderByDesc(Course::getCourseDate);
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        List<Course> courseList = courseMapper.selectList(wrapper);

        List<PageCourseDto> pageCourseDtoList = BeanUtil.copyToList(courseList, PageCourseDto.class);
        List<Integer> teacherIds = courseList.stream().map(Course::getTeacherId).collect(Collectors.toList());
        Map<Integer, String> teacherIdToUserNameMap = teacherMapper.selectTeacherListByIds(teacherIds).stream()
                .collect(Collectors.toMap(Teacher::getId, Teacher::getUsername));
        pageCourseDtoList.forEach(item -> {
            item.setTeacherName(teacherIdToUserNameMap.get(item.getTeacherId()));
        });
        response.setPageCourseDtoList(pageCourseDtoList);
        response.setTotalSize(courseList.size());
        return response;
    }
}
