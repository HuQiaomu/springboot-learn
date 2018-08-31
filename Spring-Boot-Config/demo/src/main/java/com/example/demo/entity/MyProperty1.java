package com.example.demo.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author alex_hu
 * @date 18-8-30 下午4:03
 */

@Component //将 bean 注入到 spring 容器中
@ConfigurationProperties(prefix = "my1")
public class MyProperty1 {

//    @Value("${my1.age}")
    private int age;
//    @Value("${my1.name}")
    private String name;

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "MyProperty1{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
