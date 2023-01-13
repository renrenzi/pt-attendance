package com.jj.stu.attendance.meta.request;

import com.jj.stu.attendance.dao.model.Course;
import com.jj.stu.attendance.dao.model.CourseExample;
import com.sun.istack.internal.NotNull;
import lombok.Data;

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
