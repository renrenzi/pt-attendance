package com.jj.stu.attendance.admin.service.impl;

import com.jj.stu.attendance.admin.service.StudentService;
import com.jj.stu.attendance.dao.mapper.StudentMapper;
import com.jj.stu.attendance.dao.model.Student;
import com.jj.stu.attendance.dao.request.StudentBatchInsertRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 任人子
 * @date 2022/11/4  - {TIME}
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Resource
    private StudentMapper mapper;

    @Override
    public void batchAddStudent(StudentBatchInsertRequest request) {
        for(Student student : request.getStudentList()){
            mapper.insertSelective(student);
        }
    }
}
