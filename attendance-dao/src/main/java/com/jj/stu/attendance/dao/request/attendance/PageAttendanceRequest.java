package com.jj.stu.attendance.dao.request.attendance;

import com.jj.stu.attendance.dao.model.Attendance;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 页面出席请求
 *
 * @author 张俊杰
 * @date 2022/11/19
 */
@Data
public class PageAttendanceRequest {
    /**
     * 页面num
     */
    @NotNull
    private Integer pageNum;
    /**
     * 页面大小
     */
    @NotNull
    private Integer pageSize;

    /**
     * 出席
     */
    private Attendance attendance;
}
