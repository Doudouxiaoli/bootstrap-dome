# spring boot + mybatis + enjoy 实例

## 主要技术
 - [springboot](https://docs.spring.io/spring-boot/docs/2.1.9.RELEASE/reference/html/)
 - [mybatis](https://mybatis.org/mybatis-3/zh/index.html)
 - [enjoy](https://www.jfinal.com/doc/6-1)
 - [mybatis-plus](https://mp.baomidou.com/guide)
 - [liquibase](https://www.liquibase.org/documentation/index.html)
 - [bootstrap](https://getbootstrap.com/docs/4.3/getting-started/introduction/)
 - [WxJava](https://github.com/Wechat-Group/WxJava)

## 演示内容
 - 不同环境参数配置方式
 - mybatis-plus代码生成，执行test目录下的com.kingyee.starter.MysqlGenerator#main方法
 - 使用liquibase创建数据库并管理数据库变化
 - logback日志配置
 - 数据库增删改查
 - 事务配置
 - 定时任务
 - 拦截器配置
 - enjoy模版配置
 - 分页插件
 - 属性注入
 - enjoy模版使用
 
 ## 其他配置
 - server.servlet.context-path=/XXX
 - mybatis.mapper-locations=classpath:/mapper/*.xml 
 
 ## 微信公众号
 - 3.6.0 https://github.com/Wechat-Group/WxJava
 - 如不需要该功能删除starter.wx包即可
 
 ## 富文本编辑器
 - 基于百度的带135编辑器功能,目前使用反馈良好
 - 如不需要删除 common.baidu即可(前台js: static/libs/ueditor)
 
 
 ## changelog
 - 新增邮件支持 20191227
 