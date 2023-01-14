package com.jj.stu.attendance.admin.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.jj.stu.attendance.admin.constants.RoleNameEnum;
import com.jj.stu.attendance.admin.service.AdminService;
import com.jj.stu.attendance.base.basic.Result;
import com.jj.stu.attendance.base.basic.ResultGenerator;
import com.jj.stu.attendance.base.exception.ApiException;
import com.jj.stu.attendance.base.service.RedisService;
import com.jj.stu.attendance.dao.mapper.AdminMapper;
import com.jj.stu.attendance.dao.mapper.StudentMapper;
import com.jj.stu.attendance.dao.mapper.TeacherMapper;
import com.jj.stu.attendance.dao.model.Admin;
import com.jj.stu.attendance.meta.request.MiniLoginRequest;
import com.jj.stu.attendance.meta.request.PageAdminListRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {
    @Resource
    private AdminMapper adminMapper;
    @Resource
    private StudentMapper studentMapper;
    @Resource
    private TeacherMapper teacherMapper;
    @Resource
    private RedisService redisService;
    @Override
    public Result miniLoginInfo(MiniLoginRequest request) {
        request.setPassword(DigestUtil.md5Hex(request.getPassword()));
        Admin admin = adminMapper.findAdminByCondition(request.getUserName(), request.getPassword());
        if (admin == null) {
            throw new ApiException("账号或密码错误");
        }
        StpUtil.login(admin.getId());
        String token = StpUtil.getTokenValue();
        
        return ResultGenerator.getResultByOk(token);
    }

    @Override
    public Result pageAdminInfoList(PageAdminListRequest request) {
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        List<Admin> adminList = adminMapper.selectListByCondition(request.getUserName(), request.getCreateTime());
        List<Integer> adminIds = adminList.stream().map(Admin::getId).distinct().collect(Collectors.toList());
        Integer roleId = request.getRoleId();
        if (roleId != null){
            if (RoleNameEnum.STUDENT.getRoleId().equals(roleId)){
                teacherMapper.selectByAdminId(adminIds);
            }
            if (RoleNameEnum.TEACHER.getRoleId().equals(roleId)){
                studentMapper.selectByAdminId(adminIds);
            }
        }
        return null;
    }
}
