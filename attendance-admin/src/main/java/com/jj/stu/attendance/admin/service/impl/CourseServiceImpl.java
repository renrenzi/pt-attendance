package com.jj.stu.attendance.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jj.stu.attendance.admin.service.CourseService;
import com.jj.stu.attendance.base.exception.ApiException;
import com.jj.stu.attendance.dao.mapper.CourseMapper;
import com.jj.stu.attendance.dao.mapper.TeacherMapper;
import com.jj.stu.attendance.dao.model.Course;
import com.jj.stu.attendance.dao.model.Teacher;
import com.jj.stu.attendance.meta.dto.PageCourseDTO;
import com.jj.stu.attendance.meta.request.EditCourseRequest;
import com.jj.stu.attendance.meta.request.PageCourseListRequest;
import com.jj.stu.attendance.meta.response.PageCourseListResponse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

;


/**
 * 课程服务实现类
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
    public void addCourse(EditCourseRequest request) {
        int res = courseMapper.insertSelective(covertToCourse(request));
        if (res != 1) {
            throw new ApiException("添加課程失敗");
        }
    }

    @Override
    public void editCourseDetail(EditCourseRequest request) {
        Course course = covertToCourse(request);
        if (courseMapper.selectById(course.getId()) == null) {
            course.setCourseDate(new Date());
            courseMapper.insertSelective(course);
        } else {
            courseMapper.updateByPrimaryKeySelective(course);
        }
    }

    private Course covertToCourse(EditCourseRequest request) {
        return Course.builder()
                .id(request.getId())
                .name(request.getName())
                .teacherId(request.getTeacherId())
                .courseDate(request.getCourseDate())
                .selectedNum(request.getSelectedNum())
                .maxNum(request.getMaxNum())
                .info(request.getInfo())
                .build();
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
        Page<Object> page = PageHelper.startPage(request.getPageNum(), request.getPageSize());
        List<Course> courseList = courseMapper.selectList(wrapper);

        List<PageCourseDTO> pageCourseDtoList = BeanUtil.copyToList(courseList, PageCourseDTO.class);
        List<Integer> teacherIds = courseList.stream().map(Course::getTeacherId).collect(Collectors.toList());
        Map<Integer, String> teacherIdToUserNameMap = teacherMapper.selectTeacherListByIds(teacherIds).stream()
                .collect(Collectors.toMap(Teacher::getId, Teacher::getUsername));
        pageCourseDtoList.forEach(item -> {
            item.setTeacherName(teacherIdToUserNameMap.get(item.getTeacherId()));
        });
        response.setPageCourseDtoList(pageCourseDtoList);
        response.setTotalSize(page.getTotal());
        return response;
    }
}
