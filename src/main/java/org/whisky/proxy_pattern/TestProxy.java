package org.whisky.proxy_pattern;

import org.junit.Test;
import org.whisky.proxy_pattern.example01.UserDao;
import org.whisky.proxy_pattern.example01.impl.UserDaoImpl;
import org.whisky.proxy_pattern.example01.impl.UserDaoProxy;
import org.whisky.proxy_pattern.example02.ProxyFactory;
import org.whisky.proxy_pattern.example03.User;
import org.whisky.proxy_pattern.example03.UserLogProxy;
import org.whisky.proxy_pattern.example03.UserServiceImpl;

import java.util.List;

/**
 * @ClassName TestProxy
 * @Description TODO
 * @Author GT-R
 * @Date 2024/6/27下午3:32
 * @Version 1.0
 */
public class TestProxy {
    @Test
    public void testStaticProxy(){

        //目标对象
        UserDao userDao = new UserDaoImpl();
        //代理对象
        UserDaoProxy proxy = new UserDaoProxy(userDao);
        proxy.save();
    }
    @Test
    public void testDynamicProxy(){
        UserDao target = new UserDaoImpl();
        System.out.println(target.getClass());//目标对象信息
        UserDao proxy = (UserDao)new ProxyFactory(target).getProxyInstance();
        System.out.println(proxy.getClass()); //输出代理对象信息
        proxy.save();
    }
    @Test
    public void testCglibProxy(){
        //目标对象
        UserServiceImpl userService = new UserServiceImpl();
        System.out.println(userService.getClass());

        //代理对象
        UserServiceImpl proxy = (UserServiceImpl) new UserLogProxy(userService).getLogProxy();
        System.out.println(proxy.getClass());

        List<User> userList = proxy.findUserList();
        System.out.println("用户信息: "+userList);
    }
}
