package com.jj.stu.attendance.dao.mapper;

import com.jj.stu.attendance.dao.model.STeacher;
import com.jj.stu.attendance.dao.model.STeacherExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface STeacherMapper {
    long countByExample(STeacherExample example);

    int deleteByExample(STeacherExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(STeacher record);

    int insertSelective(STeacher record);

    List<STeacher> selectByExample(STeacherExample example);

    STeacher selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") STeacher record, @Param("example") STeacherExample example);

    int updateByExample(@Param("record") STeacher record, @Param("example") STeacherExample example);

    int updateByPrimaryKeySelective(STeacher record);

    int updateByPrimaryKey(STeacher record);
}