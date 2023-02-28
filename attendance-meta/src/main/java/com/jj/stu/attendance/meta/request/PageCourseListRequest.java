package com.jj.stu.attendance.meta.request;

import com.jj.stu.attendance.dao.model.Course;
import com.jj.stu.attendance.dao.model.CourseExample;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author
 */
@Data
public class PageCourseListRequest {

    @NotNull
    private Integer pageNum;
    @NotNull
    private Integer pageSize;

    private Course course;
}
