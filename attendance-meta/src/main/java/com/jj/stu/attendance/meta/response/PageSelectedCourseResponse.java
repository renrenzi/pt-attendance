package com.jj.stu.attendance.meta.response;

import com.jj.stu.attendance.meta.dto.SelectedCourseDTO;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class PageSelectedCourseResponse {
    List<SelectedCourseDTO> selectedCourseList;

    Long totalSize;
}
