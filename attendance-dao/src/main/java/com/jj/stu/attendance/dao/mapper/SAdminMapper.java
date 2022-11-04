package com.jj.stu.attendance.dao.mapper;

import com.jj.stu.attendance.dao.model.SAdmin;
import com.jj.stu.attendance.dao.model.SAdminExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SAdminMapper {
    long countByExample(SAdminExample example);

    int deleteByExample(SAdminExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SAdmin record);

    int insertSelective(SAdmin record);

    List<SAdmin> selectByExample(SAdminExample example);

    SAdmin selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SAdmin record, @Param("example") SAdminExample example);

    int updateByExample(@Param("record") SAdmin record, @Param("example") SAdminExample example);

    int updateByPrimaryKeySelective(SAdmin record);

    int updateByPrimaryKey(SAdmin record);
}