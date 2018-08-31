package com.example.demo.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author alex_hu
 * @date 18-8-30 下午4:37
 */
@Component
@PropertySource("classpath:my2.yml")
@ConfigurationProperties(prefix = "my2")
public class MyProperty2 {

//    @Value("${my2.age}")
    private int age;
    private String name;
//    @Value("${my2.desc}")
    private String desc;

//    @Value("${my2.javaHome}")
    private String javaHome;


    @Bean
    public static PropertySourcesPlaceholderConfigurer properties()
    {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        YamlPropertiesFactoryBean yml = new YamlPropertiesFactoryBean();
        //引入自定义的yml配置文件，暴露给spring environment
        yml.setResources(new FileSystemResource("src/main/resources/my2.yml"));
        configurer.setProperties(yml.getObject());
        return configurer;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getJavaHome() {
        return javaHome;
    }

    public void setJavaHome(String javaHome) {
        this.javaHome = javaHome;
    }

    @Override
    public String toString() {
        return "MyProperty2{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", javaHome='" + javaHome + '\'' +
                '}';
    }
}
