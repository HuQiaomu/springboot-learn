https://www.imooc.com/video/16960

参考：
shiro + springboot + jwt : https://www.jianshu.com/p/3c51832f1051

shiro + springboot : https://www.jianshu.com/p/ef0a82d471d2
                    https://blog.csdn.net/ityouknow/article/details/73836159


shiro 内置过滤器
anon, authBasic, authc, user, logout
perms,  roles, ssl, port

shiro 会话管理
SessionManager, SessionDao
Redis 实现 Session 共享
Redis 实现 Session 共享存在的问题

https://blog.csdn.net/madonghyu/article/details/81673795
https://blog.csdn.net/lianyuecheng/article/details/81663269
https://www.cnblogs.com/zeng1994/p/03303c805731afc9aa9c60dbbd32a323.html

https://www.cnblogs.com/tongxuping/p/7210146.html

https://blog.csdn.net/liuchuanhong1/article/details/76690682?utm_source=gold_browser_extension

自定义 SessionManager 依赖 SessionDao (自定义 session 的创建，更新等，可以指定 session 存放位置）
 -- SessionDao 设置到 SessionManager, SessionManager 设置到 SecurityManager

shiro 缓存管理
CacheManager, Cache
Redis 实现 CacheManager

shiro 自动登录
shiro Remeber me
 -- CookieRemeberMeManager
        -- cookie -> SimpleCookie (maxAge)
 SecurityManager 设置 cookieRemeberMeManager

注意： Remembered和Authenticated两者是互斥的，若一个为true，则另一个为false，反之亦然
       Remembered: 开启 shiro rememberMe, Authenticated 通过 subject.login 登录

 remember me 作用： https://www.cnblogs.com/wq3435/p/6275779.html


 注解
 @Component 和 @Bean
两者都是往 ioc 容器注入 bean

@Bean 用于方法上，用在 被 @Configuration 注解的类上，
一般用于第三方类的注入，因为你不能在第三方类上加入 @Component

@Component 用于自己项目中 类的注入
