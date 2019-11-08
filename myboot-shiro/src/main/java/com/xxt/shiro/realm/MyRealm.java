package com.xxt.shiro.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

public class MyRealm implements Realm {
    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public boolean supports(AuthenticationToken authenticationToken) {
        return authenticationToken instanceof UsernamePasswordToken;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String userName= (String) authenticationToken.getPrincipal();
        String password= new String((char[])authenticationToken.getCredentials());
        //下面就可以通过userName，password 编写自己的判断逻辑，比如数据库或者缓存
        if(!"zhang".equals(userName)) {
            throw new UnknownAccountException(); //如果用户名错误
        }
        if(!"123456".equals(password)) {
            throw new IncorrectCredentialsException(); //如果密码错误
        }
        //如果身份认证验证成功，返回一个AuthenticationInfo实现；
        return new SimpleAuthenticationInfo(userName, password, getName());
    }
}
