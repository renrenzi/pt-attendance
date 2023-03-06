package com.jj.stu.attendance.admin.controller;

import com.jj.stu.attendance.admin.service.ConfigService;
import com.jj.stu.attendance.base.basic.Result;
import com.jj.stu.attendance.base.basic.ResultGenerator;
import com.jj.stu.attendance.base.constants.HttpStatusEnum;
import com.jj.stu.attendance.base.util.DateUtils;
import com.jj.stu.attendance.dao.model.Config;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 系统配置管理
 * @author 任人子
 */
@Api(tags = "ConfigController", description = "系统配置管理")
@RestController
@RequestMapping("/config")
public class ConfigController {
    @Resource
    private ConfigService configService;

    @ApiOperation("获取系统配置列表")
    @GetMapping("/getConfigList")
    public Result<List<Config>> getConfigList() {
        List<Config> ConfigList = configService.list();
        if (CollectionUtils.isEmpty(ConfigList)) {
            return ResultGenerator.getResultByHttp(HttpStatusEnum.INTERNAL_SERVER_ERROR);
        }
        return ResultGenerator.getResultByHttp(HttpStatusEnum.OK,ConfigList);
    }

    @ApiOperation("添加系统配置")
    @PostMapping("/addConfig")
    public Result<String> addConfig(Config Config) {
        if (ObjectUtils.isEmpty(Config)) {
            return ResultGenerator.getResultByHttp(HttpStatusEnum.BAD_REQUEST);
        }
        if (!configService.save(Config.setCreateTime(DateUtils.getLocalCurrentTime())
                .setUpdateTime(DateUtils.getLocalCurrentTime()))) {
            return ResultGenerator.getResultByHttp(HttpStatusEnum.INTERNAL_SERVER_ERROR);
        }
        return ResultGenerator.getResultByHttp(HttpStatusEnum.OK);
    }

    @ApiOperation("更新系统配置")
    @PostMapping("/updateConfig")
    public Result<String> updateConfig(Config Config) {
        if (ObjectUtils.isEmpty(Config)) {
            return ResultGenerator.getResultByHttp(HttpStatusEnum.BAD_REQUEST);
        }
        if (!configService.updateById(Config.setUpdateTime(DateUtils.getLocalCurrentTime()))) {
            return ResultGenerator.getResultByHttp(HttpStatusEnum.INTERNAL_SERVER_ERROR);
        }
        return ResultGenerator.getResultByHttp(HttpStatusEnum.OK);
    }

    @ApiOperation("删除系统配置")
    @PostMapping("/deleteConfig")
    public Result<String> deleteConfig(String configField){
        if (StringUtils.isEmpty(configField)){
            ResultGenerator.getResultByHttp(HttpStatusEnum.BAD_REQUEST);
        }
        if (!configService.removeById(configField)){
            ResultGenerator.getResultByHttp(HttpStatusEnum.INTERNAL_SERVER_ERROR);
        }
        return ResultGenerator.getResultByHttp(HttpStatusEnum.OK);
    }
}
