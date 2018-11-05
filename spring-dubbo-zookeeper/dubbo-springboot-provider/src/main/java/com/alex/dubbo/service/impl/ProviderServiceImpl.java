package com.alex.dubbo.service.impl;

import com.alex.dubbo.service.ProviderService;
import com.alibaba.dubbo.config.annotation.Service;


/**
 * @author wh1507006
 * @date 2018/11/5 13:41
 */
@Service
public class ProviderServiceImpl implements ProviderService {
    @Override
    public String sayHello(String name) {
        return "Hello, " + name + " , 你好! dubbo~~~";
    }
}
