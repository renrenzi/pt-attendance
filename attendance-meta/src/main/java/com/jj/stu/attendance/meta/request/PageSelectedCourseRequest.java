package com.jj.stu.attendance.meta.request;

import com.jj.stu.attendance.dao.model.SelectedCourse;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PageSelectedCourseRequest {

    @NotNull
    private Integer pageNum;
    @NotNull
    private Integer pageSize;

    private SelectedCourse selectedCourse;
}
