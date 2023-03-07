package com.jj.stu.attendance.admin.controller;

import com.jj.stu.attendance.admin.basic.PageCondition;
import com.jj.stu.attendance.admin.basic.PageResult;
import com.jj.stu.attendance.admin.constants.DeleteStatusEnum;
import com.jj.stu.attendance.admin.constants.LinkConstants;
import com.jj.stu.attendance.admin.service.LinkService;
import com.jj.stu.attendance.base.basic.Result;
import com.jj.stu.attendance.base.basic.ResultGenerator;
import com.jj.stu.attendance.base.constants.HttpStatusEnum;
import com.jj.stu.attendance.base.util.DateUtils;
import com.jj.stu.attendance.dao.model.Link;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * 链接控制器
 *
 * @author 张俊杰
 * @date 2023/03/06
 */
@Api(tags = "linkController", description = "友链管理")
@RestController
@RequestMapping("/link")
public class LinkController {
    @Resource
    private LinkService linkService;

    @ApiOperation(value = "获取链接类型列表")
    @GetMapping("/getLinkTypeList")
    public Result<List<Link>> getLinkTypeList() {
        List<Link> result = new ArrayList<Link>();
        result.add(new Link().setLinkType(LinkConstants.LINK_TYPE_FRIENDSHIP.getLinkTypeId())
                .setLinkName(LinkConstants.LINK_TYPE_FRIENDSHIP.getLinkTypeName()));
        result.add(new Link().setLinkType(LinkConstants.LINK_TYPE_RECOMMENDED.getLinkTypeId())
                .setLinkName(LinkConstants.LINK_TYPE_RECOMMENDED.getLinkTypeName()));
        result.add(new Link().setLinkType(LinkConstants.LINK_TYPE_PRIVATE.getLinkTypeId())
                .setLinkName(LinkConstants.LINK_TYPE_PRIVATE.getLinkTypeName()));
        return ResultGenerator.getResultByHttp(HttpStatusEnum.OK, result);
    }

    @ApiOperation(value = "分页查询链接列表")
    @PostMapping("/pageLinkList")
    public Result<PageResult<Link>> pageLinkList(PageCondition<Link> condition, Link Link) {
        if (StringUtils.isEmpty(condition.getPageNum()) || StringUtils.isEmpty(condition.getPageSize())) {
            return ResultGenerator.getResultByHttp(HttpStatusEnum.BAD_REQUEST);
        }
        return linkService.pageLinkList(condition, Link);
    }

    @ApiOperation(value = "添加链接")
    @PostMapping("/addLink")
    public Result<String> addLink(Link Link) {
        if (ObjectUtils.isEmpty(Link)) {
            return ResultGenerator.getResultByHttp(HttpStatusEnum.BAD_REQUEST);
        }
        Link.setCreateTime(DateUtils.getLocalCurrentTime())
                .setIsDeleted(DeleteStatusEnum.NOT_DELETED.getStatus());
        if (!linkService.save(Link)) {
            return ResultGenerator.getResultByHttp(HttpStatusEnum.INTERNAL_SERVER_ERROR);
        }
        return ResultGenerator.getResultByHttp(HttpStatusEnum.OK);
    }

    @ApiOperation(value = "修改删除状态")
    @PostMapping("/isDel")
    public Result<String> isDel(Link Link) {
        if (ObjectUtils.isEmpty(Link)) {
            return ResultGenerator.getResultByHttp(HttpStatusEnum.BAD_REQUEST);
        }
        if (!linkService.updateById(Link)) {
            return ResultGenerator.getResultByHttp(HttpStatusEnum.INTERNAL_SERVER_ERROR);
        }
        return ResultGenerator.getResultByHttp(HttpStatusEnum.OK);
    }

    @ApiOperation(value = "清除链接信息")
    @PostMapping("/clearLink")
    public Result<String> clearLink(Integer linkId) {
        if (StringUtils.isEmpty(linkId)) {
            return ResultGenerator.getResultByHttp(HttpStatusEnum.BAD_REQUEST);
        }
        if (!linkService.removeById(linkId)) {
            return ResultGenerator.getResultByHttp(HttpStatusEnum.INTERNAL_SERVER_ERROR);
        }
        return ResultGenerator.getResultByHttp(HttpStatusEnum.OK);
    }

    @ApiOperation(value = "获取链接")
    @PostMapping("/getLink")
    public Result<Link> getLink(Integer linkId) {
        if (StringUtils.isEmpty(linkId)) {
            return ResultGenerator.getResultByHttp(HttpStatusEnum.BAD_REQUEST);
        }
        Link Link = linkService.getById(linkId);
        if (ObjectUtils.isEmpty(Link)) {
            return ResultGenerator.getResultByHttp(HttpStatusEnum.INTERNAL_SERVER_ERROR);
        }
        return ResultGenerator.getResultByHttp(HttpStatusEnum.OK, Link);
    }

    @ApiOperation(value = "修改链接信息")
    @PostMapping("/editLink")
    public Result<String> editLinkOrSave(Link Link) {
        if (ObjectUtils.isEmpty(Link)) {
            return ResultGenerator.getResultByHttp(HttpStatusEnum.BAD_REQUEST);
        }
        boolean flag;
        if (Link.getLinkId() == null) {
            flag = linkService.save(Link.setCreateTime(DateUtils.getLocalCurrentTime()));
        } else {
            flag = linkService.updateById(Link);
        }
        if (!flag) {
            return ResultGenerator.getResultByHttp(HttpStatusEnum.INTERNAL_SERVER_ERROR);
        }
        return ResultGenerator.getResultByHttp(HttpStatusEnum.OK);
    }

}
