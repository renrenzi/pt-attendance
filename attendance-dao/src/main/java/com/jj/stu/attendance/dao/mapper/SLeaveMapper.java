package com.jj.stu.attendance.dao.mapper;

import com.jj.stu.attendance.dao.model.SLeave;
import com.jj.stu.attendance.dao.model.SLeaveExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SLeaveMapper {
    long countByExample(SLeaveExample example);

    int deleteByExample(SLeaveExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SLeave record);

    int insertSelective(SLeave record);

    List<SLeave> selectByExample(SLeaveExample example);

    SLeave selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SLeave record, @Param("example") SLeaveExample example);

    int updateByExample(@Param("record") SLeave record, @Param("example") SLeaveExample example);

    int updateByPrimaryKeySelective(SLeave record);

    int updateByPrimaryKey(SLeave record);
}