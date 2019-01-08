# ideploy
中小型公司的代码部署系统（开发中）

模块：

	ideploy-web     //管理页面 vue页面
	ideploy-log     //日志管理  编译日志、部署日志、启动停止日志
	ideploy-core    //核心功能 controller service dao  编译逻辑、部署逻辑、模板加载、
	ideploy-common  //公共功能  数据库，redis，util，配置

Web模板：

    http://ace.jeka.by/index.html
    https://coreui.io/demo/#colors.html
    
命名规范:

    html网页里的id和class，不使用驼峰，有的浏览器不敏感，类似xx-xx-xx。
    http://getbem.com/naming/
