package com.jj.stu.attendance.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jj.stu.attendance.admin.service.AttendanceService;
import com.jj.stu.attendance.dao.mapper.AttendanceMapper;
import com.jj.stu.attendance.dao.model.Attendance;
import com.jj.stu.attendance.dao.request.attendance.PageAttendanceRequest;
import com.jj.stu.attendance.dao.response.attendance.PageAttendanceResponse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 出席服务impl
 *
 * @author 张俊杰
 * @date 2022/11/19
 */
@Service
public class AttendanceServiceImpl extends ServiceImpl<AttendanceMapper, Attendance> implements AttendanceService {
    @Resource
    private AttendanceMapper attendanceMapper;
    @Override
    public PageAttendanceResponse pageAttendanceList(PageAttendanceRequest request) {
        Page<Object> page = PageHelper.startPage(request.getPageNum(), request.getPageSize());
        List<Attendance> attendanceList = attendanceMapper.selectList(new QueryWrapper<>());
        return new PageAttendanceResponse()
                .setTotalSize(page.getTotal())
                .setAttendanceList(attendanceList);
    }
}
