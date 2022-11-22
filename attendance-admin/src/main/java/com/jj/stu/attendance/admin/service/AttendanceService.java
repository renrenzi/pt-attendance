package com.jj.stu.attendance.admin.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.jj.stu.attendance.dao.model.Attendance;
import com.jj.stu.attendance.dao.request.attendance.PageAttendanceRequest;
import com.jj.stu.attendance.dao.response.attendance.PageAttendanceResponse;

/**
 * 参加服务
 *
 * @author 张俊杰
 * @date 2022/11/19
 */
public interface AttendanceService extends IService<Attendance> {
    /**
     * 页面出席名单
     *
     * @param request 请求
     * @return {@link PageAttendanceResponse}
     */
    PageAttendanceResponse pageAttendanceList(PageAttendanceRequest request);
}
