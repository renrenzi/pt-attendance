package com.jj.stu.attendance.dao.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SelectedCourseDTO {

    private Integer id;

    @ApiModelProperty(value = "学生Id")
    private Integer studentId;
    /**
     * 学号
     */
    private Integer userName;
    /**
     * 昵称
     */
    private String nickName;

    @ApiModelProperty(value = "课程Id")
    private Integer courseId;
    /**
     * 课程名称
     */
    private String courseName;

    /**
     * 选课人数
     */
    private Integer selectedNum;

    /**
     * 最大人数
     */
    private Integer maxNum;
}
