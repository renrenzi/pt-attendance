package com.jj.stu.attendance.meta.response;

import com.jj.stu.attendance.meta.dto.TeacherDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 页面老师反应
 *
 * @author 张俊杰
 * @date 2022/11/20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class PageTeacherResponse {

    /**
     * 总大小
     */
    private Long totalSize;

    private List<TeacherDTO> teacherList;
}
