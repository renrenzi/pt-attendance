package com.jj.stu.attendance.dao.request.selectedCourse;

import com.jj.stu.attendance.dao.model.SelectedCourse;
import com.sun.istack.internal.NotNull;
import lombok.Data;

@Data
public class PageSelectedCourseRequest {

    @NotNull
    private Integer pageNum;
    @NotNull
    private Integer pageSize;

    private SelectedCourse selectedCourse;
}
