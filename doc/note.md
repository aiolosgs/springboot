## 7.21
springboot热更新：
在pom.xml中添加如下依赖
```xml
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-devtools</artifactId>
	<optional>true</optional>
</dependency>
```

springboot接受请求参数/springboot中使用MVC

## 7.23
添加登录和用户新增

## 7.24
添加ResultVo统一接口返回格式

## 7.25
AuthFilter：
1.判断是否在白名单中，如果在，跳过后边的逻辑。
2.判断session中是否有token，如果没有，返回错误码401；如果有，继续。
3.判断请求头中是否有csrfToken，如果没有，返回错误码420；如果有，与session中的csrfToken进行匹配，不匹配，返回错误码420；匹配则通过。

登录逻辑：
1.从session中获取密钥对
2.从请求中获取登录名和密码
3.用私钥解密获得登录名和密码的明文
4.根据登录名在数据库中查找用户，如果没找到，提示用户名或密码错误
5.判断用户的密码与请求中的参数是否一致，如果不一致，提示用户名或密码错误
6.登陆成功，将用户id加密生成token保存在session中并返回给前端，生成csrfToken保存在session中

session与当前用户
https://blog.csdn.net/u014203449/article/details/91127576
https://blog.csdn.net/u014203449/article/details/83241010
https://blog.csdn.net/ahua186186/article/details/84914862

### todo
----
白名单逻辑
缓存
UserUtils
在线用户
