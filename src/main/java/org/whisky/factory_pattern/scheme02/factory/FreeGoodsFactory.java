package org.whisky.factory_pattern.scheme02.factory;

import org.whisky.factory_pattern.scheme02.service.IFreeGoods;
import org.whisky.factory_pattern.scheme02.service.impl.DiscountFreeGoods;
import org.whisky.factory_pattern.scheme02.service.impl.SmallGiftFreeGoods;
import org.whisky.factory_pattern.scheme02.service.impl.YouKuMemberFreeGoods;

/**
 * @ClassName FreeGoodsFactory
 * @Description TODO
 * @Author GT-R
 * @Date 2024/6/9下午6:01
 * @Version 1.0
 */
public class FreeGoodsFactory {
    public static IFreeGoods getInstance(Integer awardType){
        IFreeGoods iFreeGoods = null;
        if (awardType == 1) {
            iFreeGoods = new DiscountFreeGoods();
        }
        else if (awardType == 2) {
            iFreeGoods = new YouKuMemberFreeGoods();
        }
        else if (awardType == 3) {
            iFreeGoods = new SmallGiftFreeGoods();
        }
        return iFreeGoods;
    }
}
