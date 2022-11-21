package com.jj.stu.attendance.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jj.stu.attendance.dao.model.UserResourceCategory;
import com.jj.stu.attendance.dao.model.UserResourceCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserResourceCategoryMapper extends BaseMapper<UserResourceCategory> {
    long countByExample(UserResourceCategoryExample example);

    int deleteByExample(UserResourceCategoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserResourceCategory record);

    int insertSelective(UserResourceCategory record);

    List<UserResourceCategory> selectByExample(UserResourceCategoryExample example);

    UserResourceCategory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserResourceCategory record, @Param("example") UserResourceCategoryExample example);

    int updateByExample(@Param("record") UserResourceCategory record, @Param("example") UserResourceCategoryExample example);

    int updateByPrimaryKeySelective(UserResourceCategory record);

    int updateByPrimaryKey(UserResourceCategory record);
}