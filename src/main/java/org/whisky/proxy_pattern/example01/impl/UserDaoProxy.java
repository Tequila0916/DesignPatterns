package org.whisky.proxy_pattern.example01.impl;

import org.whisky.proxy_pattern.example01.UserDao;

/**
 * @ClassName UserDaoProxy
 * @Description TODO
 * @Author GT-R
 * @Date 2024/6/27下午3:31
 * @Version 1.0
 */
public class UserDaoProxy implements UserDao {
    private UserDao target;

    public UserDaoProxy(UserDao target) {
        this.target = target;
    }

    @Override
    public void save() {
        System.out.println("开启事务"); //扩展额外功能
        target.save();
        System.out.println("提交事务");
    }
}
