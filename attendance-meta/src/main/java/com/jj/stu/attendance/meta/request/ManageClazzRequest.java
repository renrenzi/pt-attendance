package com.jj.stu.attendance.meta.request;

import com.jj.stu.attendance.dao.model.Clazz;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 管理clazz请求
 *
 * @author LENOVO
 * @date 2022/12/02
 */
@Data
public class ManageClazzRequest {

    /**
     * 主键id
     */
    private Integer id;

    /**
     * 专业名称
     */
    @NotNull
    private String name;

    /**
     * 专业描述
     */
    private String info;

    /**
     * 1->启用 0 ->禁用
     */
    private Integer state;
}
