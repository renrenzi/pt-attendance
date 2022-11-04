package com.jj.stu.attendance.dao.mapper;

import com.jj.stu.attendance.dao.model.SStudent;
import com.jj.stu.attendance.dao.model.SStudentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SStudentMapper {
    long countByExample(SStudentExample example);

    int deleteByExample(SStudentExample example);

    int deleteByPrimaryKey(@Param("id") Integer id, @Param("username") Integer username);

    int insert(SStudent record);

    int insertSelective(SStudent record);

    List<SStudent> selectByExample(SStudentExample example);

    SStudent selectByPrimaryKey(@Param("id") Integer id, @Param("username") Integer username);

    int updateByExampleSelective(@Param("record") SStudent record, @Param("example") SStudentExample example);

    int updateByExample(@Param("record") SStudent record, @Param("example") SStudentExample example);

    int updateByPrimaryKeySelective(SStudent record);

    int updateByPrimaryKey(SStudent record);
}