package com.jj.stu.attendance.dao.mapper;

import com.jj.stu.attendance.dao.model.SAttendance;
import com.jj.stu.attendance.dao.model.SAttendanceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SAttendanceMapper {
    long countByExample(SAttendanceExample example);

    int deleteByExample(SAttendanceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SAttendance record);

    int insertSelective(SAttendance record);

    List<SAttendance> selectByExample(SAttendanceExample example);

    SAttendance selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SAttendance record, @Param("example") SAttendanceExample example);

    int updateByExample(@Param("record") SAttendance record, @Param("example") SAttendanceExample example);

    int updateByPrimaryKeySelective(SAttendance record);

    int updateByPrimaryKey(SAttendance record);
}