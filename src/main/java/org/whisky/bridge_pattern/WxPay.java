package org.whisky.bridge_pattern;

import java.math.BigDecimal;

/**
 * @ClassName WxPay
 * @Description TODO
 * @Author GT-R
 * @Date 2024/6/29下午1:53
 * @Version 1.0
 */
public class WxPay extends Pay {
    public WxPay(PayMode payMode) {
        super(payMode);
    }

    @Override
    public String transfer(String uId, String tradeId, BigDecimal amount) {
        System.out.println("微信渠道支付划账开始......");
        boolean security = payMode.security(uId);
        System.out.println("微信渠道支付风险校验: " + uId + " , " + tradeId + " , " + security);
        if (!security) {
            System.out.println("微信渠道支付划账失败!");
            return "500";
        }
        System.out.println("微信渠道划账成功! 金额: " + amount);
        return "200";
    }
}
