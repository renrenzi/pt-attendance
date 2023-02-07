package com.jj.stu.attendance.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jj.stu.attendance.admin.service.ClazzService;
import com.jj.stu.attendance.base.exception.ApiException;
import com.jj.stu.attendance.dao.mapper.ClazzMapper;
import com.jj.stu.attendance.dao.model.Clazz;
import com.jj.stu.attendance.meta.request.ManageClazzRequest;
import com.jj.stu.attendance.meta.request.PageClazzRequest;
import com.jj.stu.attendance.meta.response.PageClazzResponse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * impl clazz服务
 *
 * @author 张俊杰
 * @date 2022/11/19
 */
@Service
public class ClazzServiceImpl extends ServiceImpl<ClazzMapper, Clazz> implements ClazzService {

    @Resource
    private ClazzMapper clazzMapper;

    @Override
    public void editClazzDetail(ManageClazzRequest request) {
        Clazz clazz = Clazz.builder()
                .id(request.getId())
                .name(request.getName())
                .info(request.getInfo())
                .state(request.getState())
                .build();
        if(clazzMapper.selectById(clazz.getId()) == null){
            clazz.setCreateTime(new Date());
            clazz.setCreateUserId(1);
            clazzMapper.insertSelective(clazz);
        }else {
            clazz.setUpdateTime(new Date());
            clazz.setUpdateUserId(1);
            clazzMapper.updateByPrimaryKeySelective(clazz);
        }
    }

    @Override
    public void batchDeleteClazzList(List<Integer> clazzIds) {
        int res = clazzMapper.deleteBatchIds(clazzIds);
        if(res < 0){
            throw new ApiException("批量删除专业列表失败");
        }
    }

    @Override
    public PageClazzResponse pageClazzList(PageClazzRequest request) {
        Clazz clazz = request.getClazz();
        QueryWrapper<Clazz> wrapper = new QueryWrapper<>();
        if(clazz != null && clazz.getName() != null){
            wrapper.lambda().like(Clazz::getName, clazz.getName());
        }
        Page<Object> page = PageHelper.startPage(request.getPageNum(), request.getPageSize());
        List<Clazz> clazzList = clazzMapper.selectList(wrapper);
        return new PageClazzResponse()
                .setClazzList(clazzList)
                .setTotalSize(page.getTotal());
    }
}
