# UserCMS
这是一个用户权限管理系统

数据库文件暂时不上传



        框架：Spring MVC + Spring + Mybatis

        数据库：Oracle

        工具：idea

        其他：spring security + pagehelper

数据库设计：

        用户表

        角色表

        资源权限表

        用户表-角色表（多对多）

        角色表-资源权限表（多对多）

        订单表

        产品表

        旅客表


介绍：
        整个项目使用SSM框架来完成，利用三层架构MVC思想，表现层springmvc，业务逻辑层使用spring，持久层mybatis，并且使用spring整合springmvc和 spring整个mybatis，使用spring来管理mybatis，所有的sql使用注解开发，spring用来减少耦合，使用大量的依赖注入。

模块：
        
        用户模块：登陆管理系统和使用管理系统，管理用户

        角色模块：赋予用户角色，得到不同的资源权限

        资源权限模块：赋予角色，用来限制用户可以操作的范围，具体使用security来设置方法访问限制

        订单模块：可以查询旅客和产品和订单的所有信息（目的是使用@Results来进行多表查询，详细---结合旅客+产品+订单多表查询）

        产品模块：管理产品

所有项目难点：

        1.分页查询：结合pagehelper来解决，前端使用el+jstl来完成分页获取参数

        2.删除涉及外键的条目：需要获取指定条目的id来拿到所有外键，删除所有有关的外键之后，再删除指定条目

        3.订单的详情查询：涉及多表查询，需要@Results来解决多个对象的封装，此时直接调用其他Dao类的查询方法，来封装多个对象

        4.权限控制：security来对所有访问的方法控制，是否对某个角色显示需要用security:authentication来控制标签是否显示

