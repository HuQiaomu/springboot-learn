package com.example.demo.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author alex_hu
 * @date 18-9-12 下午2:55
 */
/**
 * 会过滤到地址栏为以下参数的请求
 * 需要主类加入@ServletComponentScan
 */
@Slf4j
@WebFilter(filterName = "testFilter", urlPatterns = {
        "/test/*", "/hello/*"
})
public class TestFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("[Filter] - init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String requestUrl = req.getRequestURI();
        log.info("[Filter] - doFilter({})", requestUrl);
        //需要调用，否则不会向下执行了
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        log.info("[Filter] - destroy");
    }
}
