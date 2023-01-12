package com.jj.stu.attendance.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jj.stu.attendance.admin.service.LeaveService;
import com.jj.stu.attendance.base.exception.ApiException;
import com.jj.stu.attendance.dao.dto.LeaveVO;
import com.jj.stu.attendance.dao.mapper.LeaveMapper;
import com.jj.stu.attendance.dao.mapper.StudentMapper;
import com.jj.stu.attendance.dao.model.Leave;
import com.jj.stu.attendance.dao.model.SelectedCourse;
import com.jj.stu.attendance.dao.model.Student;
import com.jj.stu.attendance.dao.request.leave.ManageLeaveRequest;
import com.jj.stu.attendance.dao.request.leave.PageLeaveRequest;
import com.jj.stu.attendance.dao.response.leave.PageLeaveResponse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.data.util.Pair.toMap;

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
    @Resource
    private StudentMapper studentMapper;
    @Override
    public void updateLeaveInfo(ManageLeaveRequest request) {
        Leave leave = request.getLeave();
        int result;
        if(leaveMapper.selectById(leave.getId()) == null){
            result = leaveMapper.insertSelective(leave);
        }else {
            result = leaveMapper.updateByPrimaryKeySelective(leave);
        }
        if (result != 1) {
            throw new ApiException("修改请假信息失败");
        }
    }

    @Override
    public void batchDeleteLeaveList(List<Integer> leaveIds) {
        leaveMapper.deleteBatchIds(leaveIds);
    }

    @Override
    public PageLeaveResponse pageLeaveList(PageLeaveRequest request) {
        Page<Object> page = PageHelper.startPage(request.getPageNum(), request.getPageSize());
        List<Leave> leaveList = leaveMapper.selectList(new QueryWrapper<>());
        List<Integer> studentIds = leaveList.stream().map(Leave::getStudentId).collect(Collectors.toList());
        Map<Integer, Integer> map = studentMapper.selectBatchIds(studentIds).stream().collect(Collectors.toMap(Student::getId, Student::getUsername, (v2, v1) -> v1));
        List<LeaveVO> responseList = new ArrayList<>();
        for(Leave leave : leaveList) {
            LeaveVO leaveVO = new LeaveVO();
            BeanUtil.copyProperties(leave, leaveVO);
            leaveVO.setUserName(map.get(leave.getStudentId()));
            responseList.add(leaveVO);
        }
        return new PageLeaveResponse()
                .setLeaveList(responseList)
                .setTotalSize(page.getTotal());
    }
}
