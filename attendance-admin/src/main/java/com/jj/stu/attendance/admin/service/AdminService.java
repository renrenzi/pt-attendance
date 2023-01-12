package com.jj.stu.attendance.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jj.stu.attendance.base.basic.Result;
import com.jj.stu.attendance.dao.model.Admin;
import com.jj.stu.attendance.dao.request.MiniLoginRequest;

public interface AdminService extends IService<Admin> {
    /**
     * 前台登录
     * @param request
     * @return
     */
    Result miniLoginInfo(MiniLoginRequest request);
}
