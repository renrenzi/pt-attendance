package com.jj.stu.attendance.admin.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.jj.stu.attendance.dao.model.Student;
import com.jj.stu.attendance.meta.request.PageStudentRequest;
import com.jj.stu.attendance.meta.request.StudentBatchInsertRequest;
import com.jj.stu.attendance.meta.request.StudentUpdateRequest;
import com.jj.stu.attendance.meta.response.PageStudentResponse;

import java.util.List;

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


    /**
     * 更新学生信息
     *
     * @param request 请求
     */
    void updateStudentInfo(StudentUpdateRequest request);


    /**
     * 批量删除学生列表
     *
     * @param studentIds 学生id
     */
    void batchDeleteStudentList(List<Integer> studentIds);
}
