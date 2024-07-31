package org.whisky.singleton_pattern;

/**
 * @ClassName Singleton_02
 * @Description 懒汉式 线程不安全
 * @Author GT-R
 * @Date 2024/5/29下午8:32
 * @Version 1.0
 */
public class Singleton_02 {
    private static Singleton_02 instance;

    private Singleton_02() {
    }

    public static Singleton_02 getInstance() {
        if (instance == null) {
            instance = new Singleton_02();
        }
        return instance;
    }
}
