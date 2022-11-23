package com.jj.stu.attendance.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jj.stu.attendance.admin.service.LeaveService;
import com.jj.stu.attendance.dao.mapper.LeaveMapper;
import com.jj.stu.attendance.dao.model.Leave;
import com.jj.stu.attendance.dao.request.leave.PageLeaveRequest;
import com.jj.stu.attendance.dao.response.leave.PageLeaveResponse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 离开服务impl
 *
 * @author 张俊杰
 * @date 2022/11/20
 */
@Service
public class LeaveServiceImpl extends ServiceImpl<LeaveMapper, Leave> implements LeaveService {

    @Resource
    private LeaveMapper leaveMapper;
    @Override
    public PageLeaveResponse pageLeaveList(PageLeaveRequest request) {
        Page<Object> page = PageHelper.startPage(request.getPageNum(), request.getPageSize());
        List<Leave> leaveList = leaveMapper.selectList(new QueryWrapper<>());
        return new PageLeaveResponse()
                .setLeaveList(leaveList)
                .setTotalSize(page.getTotal());
    }
}
