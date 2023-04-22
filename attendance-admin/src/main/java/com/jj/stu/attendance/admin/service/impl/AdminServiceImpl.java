package com.jj.stu.attendance.admin.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jj.stu.attendance.admin.basic.PageResult;
import com.jj.stu.attendance.admin.constants.RoleNameEnum;
import com.jj.stu.attendance.admin.service.AdminService;
import com.jj.stu.attendance.base.basic.Result;
import com.jj.stu.attendance.base.basic.ResultGenerator;
import com.jj.stu.attendance.base.basic.StpUserDetail;
import com.jj.stu.attendance.base.exception.ApiException;
import com.jj.stu.attendance.base.service.RedisService;
import com.jj.stu.attendance.dao.mapper.AdminMapper;
import com.jj.stu.attendance.dao.mapper.StudentMapper;
import com.jj.stu.attendance.dao.mapper.TeacherMapper;
import com.jj.stu.attendance.dao.model.Admin;
import com.jj.stu.attendance.dao.model.Student;
import com.jj.stu.attendance.dao.model.Teacher;
import com.jj.stu.attendance.meta.request.EditAdminInfoRequest;
import com.jj.stu.attendance.meta.request.MiniLoginRequest;
import com.jj.stu.attendance.meta.request.PageAdminListRequest;
import com.jj.stu.attendance.meta.request.UserLoginRequest;
import com.jj.stu.attendance.meta.response.PageAdminInfoResponse;
import com.jj.stu.attendance.meta.response.UserLoginResponse;
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
    public Result<UserLoginResponse> userLoginInfo(UserLoginRequest request) {
        request.setPassword(DigestUtil.md5Hex(request.getPassword()));
        Admin admin = adminMapper.findAdminByCondition(request.getUserName(), request.getPassword());
        if (admin == null) {
            throw new ApiException("账号或密码错误");
        }
        StpUtil.login(admin.getId());
        // todo 将用户信息到redis中
        return ResultGenerator.getResultByOk(new UserLoginResponse(StpUtil.getTokenValue(), buildStpUserDetail(admin, null)));
    }

    @Override
    public Result<UserLoginResponse> miniLoginInfo(MiniLoginRequest request) {
        request.setPassword(DigestUtil.md5Hex(request.getPassword()));
        Admin admin = adminMapper.findAdminByCondition(request.getUserName(), request.getPassword());
        if (admin == null) {
            throw new ApiException(String.format("账号或密码错误 userName:%s, roleName:%s", request.getUserName(), request.getRoleName()));
        }
        StpUtil.login(admin.getId());
        // todo 将用户信息到redis中
        return ResultGenerator.getResultByOk(new UserLoginResponse(StpUtil.getTokenValue(), buildStpUserDetail(admin, request.getRoleName())));
    }

    /**
     * 构建用户详情
     */
    private StpUserDetail buildStpUserDetail(Admin admin, String roleName) {

        if (Objects.nonNull(admin)) {
            StpUserDetail detail = new StpUserDetail();
            if (RoleNameEnum.STUDENT.getRoleName().equals(roleName)) {
                Student student = studentMapper.selectOne(new QueryWrapper<Student>().lambda().eq(Student::getAdminId, admin.getId()));
                if (Objects.nonNull(student)) {
                    detail.setUserId(student.getId());
                    detail.setUsername(student.getUsername());
                }
            } else if (RoleNameEnum.TEACHER.getRoleName().equals(roleName)) {
                Teacher teacher = teacherMapper.selectOne(new QueryWrapper<Teacher>().lambda().eq(Teacher::getAdminId, admin.getId()));
                if (Objects.nonNull(teacher)) {
                    detail.setUserId(teacher.getId());
                    detail.setNickName(teacher.getUsername());
                }
            }
            detail.setAdminId(admin.getId());
            detail.setUsername(admin.getUserName());
            return detail;
        }
        return null;
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
                    if (!adminIdToStudentMap.containsKey(admin.getId())){
                        continue;
                    }
                    pageAdminInfoResponse.setInfo(adminIdToStudentMap.get(admin.getId()));
                }
                if (RoleNameEnum.TEACHER.getRoleId().equals(roleId)) {
                    if (!adminIdToTeacherMap.containsKey(admin.getId())){
                        continue;
                    }
                    pageAdminInfoResponse.setInfo(adminIdToTeacherMap.get(admin.getId()));
                }
                pageAdminInfoResponse.setRoleName(Objects.requireNonNull(RoleNameEnum.getRole(roleId)).getRoleName());
            }
            pageAdminInfoResponse.setRoleId(roleId);
            result.add(pageAdminInfoResponse);
        }
        PageResult<PageAdminInfoResponse> pageResult = new PageResult<>();
        pageResult.setData(result);
        pageResult.setTotalSize((long) (RoleNameEnum.STUDENT.getRoleId().equals(roleId) ? adminIdToStudentMap.size() : adminIdToTeacherMap.size()));
        return ResultGenerator.getResultByOk(pageResult);
    }

    @Override
    public String editAdminInfo(EditAdminInfoRequest request) {
        Admin admin = adminMapper.selectByPrimaryKey(request.getAdminId());
        if (Objects.isNull(admin)) {
            throw new ApiException("修改失败, 未查询到账号信息");
        }
        admin.setPassWord(DigestUtil.md5Hex(request.getPassword()));
        int res = adminMapper.updateByPrimaryKey(admin);
        if (res != 1) {
            throw new ApiException("修改失败");
        }
        return null;
    }

}
