package com.alex.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * 使用 外部 tomcat 部署 springboot 应用，注意 pom.xml 中的配置
 */
@SpringBootApplication
public class SpringbootTomcatApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//		return super.configure(builder);
		return builder.sources(SpringbootTomcatApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootTomcatApplication.class, args);
	}

}
