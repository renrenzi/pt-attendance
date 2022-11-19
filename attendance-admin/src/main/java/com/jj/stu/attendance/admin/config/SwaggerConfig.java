package com.jj.stu.attendance.admin.config;

import com.jj.stu.attendance.base.config.BaseSwaggerConfig;
import com.jj.stu.attendance.base.domain.SwaggerProperties;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 配置
 * Admin swagger 配置
 *
 * @author 张俊杰
 * @date 2022/11/19
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig extends BaseSwaggerConfig {
    @Override
    public SwaggerProperties swaggerProperties() {
        return SwaggerProperties.builder()
                .apiBasePackage("com.jj.stu.attendance.admin.comtroller")
                .title("Admin后台管理中心")
                .description("Admin后台管理中心相关接口文档")
                .version("1.0")
                .contactName("renrenzi")
                .enableSecurity(false)
                .build();
    }
}
