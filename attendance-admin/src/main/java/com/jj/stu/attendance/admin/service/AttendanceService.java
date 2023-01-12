package com.jj.stu.attendance.admin.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.jj.stu.attendance.dao.model.Attendance;
import com.jj.stu.attendance.dao.request.attendance.ManageAttendanceRequest;
import com.jj.stu.attendance.dao.request.attendance.PageAttendanceRequest;
import com.jj.stu.attendance.dao.response.attendance.PageAttendanceResponse;

import java.util.List;

/**
 * 参加服务
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

    void batchDeleteAttendanceList(List<Integer> attendanceIds);

    void updateAttendanceInfo(ManageAttendanceRequest request);
}
