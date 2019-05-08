package com.example.demo.util;

import com.example.demo.config.CustomLocaleResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wh1507006
 * @date 2019/4/22 13:59
 */
public class MessageUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageUtil.class);

    private static final CustomLocaleResolver localeResolver;

    static {
        localeResolver =
                (CustomLocaleResolver) SpringContextUtil.getBean("localeResolver");
    }

    public static String getMessage(String messageCode) {
        return localeResolver.getMessage(messageCode);
    }

}
