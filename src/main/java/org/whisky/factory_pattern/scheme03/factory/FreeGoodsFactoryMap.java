package org.whisky.factory_pattern.scheme03.factory;

import org.whisky.factory_pattern.scheme03.factory.impl.DiscountFreeGoodsFactory;
import org.whisky.factory_pattern.scheme03.factory.impl.SmallGiftFreeGoodsFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName FreeGoodsFactoryMap
 * @Description TODO
 * @Author GT-R
 * @Date 2024/6/26上午11:07
 * @Version 1.0
 */
public class FreeGoodsFactoryMap {
    private static final Map<Integer, FreeGoodsFactory> cache = new HashMap<>();

    static {
        cache.put(1, new DiscountFreeGoodsFactory());
        cache.put(2, new SmallGiftFreeGoodsFactory());
    }

    public static FreeGoodsFactory getParserFactory(Integer type) {
        FreeGoodsFactory freeGoodsFactory = null;
        if (type == 1) {
            freeGoodsFactory = cache.get(1);
        } else if (type == 2) {
            freeGoodsFactory = cache.get(2);
        }
        return freeGoodsFactory;
    }
}
