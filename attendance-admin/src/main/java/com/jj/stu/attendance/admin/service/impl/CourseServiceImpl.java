package com.jj.stu.attendance.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
;
import com.github.pagehelper.PageHelper;
import com.jj.stu.attendance.admin.service.CourseService;
import com.jj.stu.attendance.dao.mapper.CourseMapper;
import com.jj.stu.attendance.dao.model.Course;
import com.jj.stu.attendance.dao.request.course.PageCourseListRequest;
import com.jj.stu.attendance.dao.response.course.PageCourseListResponse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Resource
    private CourseMapper courseMapper;

    @Override
    public PageCourseListResponse pageCourseList(PageCourseListRequest request) {
        PageCourseListResponse response = new PageCourseListResponse();
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        List<Course> courseList = courseMapper.selectList(wrapper);
        response.setCourseList(courseList);
        response.setTotalSize(courseList.size());
        return response;
    }
}
