package com.jj.stu.attendance.dao.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 打卡请求 request
 *
 * @author renrenzi
 * @date 2023/01/12
 */
@Data
public class PunchTheClockRequest {


    /**
     * 管理员id
     */
    @NotNull
    private Integer adminId;

    /**
     * 课程id
     */
    @NotNull
    private Integer courseId;

    /**
     * 打卡时间
     */
    @NotNull
    private Date  clockingTime;

    /**
     * 角色类型
     */
    private Integer roleType;
}
