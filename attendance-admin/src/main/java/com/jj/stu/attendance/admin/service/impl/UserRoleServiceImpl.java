package com.jj.stu.attendance.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jj.stu.attendance.admin.service.UserRoleService;
import com.jj.stu.attendance.dao.mapper.UserRoleMapper;
import com.jj.stu.attendance.dao.model.UserRole;
import org.springframework.stereotype.Service;

/**
 * 用户角色ServiceImpl
 * @author 任人子
 * @date 2022/5/9  - {TIME}
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {
}
