package com.jj.stu.attendance.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jj.stu.attendance.admin.service.UserRoleResourceRelationService;
import com.jj.stu.attendance.dao.mapper.UserRoleResourceRelationMapper;
import com.jj.stu.attendance.dao.model.UserRoleResourceRelation;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 资源角色关系ServiceImpl
 * @author 任人子
 * @date 2022/5/12  - {TIME}
 */
@Service
public class UserRoleResourceRelationServiceImpl extends ServiceImpl<UserRoleResourceRelationMapper, UserRoleResourceRelation> implements UserRoleResourceRelationService {
    @Resource
    private UserRoleResourceRelationMapper relationMapper;
    @Override
    public int deleteByList(List<UserRoleResourceRelation> roleResourceRelationList) {
        return relationMapper.deleteByList(roleResourceRelationList);
    }
}
