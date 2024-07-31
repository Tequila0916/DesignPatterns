package org.whisky.singleton_pattern;

/**
 * @ClassName Singleton03
 * @Description 懒汉式 线程安全
 * @Author GT-R
 * @Date 2024/5/29下午8:42
 * @Version 1.0
 */
public class Singleton_03 {
    private static Singleton_03 instance;

    private Singleton_03() {

    }
    public static synchronized Singleton_03 getInstance(){
        if (instance == null) {
            instance = new Singleton_03();
        }
        return instance;
    }
}
