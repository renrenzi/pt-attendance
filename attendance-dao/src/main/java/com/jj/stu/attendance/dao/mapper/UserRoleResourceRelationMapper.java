package com.jj.stu.attendance.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jj.stu.attendance.dao.model.UserRoleResourceRelation;

import java.util.List;

public interface UserRoleResourceRelationMapper extends BaseMapper<UserRoleResourceRelation> {

    int deleteByPrimaryKey(Integer id);

    int insert(UserRoleResourceRelation record);

    int insertSelective(UserRoleResourceRelation record);

    UserRoleResourceRelation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserRoleResourceRelation record);

    int updateByPrimaryKey(UserRoleResourceRelation record);

    int deleteByList(List<UserRoleResourceRelation> roleResourceRelationList);
}