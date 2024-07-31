package org.whisky.flyweight_pattern;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName GobangFactory
 * @Description TODO
 * @Author GT-R
 * @Date 2024/7/17下午3:54
 * @Version 1.0
 */
public class GobangFactory {
    private Map<String, GobangFlyweight> pool;

    private GobangFactory() {
        pool = new HashMap<>();
        GobangFlyweight white = new WhiteGobang();
        GobangFlyweight black = new BlackGobang();
        pool.put("b", black);
        pool.put("w", white);

    }

    public static GobangFactory getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public GobangFlyweight getGobang(String key) {
        return pool.get(key);
    }

    private static class SingletonHolder {
        private static final GobangFactory INSTANCE = new GobangFactory();
    }
}
