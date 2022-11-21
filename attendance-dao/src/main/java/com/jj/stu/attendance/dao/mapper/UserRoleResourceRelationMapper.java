package com.jj.stu.attendance.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jj.stu.attendance.dao.model.UserRoleResourceRelation;
import com.jj.stu.attendance.dao.model.UserRoleResourceRelationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserRoleResourceRelationMapper extends BaseMapper<UserRoleResourceRelation> {
    long countByExample(UserRoleResourceRelationExample example);

    int deleteByExample(UserRoleResourceRelationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserRoleResourceRelation record);

    int insertSelective(UserRoleResourceRelation record);

    List<UserRoleResourceRelation> selectByExample(UserRoleResourceRelationExample example);

    UserRoleResourceRelation selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserRoleResourceRelation record, @Param("example") UserRoleResourceRelationExample example);

    int updateByExample(@Param("record") UserRoleResourceRelation record, @Param("example") UserRoleResourceRelationExample example);

    int updateByPrimaryKeySelective(UserRoleResourceRelation record);

    int updateByPrimaryKey(UserRoleResourceRelation record);

    int deleteByList(List<UserRoleResourceRelation> roleResourceRelationList);
}