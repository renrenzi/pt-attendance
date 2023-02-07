# 学生考勤管理系统的设计与实现
> 系统前台模块： 在前台包含三个子模块，前台展示模块有请假模块、签到模块、考勤模块，考勤模块主要展示学生或者教师每周的考勤情况，其中签到模块内的发布签到消息，接收签到消息，涉及到消息的推送和拉取。  

>系统后台模块： 在后台中包含八个子模块。后台管理模块包括的学生管理、教师管理、专业、课程管理、选课管理、考勤管理、请假管理和系统管理等，其中系统管理模块是对用户、角色、菜单信息进行管理，数据库使用 RBAC 的设计模式进行设计，主要通过对角色进行菜单授权从而完成对用户进行权限控制，数据可视化模块对系统的相关数据进行可视化操作。

- 后端搭建

| 后台架构         | maven_version | explain                              | link                                      |
|--------------|---------------|--------------------------------------|-------------------------------------------|
| SpringBoot   | 2.3.7.RELEASE | 简化 Spring 应用的初始搭建                    | https://spring.io/projects/spring-boot    |
| Mybatis-Plus | 3.4.0         | 为简化开发而生                              | https://baomidou.com/                     |
| Mybatis      | 2.2.0         | Java 持久化框架                           | https://mybatis.org/mybatis-3/            |
| Redis        |               | 非关系型数据库                              | https://redis.io/                         |
| Sa-token     | 1.29.0        | 一个轻量级 Java 权限认证框架                    | https://sa-token.cc/                      |
| screw        | 1.0.5         | 简洁好用的数据库表结构文档生成工具                    | https://github.com/pingfangushi/screw     |
| Easy Excel   | 3.1.5         | 基于 Java 的、快速、简洁、解决大文件内存溢出的Excel处理工具。 | https://easyexcel.opensource.alibaba.com/ |
| 阿里云oss       | 2.8.3         | 用于文件存储                               | https://www.aliyun.com/product/oss        |
| knife4j      | 2.0.4         | 增强版 API 文档                           | https://doc.xiaominfo.com/                |
| bizlog       | 3.0.3         | 通用操作日志组件                             | https://github.com/mouzt/mzt-biz-log      |

* pt-attendance
    * attendance-admin 业务模块
    * attendance-base 基础模块
    * attendance-dao  数据层模块
    * attendance-meta 元数据模块