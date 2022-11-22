package com.jj.stu.attendance.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jj.stu.attendance.dao.model.Clazz;
import com.jj.stu.attendance.dao.request.clazz.PageClazzRequest;
import com.jj.stu.attendance.dao.response.clazz.PageClazzResponse;

public interface ClazzService extends IService<Clazz> {
    /**
     * 页面clazz列表
     *
     * @param request 请求
     * @return {@link PageClazzResponse}
     */
    PageClazzResponse pageClazzList(PageClazzRequest request);
}
