package org.whisky.factory_pattern.scheme02.controller;

import org.whisky.factory_pattern.scheme02.entity.AwardInfo;
import org.whisky.factory_pattern.scheme02.entity.ResponseResult;
import org.whisky.factory_pattern.scheme02.factory.FreeGoodsFactory;
import org.whisky.factory_pattern.scheme02.service.IFreeGoods;

/**
 * @ClassName DeliverController
 * @Description TODO
 * @Author GT-R
 * @Date 2024/6/9下午6:06
 * @Version 1.0
 */
public class DeliverController {
    public ResponseResult awardToUser(AwardInfo awardInfo) {
        try {
            IFreeGoods freeGoods = FreeGoodsFactory.getInstance(awardInfo.getAwardType());
            ResponseResult responseResult = freeGoods.sendFreeGoods(awardInfo);
            return responseResult;
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult("201","奖品发放失败!");
        }
    }
}
