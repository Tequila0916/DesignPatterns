package org.whisky.factory_pattern.scheme01.service;

import org.whisky.factory_pattern.scheme01.entity.SmallGiftInfo;

/**
 * @ClassName SmallGiftService
 * @Description TODO
 * @Author GT-R
 * @Date 2024/5/30下午1:25
 * @Version 1.0
 */
public class SmallGiftService {
    public Boolean giveSmallGift(SmallGiftInfo smallGiftInfo) {

        System.out.println("小礼品已发货,获奖用户注意查收! " + smallGiftInfo.toString());
        return true;
    }
}
