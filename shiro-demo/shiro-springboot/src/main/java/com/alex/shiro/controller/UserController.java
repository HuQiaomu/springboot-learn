package com.alex.shiro.controller;

import com.alex.shiro.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wh1507006
 * @date 2018/11/7 16:44
 */
@RestController
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/rememberMe")
    public String rememberMe() {
        // Remembered和Authenticated两者是互斥的，若一个为true，则另一个为false，反之亦然
        Subject subject = SecurityUtils.getSubject();
        log.info("rememberMe: " + subject.isRemembered() +", auth: " + subject.isAuthenticated());
        if (subject.isRemembered()) {
            /*
             rememberMe 的 cookie 中会记住上次登录信息，
             subject.getPrincipal() 取得值为
             doGetAuthenticationInfo() 中 SimpleAuthenticationInfo 中存的值
              */
            return subject.getPrincipal().toString();
        } else {
            return "no remember me";
        }
    }

    @GetMapping("/login")
    public String login(User user) {
        log.info(user.toString());
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),
                user.getPassword());
        try {
            token.setRememberMe(user.isRememberMe()); //启动 shiro rememberMe 功能
            subject.login(token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return e.getMessage();
        }

        if (subject.isAuthenticated()) {
            subject.getSession().setAttribute("user", user.getUsername());
            return "success";
        } else {
            return "failed";
        }
    }

    @GetMapping("/addUser")
    @RequiresRoles({"admin", "user"})
    @RequiresPermissions("user:add")
    public String addUser() {
        return "addUser success";
    }

    @GetMapping("/getUser")
    public String getUser() {
        return "sessionId: " + SecurityUtils.getSubject().getSession().getId().toString();
    }

    @GetMapping("/delUser")
    @RequiresRoles("admin")
    @RequiresPermissions("user:delete")
    public String delUser() {
        return "delUser success";
    }

    @GetMapping("/unauthorized")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public String unauthorized() {
        return "401";
    }

}
