### 注册功能:

- 修改reg.html页面,点击注册按钮时发出请求
- 创建User,UserVO,UserRegDTO,UserMapper.java,UserMapper.xml, UserController
- 在工程中把boot01工程里面的Security相关内容复制到baking工程
  - 在pom.xml添加Security依赖
  - 把security包copy到新工程
  - 将SecurityConfig里面的密码编码器改成BCryptPasswordEncoder

### 登录功能:

- 修改login.html页面, 点击登录按钮时发出请求
- 在UserController中添加login方法处理请求
- 把boot01工程中的全局异常处理代码复制到新工程