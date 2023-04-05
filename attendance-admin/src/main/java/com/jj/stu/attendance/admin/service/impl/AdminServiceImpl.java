package com.jj.stu.attendance.admin.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jj.stu.attendance.admin.basic.PageResult;
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
import com.jj.stu.attendance.dao.model.Student;
import com.jj.stu.attendance.dao.model.Teacher;
import com.jj.stu.attendance.meta.request.MiniLoginRequest;
import com.jj.stu.attendance.meta.request.PageAdminListRequest;
import com.jj.stu.attendance.meta.request.UserLoginRequest;
import com.jj.stu.attendance.meta.response.PageAdminInfoResponse;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.function.Function;
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
    @Resource
    private ApplicationContext context;

    @Override
    public Result userLoginInfo(UserLoginRequest request) {
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
    public Result miniLoginInfo(MiniLoginRequest request) {
        request.setPassword(DigestUtil.md5Hex(request.getPassword()));
        Admin admin = adminMapper.findAdminByCondition(request.getUserName(), request.getPassword());
        if (admin == null) {
            throw new ApiException(String.format("账号或密码错误 userName:%s, roleName:%s", request.getUserName(), request.getRoleName()));
        }
        StpUtil.login(admin.getId());
        String token = StpUtil.getTokenValue();

        return ResultGenerator.getResultByOk(token);
    }

    @Override
    public Result<PageResult<PageAdminInfoResponse>> pageAdminInfoList(PageAdminListRequest request) {
        Page<Object> page = PageHelper.startPage(request.getPageNum(), request.getPageSize());
        List<Admin> adminList = adminMapper.selectListByCondition(request.getUserName(), request.getCreateTime());
        List<Integer> adminIds = adminList.stream().map(Admin::getId).distinct().collect(Collectors.toList());
        Integer roleId = request.getRoleId();

        Map<Integer, Teacher> adminIdToTeacherMap = new HashMap<>();
        Map<Integer, Student> adminIdToStudentMap = new HashMap<>();
        if (roleId != null) {
            if (RoleNameEnum.STUDENT.getRoleId().equals(roleId)) {
                adminIdToStudentMap = studentMapper.selectByAdminId(adminIds).stream().collect(Collectors.toMap(Student::getAdminId, Function.identity(), (v2, v1) -> v1));
            }
            if (RoleNameEnum.TEACHER.getRoleId().equals(roleId)) {
                adminIdToTeacherMap = teacherMapper.selectByAdminId(adminIds).stream().collect(Collectors.toMap(Teacher::getAdminId, Function.identity(), (v2, v1) -> v1));
            }
        }
        List<PageAdminInfoResponse> result = new ArrayList<>();
        for (Admin admin : adminList) {
            PageAdminInfoResponse pageAdminInfoResponse = new PageAdminInfoResponse<>();
            BeanUtil.copyProperties(admin, pageAdminInfoResponse);
            if (roleId != null) {
                if (RoleNameEnum.STUDENT.getRoleId().equals(roleId)) {
                    pageAdminInfoResponse.setInfo(adminIdToStudentMap.get(admin.getId()));
                }
                if (RoleNameEnum.TEACHER.getRoleId().equals(roleId)) {
                    pageAdminInfoResponse.setInfo(adminIdToTeacherMap.get(admin.getId()));
                }
                pageAdminInfoResponse.setRoleName(Objects.requireNonNull(RoleNameEnum.getRole(roleId)).getRoleName());
            }
            pageAdminInfoResponse.setRoleId(roleId);
            result.add(pageAdminInfoResponse);
        }
        PageResult<PageAdminInfoResponse> pageResult = new PageResult<>();
        pageResult.setData(result);
        pageResult.setTotalSize(page.getTotal());
        return ResultGenerator.getResultByOk(pageResult);
    }
}
