package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * @author wh1507006
 * @date 2019/4/22 16:42
 *
 * 国际化信息获取
 */
@Component("localeResolver")
public class CustomLocaleResolver implements LocaleResolver {

    /**
     * 语言分隔符
     */
    private final String REGEX = "_";

    /**
     * http 请求头语言环境
     */
    private final String LANG_HEADER = "lang";

    /**
     * 资源文件获取
     */
    @Autowired
    private MessageSource messageSource;

    /**
     * application.yml 中配置的服务器端国际化语言
     */
    @Value("${language:zh_CN}")
    private String serverLang;

    /**
     * 按指定的语言key, 获取国际化信息
     * @param msgKey msgKey
     * @return String
     */
    public String getMessage(String msgKey) {
        return messageSource.getMessage(msgKey, null, LocaleContextHolder.getLocale());
    }

    /**
     * 获取国际化语言环境
     * @param clientLang 从 http 请求中获取的语言环境，若没有则取系统默认值
     * @return Locale
     */
    private Locale getLocale(String clientLang) {
        String localLang = StringUtils.isEmpty(clientLang) ? serverLang : clientLang;
        return new Locale(localLang.split(REGEX)[0], localLang.split(REGEX)[1]);
    }

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String clientLang = request.getHeader(LANG_HEADER);
        // 对用户输入进行验证，防止出错
        if (!StringUtils.isEmpty(clientLang) && clientLang.split(REGEX).length != 2) {
            clientLang = null;
        }
        return getLocale(clientLang);
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}
