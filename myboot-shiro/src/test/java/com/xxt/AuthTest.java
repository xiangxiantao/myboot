package com.xxt;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Test;


public class AuthTest {

    @Test
    public void testAuth(){
        //IniSecurityManagerFactory managerFactory = new IniSecurityManagerFactory("classpath:shiro.ini");
        //IniSecurityManagerFactory managerFactory = new IniSecurityManagerFactory("classpath:shiro-realm.ini");
        IniSecurityManagerFactory managerFactory = new IniSecurityManagerFactory("classpath:shiro-jdbc-realm.ini");
        SecurityManager manager = managerFactory.getInstance();
        SecurityUtils.setSecurityManager(manager);
        Subject subject=SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("zhang", "123");
        try {
            subject.login(usernamePasswordToken);
        }catch (AuthenticationException e){
            e.printStackTrace();
            System.out.println("用户名密码错误");
        }

        Assert.assertEquals(true,subject.isAuthenticated());//判断用户是否登陆
        subject.logout();

    }

}
