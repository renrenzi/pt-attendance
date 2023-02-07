package com.jj.stu.attendance.meta.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class ManageAttendanceRequest {

    private Integer id;

    /**
     * 课程Id
     */
    @NotNull
    private Integer courseId;

    /**
     * 学生Id
     */
    @NotNull
    private Integer studentId;

    /**
     * 考勤状态
     */
    private String type;

    /**
     * 课程时间
     */
    @NotNull
    private Date date;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建用户id
     */
    private Integer createUserId;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 更新用户id
     */
    private Integer updateUserId;
}
