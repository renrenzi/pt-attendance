package com.jj.stu.attendance.admin.service.student;

import com.jj.stu.attendance.admin.BaseTest;
import com.jj.stu.attendance.dao.mapper.StudentMapper;
import com.jj.stu.attendance.dao.model.Student;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentServiceTest extends BaseTest {
    @Resource
    private StudentMapper studentMapper;
    @Test
    public void batchAddStudentTest(){
        List<Student> studentList = new ArrayList<>();
        for (int i = 70000; i < 100000; i++) {
            Student student = new Student();
            student.setId(i);
            student.setClazzId(i);
            student.setMobile("122198"+i);
            student.setPassword("12345"+i);
            student.setSex(i % 2 == 0 ? "男": "女");
            student.setCreateDate(new Date());
            student.setUsername(203 + i);
            student.setNickName("測試學生" + i);
            studentList.add(student);
        }
        studentMapper.batchInsert(studentList);
    }
    @Test
    public void batchUpdateStudentTest(){
        for(int i = 3000; i < 100000; i++) {
            Student student = new Student();
            student.setAdminId(i);
            student.setId(i);
            studentMapper.updateByPrimaryKeySelective(student);
        }
    }
}
