package org.whisky.strategy_pattern.impl;

import org.whisky.strategy_pattern.Receipt;
import org.whisky.strategy_pattern.ReceiptHandleStrategy;

/**
 * @ClassName MT8104
 * @Description TODO
 * @Author GT-R
 * @Date 2024/7/23下午4:44
 * @Version 1.0
 */
public class MT8104 implements ReceiptHandleStrategy {
    @Override
    public void handleReceipt(Receipt receipt) {
        System.out.println("解析报文MT8104: " + receipt.getMessage());
    }
}
