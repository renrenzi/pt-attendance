package com.jj.stu.attendance.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jj.stu.attendance.admin.service.StudentService;
import com.jj.stu.attendance.dao.mapper.StudentMapper;
import com.jj.stu.attendance.dao.model.Student;
import com.jj.stu.attendance.meta.request.PageStudentRequest;
import com.jj.stu.attendance.meta.request.StudentBatchInsertRequest;
import com.jj.stu.attendance.meta.request.StudentUpdateRequest;
import com.jj.stu.attendance.meta.response.PageStudentResponse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 学生服务impl
 *
 * @author 任人子
 * @date 2022/11/4  - {TIME}
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {
    @Resource
    private StudentMapper studentMapper;

    @Override
    public PageStudentResponse pageStudentList(PageStudentRequest request) {
        Page<Object> page = PageHelper.startPage(request.getPageNum(), request.getPageSize());
        List<Student> studentList = studentMapper.selectList(new QueryWrapper<>());
        return new PageStudentResponse()
                .setStudentList(studentList)
                .setTotalSize(page.getTotal());
    }

    @Override
    public void batchAddStudent(StudentBatchInsertRequest request) {
        for(Student student : request.getStudentList()){
            student.setCreateDate(new Date());
            studentMapper.insertSelective(student);
        }
    }

    @Override
    public void updateStudentInfo(StudentUpdateRequest request) {

    }
}
