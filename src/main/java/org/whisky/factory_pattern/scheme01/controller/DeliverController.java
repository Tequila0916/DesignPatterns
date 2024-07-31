package org.whisky.factory_pattern.scheme01.controller;

import org.whisky.factory_pattern.scheme01.entity.AwardInfo;
import org.whisky.factory_pattern.scheme01.entity.DiscountResult;
import org.whisky.factory_pattern.scheme01.entity.SmallGiftInfo;
import org.whisky.factory_pattern.scheme01.service.DiscountService;
import org.whisky.factory_pattern.scheme01.service.SmallGiftService;
import org.whisky.factory_pattern.scheme01.service.YouKuMemberService;

import java.util.UUID;

/**
 * @ClassName DeliverController
 * @Description TODO
 * @Author GT-R
 * @Date 2024/5/30下午1:54
 * @Version 1.0
 */
public class DeliverController {
    /**
     * 按照类型的不同发放商品
     * 奖品类型: 1 打折券 ,2 优酷会员,3 小礼品
     */
    public void awardToUser(AwardInfo awardInfo) {

        if (awardInfo.getAwardType() == 1) { //打折券
            DiscountService discountService = new DiscountService();
            DiscountResult result = discountService.sendDiscount(awardInfo.getUid(), awardInfo.getAwardNumber());
            System.out.println("打折券发放成功!" + result.toString());

        } else if (awardInfo.getAwardType() == 2) { //优酷会员
            //获取用户手机号
            String bindMobile = awardInfo.getExtMap().get("phone");

            //调用service
            YouKuMemberService youKuMemberService = new YouKuMemberService();
            youKuMemberService.openMember(bindMobile, awardInfo.getAwardNumber());
            System.out.println("优酷会员发放成功!");

        } else if (awardInfo.getAwardType() == 3) {
            /*
            小礼品
            封装收货用户信息
            */
            SmallGiftInfo smallGiftInfo = new SmallGiftInfo();
            smallGiftInfo.setUserName(awardInfo.getExtMap().get("username"));
            smallGiftInfo.setOrderId(UUID.randomUUID().toString());
            smallGiftInfo.setUserPhone(awardInfo.getExtMap().get("phone"));
            smallGiftInfo.setAddress(awardInfo.getExtMap().get("address"));

            SmallGiftService smallGiftService = new SmallGiftService();
            Boolean isSuccess = smallGiftService.giveSmallGift(smallGiftInfo);
            System.out.println("小礼品发放成功!" + isSuccess);
        }
    }
}
