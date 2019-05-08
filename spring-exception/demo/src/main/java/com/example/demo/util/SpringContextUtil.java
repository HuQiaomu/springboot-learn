package com.example.demo.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

/**
 * @author wh1507006
 * @date 2019/4/22 16:57
 * ioc 容器类, 通过容器获取 bean 实例
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (ObjectUtils.isEmpty(SpringContextUtil.applicationContext)) {
            SpringContextUtil.applicationContext = applicationContext;
        }
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 根据 bean 名称从 ioc 容器中获取 bean实例
     * @param beanName bean 名称
     * @return Object
     */
    public static Object getBean(String beanName) {
        return applicationContext.getBean(beanName);
    }
}
