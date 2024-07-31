package org.whisky.factory_pattern.scheme01.service;

import org.whisky.factory_pattern.scheme01.entity.DiscountResult;

/**
 * @ClassName DiscountService
 * @Description TODO
 * @Author GT-R
 * @Date 2024/5/30下午1:24
 * @Version 1.0
 */
public class DiscountService {
    public DiscountResult sendDiscount(String uid, String number) {
        System.out.println("向用户发放打折券一张: " + uid + " , " + number);
        return new DiscountResult("200", "发放打折券成功");
    }
}
