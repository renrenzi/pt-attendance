package com.jj.stu.attendance.dao.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class LeaveVO {

    private Integer id;

    @ApiModelProperty(value = "学生Id")
    private Integer studentId;

    private String info;

    @ApiModelProperty(value = "请假状态")
    private Boolean status;

    private String remark;

    @ApiModelProperty(value = "创建时间")
    private Date createDate;

    private Integer userName;
}
