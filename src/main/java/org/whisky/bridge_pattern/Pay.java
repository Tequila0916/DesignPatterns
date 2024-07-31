package org.whisky.bridge_pattern;

import java.math.BigDecimal;

/**
 * @ClassName Pay
 * @Description TODO
 * @Author GT-R
 * @Date 2024/6/29下午1:52
 * @Version 1.0
 */
public abstract class Pay {
    protected PayMode payMode;

    public Pay(PayMode payMode) {
        this.payMode = payMode;
    }
    //划账功能
    public abstract String transfer(String uId, String tradeId, BigDecimal amount);

}
