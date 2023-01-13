package com.jj.stu.attendance.dao.request;

import com.jj.stu.attendance.dao.model.Clazz;
import com.sun.istack.internal.NotNull;
import lombok.Data;

@Data
public class PageClazzRequest {
    @NotNull
    private Integer pageNum;

    @NotNull
    private Integer pageSize;

    private Clazz clazz;
}
