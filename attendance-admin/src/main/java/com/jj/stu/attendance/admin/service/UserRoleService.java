package com.jj.stu.attendance.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jj.stu.attendance.admin.basic.PageCondition;
import com.jj.stu.attendance.admin.basic.PageResult;
import com.jj.stu.attendance.dao.model.UserRole;

/**
 * 用户角色Service
 * @author 任人子
 * @date 2022/5/9  - {TIME}
 */
public interface UserRoleService extends IService<UserRole> {
    /**
     * 页面作用
     *
     * @param condition 条件
     * @param userRole  用户角色
     * @return {@link Object}
     */
    PageResult<UserRole> pageRole(PageCondition condition, UserRole userRole);
}
