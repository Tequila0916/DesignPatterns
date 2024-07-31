package org.whisky.factory_pattern.scheme04.factory;

import org.whisky.factory_pattern.scheme04.product.AbstractFreezer;
import org.whisky.factory_pattern.scheme04.product.AbstractTV;
import org.whisky.factory_pattern.scheme04.product.HairFreezer;
import org.whisky.factory_pattern.scheme04.product.HairTV;

/**
 * @ClassName HairFactory
 * @Description TODO
 * @Author GT-R
 * @Date 2024/6/26下午6:42
 * @Version 1.0
 */
public class HairFactory implements AppliancesFactory{
    @Override
    public AbstractTV createTV() {
        return new HairTV();
    }

    @Override
    public AbstractFreezer createFreezer() {
        return new HairFreezer();
    }
}
