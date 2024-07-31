package org.whisky.factory_pattern.scheme03.factory;

import org.whisky.factory_pattern.scheme02.service.IFreeGoods;

/**
 * @ClassName FreeGoodsFactory
 * @Description TODO
 * @Author GT-R
 * @Date 2024/6/26上午10:42
 * @Version 1.0
 */
public interface FreeGoodsFactory {
    IFreeGoods getInstance();
}
