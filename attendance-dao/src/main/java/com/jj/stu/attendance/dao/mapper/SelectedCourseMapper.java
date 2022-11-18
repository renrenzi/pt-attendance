package com.jj.stu.attendance.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jj.stu.attendance.dao.model.SelectedCourse;
import com.jj.stu.attendance.dao.model.SelectedCourseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SelectedCourseMapper extends BaseMapper<SelectedCourse> {
    long countByExample(SelectedCourseExample example);

    int deleteByExample(SelectedCourseExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SelectedCourse record);

    int insertSelective(SelectedCourse record);

    List<SelectedCourse> selectByExample(SelectedCourseExample example);

    SelectedCourse selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SelectedCourse record, @Param("example") SelectedCourseExample example);

    int updateByExample(@Param("record") SelectedCourse record, @Param("example") SelectedCourseExample example);

    int updateByPrimaryKeySelective(SelectedCourse record);

    int updateByPrimaryKey(SelectedCourse record);
}