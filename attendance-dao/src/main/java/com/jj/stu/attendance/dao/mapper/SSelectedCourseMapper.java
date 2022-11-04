package com.jj.stu.attendance.dao.mapper;

import com.jj.stu.attendance.dao.model.SSelectedCourse;
import com.jj.stu.attendance.dao.model.SSelectedCourseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SSelectedCourseMapper {
    long countByExample(SSelectedCourseExample example);

    int deleteByExample(SSelectedCourseExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SSelectedCourse record);

    int insertSelective(SSelectedCourse record);

    List<SSelectedCourse> selectByExample(SSelectedCourseExample example);

    SSelectedCourse selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SSelectedCourse record, @Param("example") SSelectedCourseExample example);

    int updateByExample(@Param("record") SSelectedCourse record, @Param("example") SSelectedCourseExample example);

    int updateByPrimaryKeySelective(SSelectedCourse record);

    int updateByPrimaryKey(SSelectedCourse record);
}