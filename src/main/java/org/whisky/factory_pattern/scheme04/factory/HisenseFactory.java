package org.whisky.factory_pattern.scheme04.factory;

import org.whisky.factory_pattern.scheme04.product.AbstractFreezer;
import org.whisky.factory_pattern.scheme04.product.AbstractTV;
import org.whisky.factory_pattern.scheme04.product.HisenseFreezer;
import org.whisky.factory_pattern.scheme04.product.HisenseTV;

/**
 * @ClassName HisenseFactory
 * @Description TODO
 * @Author GT-R
 * @Date 2024/6/26下午6:43
 * @Version 1.0
 */
public class HisenseFactory implements AppliancesFactory{
    @Override
    public AbstractTV createTV() {
        return new HisenseTV();
    }

    @Override
    public AbstractFreezer createFreezer() {
        return new HisenseFreezer();
    }
}
