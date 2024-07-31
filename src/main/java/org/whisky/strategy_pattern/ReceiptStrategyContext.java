package org.whisky.strategy_pattern;

/**
 * @ClassName ReceiptStrategyContext
 * @Description TODO
 * @Author GT-R
 * @Date 2024/7/23下午4:45
 * @Version 1.0
 */
public class ReceiptStrategyContext {
    private ReceiptHandleStrategy receiptHandleStrategy;

    public void setReceiptHandleStrategy(ReceiptHandleStrategy receiptHandleStrategy) {
        this.receiptHandleStrategy = receiptHandleStrategy;
    }
    public void handleReceipt(Receipt receipt){
        if(receipt!=null){
            receiptHandleStrategy.handleReceipt(receipt);
        }
    }
}
