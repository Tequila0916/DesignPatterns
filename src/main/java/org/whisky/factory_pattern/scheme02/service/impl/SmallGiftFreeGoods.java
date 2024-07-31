package org.whisky.factory_pattern.scheme02.service.impl;

import org.whisky.factory_pattern.scheme02.entity.AwardInfo;
import org.whisky.factory_pattern.scheme02.entity.ResponseResult;
import org.whisky.factory_pattern.scheme02.entity.SmallGiftInfo;
import org.whisky.factory_pattern.scheme02.service.IFreeGoods;

import java.util.UUID;

/**
 * @ClassName SmallGiftFreeGoods
 * @Description TODO
 * @Author GT-R
 * @Date 2024/6/4上午11:51
 * @Version 1.0
 */
public class SmallGiftFreeGoods implements IFreeGoods {
    @Override
    public ResponseResult sendFreeGoods(AwardInfo awardInfo) {
        SmallGiftInfo smallGiftInfo = new SmallGiftInfo();
        smallGiftInfo.setUserPhone(awardInfo.getExtMap().get("phone"));
        smallGiftInfo.setUserName(awardInfo.getExtMap().get("username"));
        smallGiftInfo.setAddress(awardInfo.getExtMap().get("address"));
        smallGiftInfo.setOrderId(UUID.randomUUID().toString());

        System.out.println("小礼品发放成,请注意查收: " + smallGiftInfo.toString());
        return new ResponseResult("200","小礼品发送成功",smallGiftInfo);
    }
}
