package org.whisky.strategy_pattern.impl;

import org.whisky.strategy_pattern.Receipt;
import org.whisky.strategy_pattern.ReceiptHandleStrategy;

/**
 * @ClassName Mt1011
 * @Description TODO
 * @Author GT-R
 * @Date 2024/7/23下午4:42
 * @Version 1.0
 */
public class MT1011 implements ReceiptHandleStrategy {
    @Override
    public void handleReceipt(Receipt receipt) {
        System.out.println("解析报文MT1011: " + receipt.getMessage());
    }
}
