package com.jj.stu.attendance.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jj.stu.attendance.dao.model.Teacher;
import com.jj.stu.attendance.dao.model.TeacherExample;

import java.util.Arrays;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TeacherMapper extends BaseMapper<Teacher> {
    long countByExample(TeacherExample example);

    int deleteByExample(TeacherExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Teacher record);

    int insertSelective(Teacher record);

    List<Teacher> selectByExample(TeacherExample example);

    Teacher selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Teacher record, @Param("example") TeacherExample example);

    int updateByExample(@Param("record") Teacher record, @Param("example") TeacherExample example);

    int updateByPrimaryKeySelective(Teacher record);

    int updateByPrimaryKey(Teacher record);

    List<Teacher> selectTeacherListByIds(@Param("teacherIds") List<Integer> teacherIds);
}