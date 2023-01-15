# 基于Servlet+Mybatis+Thymeleaf的图书管理系统

## 项目需求：

* 图书管理员的登陆和退出（只有登陆之后才能进入管理页面）
* 图书的列表浏览（包括书籍是否被借出的状态也要进行显示）以及图书的添加和删除
* 学生的列表浏览
* 查看所有的借阅列表，添加借阅信息

## 关键功能实现

### “记住我”功能：

登录成功后，服务端给客户端种下`cookie`，随后客户端的每次请求都会携带这个`cookie`。

在下一次登录时，首先判断请求是否携带了`cookie`，如果携带了则不用登录，自动跳转管理系统首页。

### 过滤器

配置全局`Filter`, 正则匹配客户端的请求路径，判断是否需要拦截。

### 代码实现流程：

Entity(Dao) -> Mapper -> Service -> ServiceImpl -> Servlet

（1）定义实体类；

（2）定义Mapper接口，使用注解的方式定义`sql`语句，配置映射规则；

（3）定义Service接口；

（4）实现Service接口；

（5）业务逻辑层，撰写对应的Servlet；

### 封装工具类

MybatisUtil、ThymeleafUtil、DateFormatUtil