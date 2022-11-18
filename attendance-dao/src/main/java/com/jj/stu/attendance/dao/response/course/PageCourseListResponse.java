package com.jj.stu.attendance.dao.response.course;

import com.jj.stu.attendance.dao.model.Course;
import lombok.Data;

import java.util.List;

@Data
public class PageCourseListResponse {
    List<Course> courseList;
    Integer totalSize;
}
