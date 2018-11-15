package com.alex.shiro.config;

import com.alex.shiro.cache.RedisCacheManager;
import com.alex.shiro.session.CustomSessionManager;
import com.alex.shiro.session.RedisSessionDao;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author wh1507006
 * @date 2018/11/7 16:30
 */
@Configuration
public class ShiroConfig {

    @Resource
    private RedisSessionDao redisSessionDao;
    @Resource
    private CustomSessionManager customSessionManager;
    @Resource
    private RedisCacheManager redisCacheManager;

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

//        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setLoginUrl("/unauthorized");
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorized");

        Map<String, String> filterChainDefinitions = new LinkedHashMap<>();
        filterChainDefinitions.put("/login", "anon");
        // user拦截器判断用户是否认证，isRemembered() 或 isAuthenticated()
        // 注意 Remembered和Authenticated两者是互斥的，若一个为true，则另一个为false，反之亦然
        filterChainDefinitions.put("/rememberMe", "user");
        // authc 拦截器用户是否是通过 subject.login() 登录的
        filterChainDefinitions.put("/**", "authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitions);

        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager securityManager(CustomRealm customRealm,
                                           RememberMeManager rememberMeManager) {
        //注意这里使用 DefaultWebSecurityManager
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(customRealm);

        // SessionManager
//        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        //使用自定义的 SessionManager
        customSessionManager.setSessionDAO(redisSessionDao);
        // redis key 已经设置过期时间，所以不要设置 session 过期时间
//        sessionManager.setDeleteInvalidSessions(true); //删除过期的 session
//        sessionManager.setGlobalSessionTimeout(redisSessionDao.getExpireTime()); // 设置全局 session 过期时间
        securityManager.setSessionManager(customSessionManager);

        // 自定义 CacheManager
        securityManager.setCacheManager(redisCacheManager);

        securityManager.setRememberMeManager(rememberMeManager);
        return securityManager;
    }

    /**
     * 用于 shiro 的 rememberMe 功能，主要是将登录信息保存在 cookie 中
     * @returnM
     */
    @Bean
    public RememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();

        SimpleCookie simpleCookie = new SimpleCookie();
        simpleCookie.setMaxAge(20000); // cookie 有效时长
        simpleCookie.setName("rememberMe");

        cookieRememberMeManager.setCookie(simpleCookie);
        //rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
        // 注意 秘钥长度
        cookieRememberMeManager.setCipherKey("01234567890123456789012345678912".getBytes());

        return cookieRememberMeManager;
    }

    @Bean
    public CustomRealm customRealm(HashedCredentialsMatcher hashedCredentialsMatcher) {
        CustomRealm customRealm = new CustomRealm();
        customRealm.setCredentialsMatcher(hashedCredentialsMatcher); // 设置密码加密方式
        return customRealm;
    }

    /**
     * 定义密码加密方式
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher1 = new HashedCredentialsMatcher();
        hashedCredentialsMatcher1.setHashAlgorithmName("md5"); //加密方式
        hashedCredentialsMatcher1.setHashIterations(1); //加密次数
        return hashedCredentialsMatcher1;
    }

    /**
     *  开启shiro aop注解支持.
     *  使用代理方式;所以需要开启代码支持;
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * shiro aop 注解所需
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

}
