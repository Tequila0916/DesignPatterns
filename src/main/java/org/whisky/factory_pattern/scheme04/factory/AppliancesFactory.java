package org.whisky.factory_pattern.scheme04.factory;

import org.whisky.factory_pattern.scheme04.product.AbstractFreezer;
import org.whisky.factory_pattern.scheme04.product.AbstractTV;

/**
 * @ClassName AppliancesFactory
 * @Description TODO
 * @Author GT-R
 * @Date 2024/6/26下午6:41
 * @Version 1.0
 */
public interface AppliancesFactory {
    AbstractTV createTV();
    AbstractFreezer createFreezer();
}
