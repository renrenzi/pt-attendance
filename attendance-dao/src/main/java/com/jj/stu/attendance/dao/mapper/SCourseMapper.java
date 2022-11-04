package com.jj.stu.attendance.dao.mapper;

import com.jj.stu.attendance.dao.model.SCourse;
import com.jj.stu.attendance.dao.model.SCourseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SCourseMapper {
    long countByExample(SCourseExample example);

    int deleteByExample(SCourseExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SCourse record);

    int insertSelective(SCourse record);

    List<SCourse> selectByExample(SCourseExample example);

    SCourse selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SCourse record, @Param("example") SCourseExample example);

    int updateByExample(@Param("record") SCourse record, @Param("example") SCourseExample example);

    int updateByPrimaryKeySelective(SCourse record);

    int updateByPrimaryKey(SCourse record);
}