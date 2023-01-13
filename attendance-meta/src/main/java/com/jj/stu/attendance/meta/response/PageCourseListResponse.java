package com.jj.stu.attendance.meta.response;

import com.jj.stu.attendance.dao.dto.PageCourseDto;
import lombok.Data;

import java.util.List;

@Data
public class PageCourseListResponse {
    List<PageCourseDto> pageCourseDtoList;
    Long totalSize;
}
