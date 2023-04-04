package com.jj.stu.attendance.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jj.stu.attendance.dao.model.Clazz;
import com.jj.stu.attendance.meta.request.ManageClazzRequest;
import com.jj.stu.attendance.meta.request.PageClazzRequest;
import com.jj.stu.attendance.meta.response.PageClazzResponse;

import java.util.List;

public interface ClazzService extends IService<Clazz> {
    /**
     * 页面clazz列表
     *
     * @param request 请求
     * @return {@link PageClazzResponse}
     */
    PageClazzResponse pageClazzList(PageClazzRequest request);

    /**
     * 批量删除clazz列表
     *
     * @param clazzIds clazz id
     */
    void batchDeleteClazzList(List<Integer> clazzIds);

    /**
     * 编辑clazz详细信息
     *
     * @param request 请求
     */
    void editClazzDetail(ManageClazzRequest request);

    /**
     * 获取所有专业列表
     *
     * @return {@link Object}
     */
    List<Clazz> getAllClazzList();
}
