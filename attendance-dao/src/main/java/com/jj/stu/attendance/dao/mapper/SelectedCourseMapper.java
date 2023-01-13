package com.jj.stu.attendance.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jj.stu.attendance.dao.model.SelectedCourse;import com.jj.stu.attendance.dao.model.SelectedCourseExample;import org.apache.ibatis.annotations.Param;import java.util.List;

public interface SelectedCourseMapper extends BaseMapper<SelectedCourse> {
    int deleteByPrimaryKey(Integer id);

    int insert(SelectedCourse record);

    int insertSelective(SelectedCourse record);

    SelectedCourse selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SelectedCourse record);

    int updateByPrimaryKey(SelectedCourse record);
}