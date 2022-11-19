package com.jj.stu.attendance.admin.controller;

import com.jj.stu.attendance.dao.model.Student;
import com.jj.stu.attendance.dao.request.student.StudentBatchInsertRequest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * @author 任人子
 * @date 2022/11/5  - {TIME}
 */
@SpringBootTest
public class StudentControllerTest {
    @Resource
    private StudentController controller;
    @Test
    public void test(){
        StudentBatchInsertRequest request = new StudentBatchInsertRequest();

        request.setStudentList(new ArrayList<Student>(){{
            Student student = new Student();
            student.setClazzId(1);
            student.setUsername(2901);
            student.setPassword("1231");
            student.setMobile("123");
            student.setNickName("张三");
            student.setSex("女");
            add(student);
        }});
        controller.batchAddStudent(request);
    }
}
