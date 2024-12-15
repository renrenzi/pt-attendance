package com.jj.stu.attendance.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jj.stu.attendance.admin.basic.PageCondition;
import com.jj.stu.attendance.admin.basic.PageResult;
import com.jj.stu.attendance.admin.service.LinkService;
import com.jj.stu.attendance.base.basic.Result;
import com.jj.stu.attendance.base.basic.ResultGenerator;
import com.jj.stu.attendance.base.constants.HttpStatusEnum;
import com.jj.stu.attendance.dao.mapper.LinkMapper;
import com.jj.stu.attendance.dao.model.Link;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 友链ServiceImpl
 *
 * @author 任人子
 * @date 2023/03/06
 */
@Service
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {

    @Resource
    private LinkMapper linkMapper;

    @Override
    public Result<PageResult<Link>> pageLinkList(PageCondition<Link> condition, Link link) {
        QueryWrapper<Link> query = new QueryWrapper<>(link);
        query.lambda().orderByDesc(Link::getLinkRank);
        Page<Link> page = new Page<>(condition.getPageNum(), condition.getPageSize());
        linkMapper.selectPage(page, query);
        List<Link> records = page.getRecords();
        if (CollectionUtils.isEmpty(records)) {
            return ResultGenerator.getResultByHttp(HttpStatusEnum.OK, new PageResult<>(2000, null, 0L, null));
        }
        PageResult<Link> result = new PageResult<>();
        result.setData(records);
        result.setTotalSize(page.getTotal());
        return ResultGenerator.getResultByHttp(HttpStatusEnum.OK, result);
    }
}
