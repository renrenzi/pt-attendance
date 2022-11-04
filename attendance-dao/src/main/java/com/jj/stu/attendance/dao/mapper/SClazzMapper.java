package com.jj.stu.attendance.dao.mapper;

import com.jj.stu.attendance.dao.model.SClazz;
import com.jj.stu.attendance.dao.model.SClazzExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SClazzMapper {
    long countByExample(SClazzExample example);

    int deleteByExample(SClazzExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SClazz record);

    int insertSelective(SClazz record);

    List<SClazz> selectByExample(SClazzExample example);

    SClazz selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SClazz record, @Param("example") SClazzExample example);

    int updateByExample(@Param("record") SClazz record, @Param("example") SClazzExample example);

    int updateByPrimaryKeySelective(SClazz record);

    int updateByPrimaryKey(SClazz record);
}