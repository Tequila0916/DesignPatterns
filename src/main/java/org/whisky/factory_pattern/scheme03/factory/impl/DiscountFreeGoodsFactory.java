package org.whisky.factory_pattern.scheme03.factory.impl;

import org.whisky.factory_pattern.scheme02.service.IFreeGoods;
import org.whisky.factory_pattern.scheme02.service.impl.DiscountFreeGoods;
import org.whisky.factory_pattern.scheme03.factory.FreeGoodsFactory;

/**
 * @ClassName DiscountFreeGoodsFactory
 * @Description TODO
 * @Author GT-R
 * @Date 2024/6/26上午10:44
 * @Version 1.0
 */
public class DiscountFreeGoodsFactory implements FreeGoodsFactory {

    @Override
    public IFreeGoods getInstance() {
        return new DiscountFreeGoods();
    }
}
