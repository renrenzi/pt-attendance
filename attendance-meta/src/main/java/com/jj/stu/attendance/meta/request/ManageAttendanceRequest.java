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
    @NotNull
    private String type;

    /**
     * 课程时间
     */
    @NotNull
    /*@JsonFormat(pattern = "yyyy-mm-dd hh:mm:ss")*/
    private Date date;

    /**
     * 用户id
     */
    private Integer userId;

}
