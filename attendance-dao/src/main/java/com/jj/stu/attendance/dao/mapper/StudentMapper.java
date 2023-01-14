package com.jj.stu.attendance.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jj.stu.attendance.dao.model.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentMapper extends BaseMapper<Student> {
    int deleteByPrimaryKey(@Param("id") Integer id, @Param("username") Integer username);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(@Param("id") Integer id, @Param("username") Integer username);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);

    List<Student> selectByAdminId(@Param("adminIds") List<Integer> adminIds);
}