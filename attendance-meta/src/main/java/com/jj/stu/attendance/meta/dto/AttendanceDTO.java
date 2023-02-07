package com.jj.stu.attendance.meta.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = false)
public class AttendanceDTO {
    private Integer id;

    /**
     * 课程Id
     */
    private Integer courseId;
    /**
     * 课程名称
     */
    private String courseName;
    /**
     * 学生Id
     */
    private Integer studentId;
    /**
     * 学号
     */
    private String username;
    /**
     * 姓名
     */
    private String nickname;
    /**
     * 考勤状态
     */
    private String type;

    /**
     * 课程时间
     */
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
