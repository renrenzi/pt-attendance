package com.jj.stu.attendance.meta.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class LeaveDTO {

    private Integer id;

    @ApiModelProperty(value = "学生Id")
    private Integer studentId;

    private String info;

    @ApiModelProperty(value = "请假状态")
    private Boolean status;

    private String remark;

    @ApiModelProperty(value = "创建时间")
    private Date createDate;
    /**
     * 学号
     */
    private String userName;
}
