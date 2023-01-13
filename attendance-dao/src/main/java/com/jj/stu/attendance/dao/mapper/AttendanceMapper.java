package com.jj.stu.attendance.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jj.stu.attendance.dao.model.Attendance;import com.jj.stu.attendance.dao.model.AttendanceExample;import org.apache.ibatis.annotations.Param;import java.util.List;

public interface AttendanceMapper extends BaseMapper<Attendance> {
    int deleteByPrimaryKey(Integer id);

    int insert(Attendance record);

    int insertSelective(Attendance record);

    Attendance selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Attendance record);

    int updateByPrimaryKey(Attendance record);
}