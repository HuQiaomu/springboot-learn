package com.example.demo.listener;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @author alex_hu
 * @date 18-9-12 下午3:07
 */
/**
 * 需要主类加入
 * @ServletComponentScan
 */
@Slf4j
@WebListener
public class TestListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        log.info("[Listener] - init");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        log.info("[listener] - destroy");
    }
}
