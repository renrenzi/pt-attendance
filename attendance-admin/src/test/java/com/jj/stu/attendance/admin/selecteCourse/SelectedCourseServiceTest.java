package com.jj.stu.attendance.admin.selecteCourse;

import com.jj.stu.attendance.admin.BaseTest;
import com.jj.stu.attendance.admin.service.SelectedCourseService;
import com.jj.stu.attendance.dao.model.SelectedCourse;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class SelectedCourseServiceTest extends BaseTest {
    @Resource
    private SelectedCourseService selectedCourseService;
    @Test
    public void batchAddSelectedCourse(){
        List<SelectedCourse> selectedCourseList = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            SelectedCourse selectedCourse = new SelectedCourse();
            selectedCourse.setId(i);
            selectedCourse.setCourseId(i);
            selectedCourse.setStudentId(i);
            selectedCourseList.add(selectedCourse);
        }
        selectedCourseService.saveBatch(selectedCourseList);
    }
}
