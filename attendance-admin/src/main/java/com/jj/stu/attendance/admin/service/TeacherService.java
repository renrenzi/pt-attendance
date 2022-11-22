package com.jj.stu.attendance.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jj.stu.attendance.dao.model.Teacher;
import com.jj.stu.attendance.dao.request.teacher.PageTeacherRequest;
import com.jj.stu.attendance.dao.response.teacher.PageTeacherResponse;

public interface TeacherService extends IService<Teacher> {
    /**
     * 老师列表页
     *
     * @param request 请求
     * @return {@link PageTeacherResponse}
     */
    PageTeacherResponse pageTeacherList(PageTeacherRequest request);
}
