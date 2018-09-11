#示例参考：　http://www.iocoder.cn/Spring-Boot/battcn/v2-nosql-redis/
#redis 设置密码：https://mp.weixin.qq.com/s/0rkyDbQL7AnR5qlYRpjP9Q

学习目标：
1.redis 的存值，取值，删除
2.redis 设置 key 过期时间


spring boot 使用 redis 做缓存（缓存注解)
#示例参考：　http://www.iocoder.cn/Spring-Boot/battcn/v2-cache-redis/
#缓存注解其他参考：　https://blog.csdn.net/weixin_37651459/article/details/81125970


总结：
1. 自定义 cacheManager 用于配置 缓存注解方式设置缓存的各种配置信息
2. 自定义 redisTemplate 用于配置　直接使用　redisTemplate 存放信息到缓存的各种配置信息