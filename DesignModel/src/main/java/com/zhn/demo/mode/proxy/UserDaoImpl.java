package com.zhn.demo.mode.proxy;

public class UserDaoImpl implements UserDao {
    @Override
    public void save() {
        System.out.println(" save ... ");
    }
}
