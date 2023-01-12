package com.jj.stu.attendance.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jj.stu.attendance.dao.model.Course;
import com.jj.stu.attendance.dao.request.course.EditCourseRequest;
import com.jj.stu.attendance.dao.request.course.PageCourseListRequest;
import com.jj.stu.attendance.dao.response.course.PageCourseListResponse;

import java.util.List;

public interface CourseService extends IService<Course> {
    /**
     * 课程列表页
     *
     * @param request 请求
     * @return {@link PageCourseListResponse}
     */
    PageCourseListResponse pageCourseList(PageCourseListRequest request);

    /**
     * 批量删除课程由ids
     *
     * @param courseIds 当然id
     */
    void batchDeleteCourseByIds(List<Integer> courseIds);

    /**
     * 编辑课程细节
     *
     * @param request 请求
     */
    void editCourseDetail(EditCourseRequest request);

    /**
     * 添加课程
     *
     * @param request 请求
     */
    void addCourse(EditCourseRequest request);
}
