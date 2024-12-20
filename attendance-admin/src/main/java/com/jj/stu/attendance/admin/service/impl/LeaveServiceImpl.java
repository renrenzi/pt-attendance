package com.jj.stu.attendance.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jj.stu.attendance.admin.service.LeaveService;
import com.jj.stu.attendance.base.exception.ApiException;
import com.jj.stu.attendance.meta.dto.LeaveDTO;
import com.jj.stu.attendance.dao.mapper.LeaveMapper;
import com.jj.stu.attendance.dao.mapper.StudentMapper;
import com.jj.stu.attendance.dao.model.Leave;
import com.jj.stu.attendance.dao.model.Student;
import com.jj.stu.attendance.meta.request.ManageLeaveRequest;
import com.jj.stu.attendance.meta.request.PageLeaveRequest;
import com.jj.stu.attendance.meta.response.PageLeaveResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

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
        Leave leave = coverToEntity(request);
        int result;
        if (leaveMapper.selectById(leave.getId()) == null) {
            leave.setCreateDate(new Date());
            result = leaveMapper.insertSelective(leave);
        } else {
            result = leaveMapper.updateByPrimaryKeySelective(leave);
        }
        if (result != 1) {
            throw new ApiException("修改请假信息失败");
        }
    }

    private Leave coverToEntity(ManageLeaveRequest request) {
        return new Leave().setId(request.getId())
                .setInfo(request.getInfo())
                .setStatus(request.getStatus())
                .setRemark(request.getRemark())
                .setStudentId(request.getStudentId())
                .setCreateDate(request.getCreateDate());
    }

    @Override
    public void batchDeleteLeaveList(List<Integer> leaveIds) {
        leaveMapper.deleteBatchIds(leaveIds);
    }

    @Override
    public PageLeaveResponse pageLeaveList(PageLeaveRequest request) {
        Page<Object> page = PageHelper.startPage(request.getPageNum(), request.getPageSize());
        List<Leave> leaveList = leaveMapper.selectList(buildLeaveQueryWrapper(request));
        List<Integer> studentIds = leaveList.stream().map(Leave::getStudentId).distinct().collect(Collectors.toList());
        Map<Integer, Student> map = new HashMap<>(studentIds.size());
        if (!CollectionUtils.isEmpty(studentIds)) {
            map = studentMapper.selectBatchIds(studentIds).stream().collect(Collectors.toMap(Student::getId, Function.identity(), (v2, v1) -> v1));
        }
        List<LeaveDTO> responseList = new ArrayList<>();
        for (Leave leave : leaveList) {
            LeaveDTO leaveVO = new LeaveDTO();
            BeanUtil.copyProperties(leave, leaveVO);
            Student student = map.getOrDefault(leave.getStudentId(), new Student());
            leaveVO.setUserName(student.getUsername());
            leaveVO.setNickname(student.getNickName());
            responseList.add(leaveVO);
        }
        return new PageLeaveResponse()
                .setLeaveList(responseList)
                .setTotalSize(page.getTotal());
    }

    /**
     * 构建请假查询包装
     *
     * @param request 请求
     * @return {@link QueryWrapper}
     */
    private QueryWrapper buildLeaveQueryWrapper(PageLeaveRequest request) {
        QueryWrapper<Leave> queryWrapper = new QueryWrapper<>();
        Leave leave = request.getLeave();
        if (leave != null) {
            if (leave.getStudentId() != null) {
                queryWrapper.lambda().eq(Leave::getStudentId, leave.getStudentId());
            }
        }
        queryWrapper.lambda().orderByDesc(Leave::getCreateDate);
        return queryWrapper;
    }
}
