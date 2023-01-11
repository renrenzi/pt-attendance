package com.jj.stu.attendance.dao.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SelectedCourseDTO {

    private Integer id;

    @ApiModelProperty(value = "学生Id")
    private Integer studentId;

    private Integer userName;

    private String nickName;

    @ApiModelProperty(value = "课程Id")
    private Integer courseId;

    private String courseName;
}
