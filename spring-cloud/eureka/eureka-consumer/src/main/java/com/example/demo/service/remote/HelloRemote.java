package com.example.demo.service.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author alex_hu
 * @date 18-9-20 下午4:10
 */
@FeignClient(name = "spring-cloud-producer")
public interface HelloRemote {

    @RequestMapping("/hello")
    String hello(@RequestParam(value = "name") String name);

}
