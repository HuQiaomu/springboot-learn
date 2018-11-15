package com.alex.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md2Hash;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author wh1507006
 * @date 2018/11/7 15:38
 * Shiro 自定义 Realm
 */
public class CustomRealm extends AuthorizingRealm {

    Map<String, String>  userMap = new HashMap<String, String>(16);
    {
//        userMap.put("alex", "alex");
//        userMap.put("alex", "534b44a19bf18d20b71ecc4eb77c572f");
        userMap.put("alex", "0bf4375c81978b29d0f546a1e9cd6412");
//        super.setName("customRealm");
    }

    /**
     * 授权： 认证成功后，判断是否有权限，分 role, permission
     * @param principals
     * @return
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        String userName = (String) principals.getPrimaryPrincipal();
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
        sets.add("user:delete");
        sets.add("user:select");
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
        String userName = (String) token.getPrincipal();
        //2.通过用户名到数据库中获取凭证
        String password = getPasswordByUserName(userName);
        if (password == null) {
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                userName, password, getName()
        );
        //添加 盐加密，需将盐存放进去
        authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes("alex"));
        return authenticationInfo;
    }

    private String getPasswordByUserName(String userName) {
        return userMap.get(userName);
    }

    public static void main(String[] args) {
//        Md5Hash md5Hash = new Md5Hash("alex"); //不添加 盐 加密  534b44a19bf18d20b71ecc4eb77c572f
        Md5Hash md5Hash = new Md5Hash("alex", "alex"); //添加 盐 加密 0bf4375c81978b29d0f546a1e9cd6412
        System.out.println(md5Hash.toString());
    }
}
