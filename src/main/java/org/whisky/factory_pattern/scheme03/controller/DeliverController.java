package org.whisky.factory_pattern.scheme03.controller;

import org.whisky.factory_pattern.scheme02.entity.AwardInfo;
import org.whisky.factory_pattern.scheme02.entity.ResponseResult;
import org.whisky.factory_pattern.scheme02.service.IFreeGoods;
import org.whisky.factory_pattern.scheme03.factory.FreeGoodsFactoryMap;
import org.whisky.factory_pattern.scheme03.factory.FreeGoodsFactory;

/**
 * @ClassName DeliverController
 * @Description TODO
 * @Author GT-R
 * @Date 2024/6/26上午10:47
 * @Version 1.0
 */
public class DeliverController {
    public ResponseResult awardToUser(AwardInfo awardInfo) {
        FreeGoodsFactory freeGoodsFactory = null;
        freeGoodsFactory = FreeGoodsFactoryMap.getParserFactory(awardInfo.getAwardType());
        IFreeGoods freeGoods = freeGoodsFactory.getInstance();
        System.out.println("=====工厂方法模式========");
        ResponseResult result = freeGoods.sendFreeGoods(awardInfo);
        return result;
    }
}
