package org.whisky.singleton_pattern;

/**
 * @ClassName Singleton_01
 * @Description 饿汉式
 * @Author GT-R
 * @Date 2024/5/29下午8:24
 * @Version 1.0
 */
public class Singleton_01 {
    //创建私有全局静态对象
    private static Singleton_01 instance = new Singleton_01();

    //私有构造方法
    private Singleton_01() {
    }

    //提供外部获取单例对象
    public static Singleton_01 getInstance() {
        return instance;
    }
}
