# ideploy
中小型公司的代码部署系统（开发中）

模块：

	ideploy-web     //管理页面  controller service dao
	ideploy-core    //核心功能  编译逻辑、部署逻辑、编译日志、部署日志、启动停止日志
	ideploy-plugin  //插件功能  模板加载、模板渲染、扩展模板
	ideploy-common  //公共功能  数据库，redis，util，配置

Web模板：

    http://ace.jeka.by/index.html
    https://coreui.io/demo/#colors.html
    
命名规范:

    html网页里的id和class，不使用驼峰，有的浏览器不敏感，类似xx-xx-xx。
    http://getbem.com/naming/

热部署debug:
    
    IntelliJ IDEA 在spring-boot热加载需要执行如下配置
    https://www.cnblogs.com/yjmyzz/p/use-devtools-of-spring-boot-framework.html
    
