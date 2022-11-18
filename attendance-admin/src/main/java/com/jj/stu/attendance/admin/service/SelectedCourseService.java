package com.jj.stu.attendance.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jj.stu.attendance.dao.model.SelectedCourse;
import com.jj.stu.attendance.dao.request.selectedCourse.PageSelectedCourseRequest;
import com.jj.stu.attendance.dao.response.selectedCourse.PageSelectedCourseResponse;

public interface SelectedCourseService extends IService<SelectedCourse> {
    PageSelectedCourseResponse pageSelectedCourseList(PageSelectedCourseRequest request);
}
