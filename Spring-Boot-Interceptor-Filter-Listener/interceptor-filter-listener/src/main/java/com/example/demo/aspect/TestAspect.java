package com.example.demo.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author alex_hu
 * @date 18-9-12 下午3:11
 */

@Slf4j
@Component
@Aspect
public class TestAspect {

//    @Before()
//    @After()

    /*
    此时无需其他配置，AOP的拦截功能就能生效。它能够对com.example.demo.service包下所有的方法进行拦截
     */
    @Around("execution(* com.example.demo.service..*.*(..))")
    public Object aroundInvoke(ProceedingJoinPoint point) throws Throwable{
        log.info("【*** Service-Before ***】执行参数："
                + Arrays.toString(point.getArgs()));
        Object obj = point.proceed(point.getArgs()); // 进行具体业务调用
        log.info("【*** Service-After ***】返回结果：" + obj);
        return obj;
    }
}
