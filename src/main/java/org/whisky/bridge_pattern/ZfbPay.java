package org.whisky.bridge_pattern;

import java.math.BigDecimal;

/**
 * @ClassName ZfbPay
 * @Description TODO
 * @Author GT-R
 * @Date 2024/6/29下午1:55
 * @Version 1.0
 */
public class ZfbPay extends Pay {
    public ZfbPay(PayMode payMode) {
        super(payMode);
    }

    @Override
    public String transfer(String uId, String tradeId, BigDecimal amount) {
        System.out.println("支付宝渠道支付划账开始......");
        boolean security = payMode.security(uId);
        System.out.println("支付宝渠道支付风险校验: " + uId + " , " + tradeId + " , " + security);
        if (!security) {
            System.out.println("支付宝渠道支付划账失败!");
            return "500";
        }
        System.out.println("支付宝渠道划账成功! 金额: " + amount);
        return "200";
    }
}
