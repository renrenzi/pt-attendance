package com.jj.stu.attendance.meta.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jj.stu.attendance.base.exception.ApiException;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 编辑课程要求
 *
 * @author LENOVO
 * @date 2022/11/22
 */
@Data
public class EditCourseRequest{

    private Integer id;

    /**
     * 课程名字
     */
    @NotNull(message = "课程名称不能为空")
    private String name;

    /**
     * 教师Id
     */
    @NotNull(message = "教师Id不能为空")
    private Integer teacherId;

    /**
     * 课程时间
     */
    @JsonFormat(pattern = "yyyy-mm-dd hh:mm:ss")
    private Date courseDate;

    /**
     * 选课人数
     */
    @NotNull(message = "选课人数不能为空")
    private Integer selectedNum;

    /**
     * 最大人数
     */
    @NotNull(message = "最大人数不能为空")
    private Integer maxNum;

    private String info;

    public void checkSelectedNumToMaxNum(){
        if(selectedNum > maxNum){
            throw new ApiException("选课人数不能大于最大人数");
        }
    }
}
