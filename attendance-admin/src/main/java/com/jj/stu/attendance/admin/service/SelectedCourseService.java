package com.jj.stu.attendance.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jj.stu.attendance.dao.model.SelectedCourse;
import com.jj.stu.attendance.dao.request.selectedCourse.PageSelectedCourseRequest;
import com.jj.stu.attendance.dao.response.selectedCourse.PageSelectedCourseResponse;

import java.util.List;

public interface SelectedCourseService extends IService<SelectedCourse> {
    /**
     * 页面选择课程列表
     *
     * @param request 请求
     * @return {@link PageSelectedCourseResponse}
     */
    PageSelectedCourseResponse pageSelectedCourseList(PageSelectedCourseRequest request);

    /**
     * 批量删除所选课程列表
     *
     * @param selectedCourseIds 选修课id
     */
    void batchDeleteSelectedCourseList(List<Integer> selectedCourseIds);
}
