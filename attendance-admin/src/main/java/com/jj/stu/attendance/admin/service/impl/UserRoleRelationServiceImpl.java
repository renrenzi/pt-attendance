package com.jj.stu.attendance.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jj.stu.attendance.admin.service.UserRoleRelationService;
import com.jj.stu.attendance.dao.mapper.UserRoleRelationMapper;
import com.jj.stu.attendance.dao.model.UserRoleRelation;
import org.springframework.stereotype.Service;

/**
 * 用户角色关系ServiceImpl
 *
 * @author 任人子
 * @date 2022/5/9  - {TIME}
 */
@Service
public class UserRoleRelationServiceImpl extends ServiceImpl<UserRoleRelationMapper, UserRoleRelation> implements UserRoleRelationService {
}
