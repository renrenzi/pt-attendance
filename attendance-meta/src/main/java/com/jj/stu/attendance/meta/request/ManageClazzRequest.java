package com.jj.stu.attendance.meta.request;

import com.jj.stu.attendance.dao.model.Clazz;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 管理clazz请求
 *
 * @author LENOVO
 * @date 2022/12/02
 */
@Data
public class ManageClazzRequest {
    @NotNull
    private Clazz clazz;
}
