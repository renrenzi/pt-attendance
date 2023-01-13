package com.jj.stu.attendance.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jj.stu.attendance.dao.model.Leave;
import com.jj.stu.attendance.meta.request.ManageLeaveRequest;
import com.jj.stu.attendance.meta.request.PageLeaveRequest;
import com.jj.stu.attendance.meta.response.PageLeaveResponse;

import java.util.List;

/**
 * 离开服务
 *
 * @author 张俊杰
 * @date 2022/11/20
 */
public interface LeaveService extends IService<Leave> {
    /**
     * 页面离开列表
     *
     * @param request 请求
     * @return {@link PageLeaveResponse}
     */
    PageLeaveResponse pageLeaveList(PageLeaveRequest request);

    /**
     * 批量删除请假列表
     * @param leaveIds
     */
    void batchDeleteLeaveList(List<Integer> leaveIds);

    /**
     * 修改请假信息
     * @param request
     */
    void updateLeaveInfo(ManageLeaveRequest request);
}
