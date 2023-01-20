package com.jj.stu.attendance.meta.response;

import com.jj.stu.attendance.meta.dto.PageCourseDTO;
import lombok.Data;

import java.util.List;

@Data
public class PageCourseListResponse {
    List<PageCourseDTO> pageCourseDtoList;
    Long totalSize;
}
