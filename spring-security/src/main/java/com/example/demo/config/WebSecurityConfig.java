package com.example.demo.config;

import com.example.demo.error.AccessDeniedServletHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author alex_hu
 * @date 18-9-18 下午2:07
 */
@Slf4j
@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
        http
                .authorizeRequests() //表示下面的节点都需要身份验证
                .antMatchers("/", "/logout").permitAll()   // permitAll 表示不认证可以访问
                .antMatchers("/user").hasRole("USER")
                .antMatchers("/admin").access("hasRole('ADMIN') or hasRole('DBA') ")
                .anyRequest().authenticated()  //未匹配的都需要认证
                .and()   //一段匹配规则的结束
                .formLogin()
                .and().logout().logoutUrl("/logout").permitAll();  //注销(不需要验证)

//        http.exceptionHandling().authenticationEntryPoint()
        http.exceptionHandling().accessDeniedHandler(new AccessDeniedServletHandler());

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        super.configure(auth);
        auth.inMemoryAuthentication()
                .withUser("alex").password("123456").roles("USER");
        auth.inMemoryAuthentication()
                .withUser("admin").password("123456").roles("ADMIN");

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder(){
            @Override
            public String encode(CharSequence charSequence) {
                return new BCryptPasswordEncoder().encode(charSequence); //加密后的密码
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
//                return false;
//                return encode(charSequence).equals(s);
                return true;
            }
        };
    }



}
