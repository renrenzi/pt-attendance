package com.jj.stu.attendance.admin.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.jj.stu.attendance.base.basic.Result;
import com.jj.stu.attendance.dao.model.Student;
import com.jj.stu.attendance.dao.request.student.PageStudentRequest;
import com.jj.stu.attendance.dao.request.student.StudentBatchInsertRequest;
import com.jj.stu.attendance.dao.response.student.PageStudentResponse;

/**
 * @author 任人子
 * @date 2022/11/4  - {TIME}
 */
public interface StudentService extends IService<Student> {

    /**
     * 批量添加学生
     *
     * @param request 请求
     */
    void batchAddStudent(StudentBatchInsertRequest request);

    /**
     * 学生列表页
     *
     * @param request 请求
     * @return {@link PageStudentResponse}
     */
    PageStudentResponse pageStudentList(PageStudentRequest request);
}
