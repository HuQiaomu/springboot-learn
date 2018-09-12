package com.example.demo.intercepter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author alex_hu
 * @date 18-9-12 下午1:58
 */
/*
 * 需要config配置类配合，看config目录
 * 路径只有走DispatcherServlet，才会被拦截，默认静态资源不会被拦截
 */
@Slf4j
public class TestInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        log.info("[interceptor preHandle] - 没进　controller 之前调用");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        log.info("[interceptor postHandle] - 请求处理之后进行调用，但是在视图被渲染之前，即Controller方法调用之后");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        log.info("[interceptor afterCompletion] - 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行，主要是用于进行资源清理工作");
    }
}
