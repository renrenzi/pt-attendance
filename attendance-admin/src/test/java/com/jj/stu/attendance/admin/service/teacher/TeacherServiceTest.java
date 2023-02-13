package com.jj.stu.attendance.admin.service.teacher;

import com.jj.stu.attendance.admin.BaseTest;
import com.jj.stu.attendance.admin.service.TeacherService;
import com.jj.stu.attendance.dao.model.Teacher;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TeacherServiceTest extends BaseTest {
    @Resource
    private TeacherService teacherService;
    @Test
    public void batchAddTeacher(){
        List<Teacher> teacherList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Teacher teacher = new Teacher();
            teacher.setId(i);
            teacher.setMobile("12321" +i);
            teacher.setSex(i % 2 == 0 ? "男" : "女");
            teacher.setPassword("123456");
            teacher.setUsername("测试教师" +i);
            teacher.setClazzId(i);
            teacher.setCreateDate(new Date());
            teacher.setAdminId(i);
            teacherList.add(teacher);
        }
        teacherService.saveBatch(teacherList);

    }
}
