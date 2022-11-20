package com.jj.stu.attendance.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jj.stu.attendance.dao.model.Leave;
import com.jj.stu.attendance.dao.request.leave.PageLeaveRequest;
import com.jj.stu.attendance.dao.response.leave.PageLeaveResponse;

/**
 * 离开服务
 *
 * @author 张俊杰
 * @date 2022/11/20
 */
public interface LeaveService extends IService<Leave> {
    PageLeaveResponse pageLeaveList(PageLeaveRequest request);
}
