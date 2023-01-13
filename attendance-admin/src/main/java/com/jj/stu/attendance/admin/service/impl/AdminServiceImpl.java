package com.jj.stu.attendance.admin.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.crypto.digest.Digester;
import cn.hutool.crypto.digest.MD5;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jj.stu.attendance.admin.service.AdminService;
import com.jj.stu.attendance.base.basic.Result;
import com.jj.stu.attendance.base.basic.ResultGenerator;
import com.jj.stu.attendance.base.exception.ApiException;
import com.jj.stu.attendance.base.service.RedisService;
import com.jj.stu.attendance.dao.mapper.AdminMapper;
import com.jj.stu.attendance.dao.model.Admin;
import com.jj.stu.attendance.dao.request.MiniLoginRequest;
import com.jj.stu.attendance.dao.request.PageAdminListRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {
    @Resource
    private AdminMapper adminMapper;
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
        return null;
    }
}
