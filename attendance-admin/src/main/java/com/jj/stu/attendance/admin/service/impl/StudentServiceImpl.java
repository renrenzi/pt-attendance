package com.jj.stu.attendance.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jj.stu.attendance.admin.service.StudentService;
import com.jj.stu.attendance.dao.mapper.StudentMapper;
import com.jj.stu.attendance.dao.model.Student;
import com.jj.stu.attendance.dao.request.student.StudentBatchInsertRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 学生服务impl
 *
 * @author 任人子
 * @date 2022/11/4  - {TIME}
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {
    @Resource
    private StudentMapper mapper;

    @Override
    public void batchAddStudent(StudentBatchInsertRequest request) {
        for(Student student : request.getStudentList()){
            student.setCreateDate(new Date());
            mapper.insertSelective(student);
        }
    }
}
