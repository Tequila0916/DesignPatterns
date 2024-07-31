package org.whisky.singleton_pattern;

import java.io.Serializable;

/**
 * @ClassName Singleton_04
 * @Description 双重判断
 * @Author GT-R
 * @Date 2024/5/29下午8:47
 * @Version 1.0
 */
public class Singleton_04 implements Serializable {
    private volatile static Singleton_04 instance;

    private Singleton_04() {
    }

    public static Singleton_04 getInstance() {
        if (instance == null) {
            synchronized (Singleton_04.class) {
                if (instance == null) {
                    instance = new Singleton_04();
                }
            }
        }
        return instance;
    }
    private Object readResolve() {
        return instance;
    }
}
