package com.jj.stu.attendance.dao.dto;

import com.jj.stu.attendance.dao.model.Course;
import lombok.Data;

@Data
public class PageCourseDto extends Course {

    private String teacherName;
}
