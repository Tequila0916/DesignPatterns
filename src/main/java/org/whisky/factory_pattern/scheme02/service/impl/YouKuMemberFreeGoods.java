package org.whisky.factory_pattern.scheme02.service.impl;

import org.whisky.factory_pattern.scheme02.entity.AwardInfo;
import org.whisky.factory_pattern.scheme02.entity.ResponseResult;
import org.whisky.factory_pattern.scheme02.service.IFreeGoods;

/**
 * @ClassName YouKuMemberFreeGoods
 * @Description TODO
 * @Author GT-R
 * @Date 2024/6/4上午11:52
 * @Version 1.0
 */
public class YouKuMemberFreeGoods implements IFreeGoods {
    @Override
    public ResponseResult sendFreeGoods(AwardInfo awardInfo) {
        String phone = awardInfo.getExtMap().get("phone");
        System.out.println("发放优酷会员成功,绑定手机号: " + phone);
        return new ResponseResult("200","优酷会员发放成功!");
    }
}
