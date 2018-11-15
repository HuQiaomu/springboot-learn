package com.alex.shiro.config;

import com.alex.shiro.entity.User;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author wh1507006
 * @date 2018/11/7 16:36
 * Shiro 自定义 Realm
 */
public class CustomRealm extends AuthorizingRealm {

    private static final Logger  log = LoggerFactory.getLogger(CustomRealm.class);

    Map<String, User> userMap = new HashMap<String, User>(16);
    {
//        userMap.put("alex", "alex");
//        userMap.put("alex", "534b44a19bf18d20b71ecc4eb77c572f");
        userMap.put("alex", new User("alex", "0bf4375c81978b29d0f546a1e9cd6412"));
//        super.setName("customRealm");
    }

    /**
     * 授权： 认证成功后，判断是否有权限，分 role, permission
     * @param principals
     * @return
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        // 从 doGetAuthenticationInfo() 中 SimpleAuthenticationInfo 存什么则取得是什么，
        // 存 userName 则是 userName, 存 user 则是 user.
//        String userName = (String) principals.getPrimaryPrincipal();
        User user = (User) principals.getPrimaryPrincipal();
        String userName = user.getUsername();
        // 从数据库或者缓存中获取角色数据
        Set<String> roles =  getRolesByUserName(userName);

        Set<String> permissions = getPermissionsByUserName(userName);

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setRoles(roles);
        simpleAuthorizationInfo.setStringPermissions(permissions);

        return simpleAuthorizationInfo;
    }

    private Set<String> getPermissionsByUserName(String userName) {
        Set<String> sets = new HashSet<String>();
//        sets.add("user:delete");
        sets.add("user:select");
        sets.add("user:add");
        return sets;
    }

    private Set<String> getRolesByUserName(String userName) {
        Set<String> sets = new HashSet<String>();
        sets.add("admin");
        sets.add("user");
        return sets;
    }

    /**
     * 认证： 从数据源获取认证信息
     * @param token
     * @return
     * @throws AuthenticationException
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //1.从主体传过来的认证信息中，获得用户名
        // subject.login(username, password) 传过来的 userName, 之后根据userName从数据源获取 password
        // 再 将数据源的 password 存放到 凭证中， shiro 会进行比较
        String userName = (String) token.getPrincipal();
        //2.通过用户名到数据库中获取凭证
        /*
        String password = getPasswordByUserName(userName);
        log.info("userName: " + userName + ", password: " + password);
        if (password == null) {
            return null;
        }

        // 将 userName， password 存放到凭证中
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                userName, password, getName()
        );
        */
        User user = getUserByUserName(userName);
        if (user == null) {
            return null;
        }
        //将 整个用户信息， password 存放到凭证中
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user, user.getPassword(), getName()
        );
        //添加 盐加密，需将盐存放进去
        authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes("alex"));
        return authenticationInfo;
    }

    private User getUserByUserName(String userName) {
        return userMap.get(userName);
    }

    public static void main(String[] args) {
//        Md5Hash md5Hash = new Md5Hash("alex"); //不添加 盐 加密  534b44a19bf18d20b71ecc4eb77c572f
        Md5Hash md5Hash = new Md5Hash("alex", "alex"); //添加 盐 加密 0bf4375c81978b29d0f546a1e9cd6412
        System.out.println(md5Hash.toString());
    }
}
