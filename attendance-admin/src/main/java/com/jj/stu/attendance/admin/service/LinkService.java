package com.jj.stu.attendance.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jj.stu.attendance.admin.basic.PageCondition;
import com.jj.stu.attendance.admin.basic.PageResult;
import com.jj.stu.attendance.base.basic.Result;
import com.jj.stu.attendance.dao.model.Link;

/**
 * 友链Service
 *
 * @author 任人子
 */
public interface LinkService extends IService<Link> {


    /**
     * 页面链接列表
     *
     * @param condition 条件
     * @param link      链接
     * @return {@link Result}<{@link PageResult}<{@link Link}>>
     */
    Result<PageResult<Link>> pageLinkList(PageCondition<Link> condition, Link link);
}
