package com.jj.stu.attendance.dao.response.leave;

import com.jj.stu.attendance.dao.model.Leave;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 页面离开响应
 *
 * @author 张俊杰
 * @date 2022/11/20
 */
@Data
@Accessors(chain = true)
public class PageLeaveResponse {

    /**
     * 总大小
     */
    private Integer totalSize;

    /**
     * 离开列表
     */
    private List<Leave> leaveList;
}
