package com.jj.stu.attendance.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jj.stu.attendance.base.basic.Result;
import com.jj.stu.attendance.dao.model.Admin;
import com.jj.stu.attendance.meta.request.MiniLoginRequest;
import com.jj.stu.attendance.meta.request.PageAdminListRequest;
import com.jj.stu.attendance.meta.response.PageAdminInfoResponse;

import java.util.List;

/**
 * 管理服务
 *
 * @author 张俊杰
 * @date 2023/01/14
 */
public interface AdminService extends IService<Admin> {
    /**
     * 前台登录
     * @param request
     * @return
     */
    Result miniLoginInfo(MiniLoginRequest request);

    /**
     * 分页获取用户列表
     *
     * @param request 请求
     * @return {@link Result}
     */
    Result<List<PageAdminInfoResponse>> pageAdminInfoList(PageAdminListRequest request);
}
