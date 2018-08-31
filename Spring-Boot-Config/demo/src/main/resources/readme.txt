#自定义属性注意事项
1.application 添加注解　@EnableConfigurationProperties
2.属性类添加注解　@Component //将 bean 注入到 spring 容器中
3.属性类　属性需要添加 getter/setter 方法才能从　自定义属性中将获取的值设置进去(setter)
    getter 将整个类返回才能有值

#自定义属性文件
1.
#自定义属性文件获取不到值问题
https://blog.csdn.net/ignorewho/article/details/80666957