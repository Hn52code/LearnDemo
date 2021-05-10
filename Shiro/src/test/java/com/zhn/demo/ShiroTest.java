package com.zhn.demo;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

public class ShiroTest {

    @Test
    public void testShiroStart() {

        //  通过初始化配置来创建安全管理器工厂
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");

        // 获取安全管理器实例
        SecurityManager securityManager = factory.getInstance();

        // 将安全管理器设置给安全工具
        SecurityUtils.setSecurityManager(securityManager);

        // 通过安全工具类获取当前用户
        Subject currentUser = SecurityUtils.getSubject();

        // 获取当前用户的会话对象
        Session session = currentUser.getSession();

        // 判断身份认证否
        if (!currentUser.isAuthenticated()) {
            // 创建登录信息
            UsernamePasswordToken token = new UsernamePasswordToken("lonestarr", "vespa");
            // 设置记住我
            token.setRememberMe(true);
            // 当前用户登录
            currentUser.login(token);
        }
        // 判断当前用户有某个角色？
        if (currentUser.hasRole("goodguy")) {
            System.out.println("拥有 goodguy 角色");
        }

        // 判断当前用户拥有权限？
        if (currentUser.isPermitted("lightsaber:wield")){
            System.out.println("拥有 lightsaber:wield 权限");
        }

    }
}
