package org.whisky.strategy_pattern;

import java.util.List;

/**
 * @ClassName Client
 * @Description TODO
 * @Author GT-R
 * @Date 2024/7/23下午4:51
 * @Version 1.0
 */
public class Client {
    public static void main(String[] args) {
        List<Receipt> receipts = ReceiptBuilder.genReceiptList();
        ReceiptStrategyContext receiptStrategyContext = new ReceiptStrategyContext();
        for (Receipt receipt : receipts) {
            ReceiptHandleStrategyFactory.init();
            ReceiptHandleStrategy strategy = ReceiptHandleStrategyFactory.getStrategy(receipt.getType());
            receiptStrategyContext.setReceiptHandleStrategy(strategy);
            receiptStrategyContext.handleReceipt(receipt);
        }
    }
}
