package com.jj.stu.attendance.admin.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.jj.stu.attendance.base.basic.Result;
import com.jj.stu.attendance.dao.model.Attendance;
import com.jj.stu.attendance.meta.request.ManageAttendanceRequest;
import com.jj.stu.attendance.meta.request.PageAttendanceRequest;
import com.jj.stu.attendance.meta.request.PunchTheClockRequest;
import com.jj.stu.attendance.meta.response.PageAttendanceResponse;

import java.util.List;

/**
 * 出勤服务
 *
 * @author 张俊杰
 * @date 2022/11/19
 */
public interface AttendanceService extends IService<Attendance> {
    /**
     * 分页获取考勤列表
     *
     * @param request 请求
     * @return {@link PageAttendanceResponse}
     */
    PageAttendanceResponse pageAttendanceList(PageAttendanceRequest request);

    /**
     * 批量删除考勤列表
     *
     * @param attendanceIds 出勤ID
     */
    void batchDeleteAttendanceList(List<Integer> attendanceIds);

    /**
     * 更新出勤信息
     *
     * @param request 要求
     */
    void updateAttendanceInfo(ManageAttendanceRequest request);

    /**
     * 打卡
     *
     * @param request 要求
     * @return {@link Result}
     */
    Result punchTheClock(PunchTheClockRequest request);
}
