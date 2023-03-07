package com.jj.stu.attendance.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jj.stu.attendance.dao.model.UserResource;

public interface UserResourceMapper extends BaseMapper<UserResource> {

    int deleteByPrimaryKey(Integer id);

    int insert(UserResource record);

    int insertSelective(UserResource record);

    UserResource selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserResource record);

    int updateByPrimaryKey(UserResource record);
}