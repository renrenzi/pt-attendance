package com.jj.stu.attendance.dao.response.selectedCourse;

import com.jj.stu.attendance.dao.model.Clazz;
import com.jj.stu.attendance.dao.model.SelectedCourse;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class PageSelectedCourseResponse {
    List<SelectedCourse> selectedCourseList;

    Integer totalSize;
}
