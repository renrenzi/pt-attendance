package com.jj.stu.attendance.admin.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.jj.stu.attendance.dao.model.Student;
import com.jj.stu.attendance.dao.request.student.StudentBatchInsertRequest;

/**
 * @author 任人子
 * @date 2022/11/4  - {TIME}
 */
public interface StudentService extends IService<Student> {

    void batchAddStudent(StudentBatchInsertRequest request);
}
