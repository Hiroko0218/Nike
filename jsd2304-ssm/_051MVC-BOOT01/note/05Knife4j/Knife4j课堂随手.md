

### 1. 前后端联调（API文档）

### 2. Lombok

* 自动生成 `@Setter() @Getter() @ToString() @AllArgsConstructor @NoArgsConstructor @Data` ，简化工程代码，提高开发效率

* 日志注解：`@Slf4j` ，根据实际需求指定多种日志级别，既可以帮助开发者调试，又可以节约系统资源

  TRACE   <  DEBUG  <  INFO[默认级别] < WARN < ERROR









* 生成API文档
* 在线调试接口（**Controller中方法**）





1. pom.xml中添加Knife4j依赖，刷新maven
2. 创建配置文件 Knife4jConfig，指定生成API文档的包路径
3. 重启工程
4. 浏览器访问：http://localhost:8080/doc.html





* 确认所有的DTO中，是否添加 `@ApiModelProperty()` 注解
* 确认所有的控制器方法中，是否添加 `@ApiOperation()` 注解
* 确认所有控制器类中，是否添加 `@Api(tags="")` 注解
* 确认微博模块和评论模块中，声明参数接收的方法，是否添加 `@ApiImplicitParam()` 或 `@ApiImplicitParams(value = {})` 注解



* 用户模块：3个功能  `@ApiIgore` 注解

* 微博模块：发布微博

* 评论模块：发布评论

  重启工程，Knife4j页面确认





## 电商网站

* 用户模块：`10100 ~ 10199`
* 商品模块：`10200 ~ 10299`
* 购物车模块：`10300 ~ 10399`
* 订单模块：`10400 ~ 10499`
* 支付模块：`10500 ~ 10599`





* 封装 JsonResult，定义相关注解和构造方法；
* 修改注册功能返回值，并重启工程；
* 刷新Knife4j页面测试；
* 修改登录功能再次测试；



* 首先实现注册功能 JsonResult 封装
* 然后实现登录功能 JsonResult 封装
* 最后思考 获取当前用户功能 如何实现？





* 把微博模块所有控制器方法，统一封装返回；
* 把评论模块所有控制器方法，统一封装返回；







* 创建工程，并做好相关配置后启动工程

* 实现用户模块的：注册、登录、获取当前用户功能

* POJO类使用：Lombok

* 日志级别要求：

  * 全局：WARN
  * 工程目录：DEBUG

  调试过程中不能使用打印，只能使用 log.debug("xxxx")；

* 配置Knife4j，进行测试（做常规的注解配置）

* 统一响应结果的封装（自定义枚举状态码、定义静态方法 - 有数据返回和无数据返回）





























