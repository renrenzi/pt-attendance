package com.jj.stu.attendance.dao.response;

import com.jj.stu.attendance.dao.dto.SelectedCourseDTO;
import com.jj.stu.attendance.dao.model.Clazz;
import com.jj.stu.attendance.dao.model.SelectedCourse;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class PageSelectedCourseResponse {
    List<SelectedCourseDTO> selectedCourseList;

    Long totalSize;
}
