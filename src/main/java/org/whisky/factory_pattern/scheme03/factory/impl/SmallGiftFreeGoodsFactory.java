package org.whisky.factory_pattern.scheme03.factory.impl;

import org.whisky.factory_pattern.scheme02.service.IFreeGoods;
import org.whisky.factory_pattern.scheme02.service.impl.SmallGiftFreeGoods;
import org.whisky.factory_pattern.scheme03.factory.FreeGoodsFactory;

/**
 * @ClassName SmallGiftFreeGoodsFactory
 * @Description TODO
 * @Author GT-R
 * @Date 2024/6/26上午10:45
 * @Version 1.0
 */
public class SmallGiftFreeGoodsFactory implements FreeGoodsFactory {
    @Override
    public IFreeGoods getInstance() {
        return new SmallGiftFreeGoods();
    }
}
