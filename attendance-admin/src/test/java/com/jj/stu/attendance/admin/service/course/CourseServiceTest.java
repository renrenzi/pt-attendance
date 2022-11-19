package com.jj.stu.attendance.admin.service.course;

import com.jj.stu.attendance.admin.BaseTest;
import com.jj.stu.attendance.dao.mapper.CourseMapper;
import com.jj.stu.attendance.dao.model.Course;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CourseServiceTest extends BaseTest {
    @Resource
    private CourseMapper courseMapper;
    @Test
    public void batchAddCourse(){
        List<Course> courseList = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            Course course = new Course();
            course.setId(i);
            course.setName("課程"+i);
            course.setMaxNum(50);
            course.setTeacherId(i);
            course.setCourseDate(new Date());
            course.setSelectedNum(i);
            course.setInfo("test 添加課程" + i);
            courseList.add(course);
            courseMapper.insert(course);
        }

    }
}
