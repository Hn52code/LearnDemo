package com.zhn.demo.shiro.authentication;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

public class MyRealm extends AuthorizingRealm {

    @Override
    public void setName(String name) {
        super.setName("myrealm");
    }

    // 身份认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String userCode = (String) token.getPrincipal();

        String pwd = "2f2b0fcc14a8bc108226fdcb9b203c14";
        String nonce = "asdf";


        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userCode, pwd,
                ByteSource.Util.bytes(nonce), this.getName());


        return info;
    }


    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {


        return null;
    }


}
