package org.whisky.proxy_pattern.example03;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @ClassName UserLogProxy
 * @Description TODO
 * @Author GT-R
 * @Date 2024/6/28上午10:33
 * @Version 1.0
 */
public class UserLogProxy implements MethodInterceptor {
    private Object target;

    public UserLogProxy(Object target) {
        this.target = target;
    }

    public Object getLogProxy() {
        Enhancer en = new Enhancer();
        en.setSuperclass(target.getClass());
        en.setCallback(this);
        return en.create();
    }

    /**
     * 实现回调方法
     *
     * @param o           代理对象
     * @param method      目标对象中的方法的Method实例
     * @param args        实际参数
     * @param methodProxy 代理对象中的方法的method实例
     * @return: java.lang.Object
     */
    @Override
    public Object intercept(Object o, Method method, Object[] args,
                            MethodProxy methodProxy) throws Throwable {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(formatter.format(calendar.getTime()) + " [" + method.getName() + "] 查询用户信息...]");
        Object result = methodProxy.invokeSuper(o, args);
        return result;
    }
}
