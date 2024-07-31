package org.whisky.factory_pattern.scheme02.service.impl;

import org.whisky.factory_pattern.scheme02.entity.AwardInfo;
import org.whisky.factory_pattern.scheme02.entity.ResponseResult;
import org.whisky.factory_pattern.scheme02.service.IFreeGoods;

/**
 * @ClassName DiscountFreeGoods
 * @Description TODO
 * @Author GT-R
 * @Date 2024/6/4上午11:43
 * @Version 1.0
 */
public class DiscountFreeGoods implements IFreeGoods {
    @Override
    public ResponseResult sendFreeGoods(AwardInfo awardInfo) {
        System.out.println("向用户发放一张打折券: " + awardInfo.getUid() + " , " + awardInfo.getAwardNumber());
        return new ResponseResult("200","打折券发放成功!");
    }
}
