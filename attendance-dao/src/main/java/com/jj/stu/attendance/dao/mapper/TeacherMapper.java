package com.jj.stu.attendance.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jj.stu.attendance.dao.model.Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeacherMapper extends BaseMapper<Teacher> {
    int deleteByPrimaryKey(Integer id);

    int insert(Teacher record);

    int insertSelective(Teacher record);

    Teacher selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Teacher record);

    int updateByPrimaryKey(Teacher record);

    List<Teacher> selectTeacherListByIds(@Param("teacherIds") List<Integer> teacherIds);

    List<Teacher> selectByAdminId(@Param("adminIds") List<Integer> adminIds);
}