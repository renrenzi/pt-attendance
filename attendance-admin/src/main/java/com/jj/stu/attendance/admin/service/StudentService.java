package com.jj.stu.attendance.admin.service;


import com.jj.stu.attendance.dao.request.StudentBatchInsertRequest;

/**
 * @author 任人子
 * @date 2022/11/4  - {TIME}
 */
public interface StudentService {

    void batchAddStudent(StudentBatchInsertRequest request);
}
