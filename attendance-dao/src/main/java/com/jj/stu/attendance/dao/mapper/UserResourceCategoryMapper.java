package com.jj.stu.attendance.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jj.stu.attendance.dao.model.UserResourceCategory;

public interface UserResourceCategoryMapper extends BaseMapper<UserResourceCategory> {


    int deleteByPrimaryKey(Integer id);

    int insert(UserResourceCategory record);

    int insertSelective(UserResourceCategory record);

    UserResourceCategory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserResourceCategory record);

    int updateByPrimaryKey(UserResourceCategory record);
}