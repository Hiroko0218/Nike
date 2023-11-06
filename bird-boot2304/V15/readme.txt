独立完成登录模块

步骤:
1:创建登录相关的页面
    1.1:login.html页面,表单要求两个输入框分别为用户名和密码
        表单action="/loginUser"
    1.2:login_info_error页面,当用户登录信息输入有误(空着不填等)时提示该页面
        页面居中显示一行字:登录信息输入有误,请重新登录
    1.3:login_success.html  登录成功提示页面
    1.4:login_fail.html 登录失败提示页面

2:在UserController中定义用于处理登录业务的方法:login
  方法定义参考reg方法
3:在DispatcherServlet的判断注册业务下面添加一个else if分支,判断请求路径path的值是否为"/loginUser"
  从而调用登录方法处理登录业务
