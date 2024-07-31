package org.whisky.proxy_pattern.example01.impl;

import org.whisky.proxy_pattern.example01.UserDao;

/**
 * @ClassName UserDaoImpl
 * @Description TODO
 * @Author GT-R
 * @Date 2024/6/27下午3:30
 * @Version 1.0
 */
public class UserDaoImpl implements UserDao {
    @Override
    public void save() {
        System.out.println("保存数据");
    }
}
