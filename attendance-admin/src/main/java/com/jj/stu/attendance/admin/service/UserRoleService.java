package com.jj.stu.attendance.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jj.stu.attendance.admin.basic.PageCondition;
import com.jj.stu.attendance.admin.basic.PageResult;
import com.jj.stu.attendance.base.basic.Result;
import com.jj.stu.attendance.dao.model.UserRole;

import javax.management.relation.Role;
import java.util.List;

/**
 * 用户角色Service
 *
 * @author 任人子
 * @date 2022/5/9  - {TIME}
 */
public interface UserRoleService extends IService<UserRole> {
    /**
     * 页面角色
     *
     * @param condition 条件
     * @param userRole  用户角色
     * @return {@link PageResult}<{@link UserRole}>
     */
    PageResult<UserRole> pageRole(PageCondition condition, UserRole userRole);

    /**
     * 获取所有启用角色
     *
     * @return {@link Result}<{@link List}<{@link Role}>>
     */
    Result<List<UserRole>> getAllEnableRole();
}
