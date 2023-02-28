package com.jj.stu.attendance.meta.request;

import com.jj.stu.attendance.dao.model.Clazz;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PageClazzRequest {
    @NotNull
    private Integer pageNum;

    @NotNull
    private Integer pageSize;

    private Clazz clazz;
}
