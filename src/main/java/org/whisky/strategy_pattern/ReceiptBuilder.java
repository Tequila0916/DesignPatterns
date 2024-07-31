package org.whisky.strategy_pattern;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ReceiptBuilder
 * @Description TODO
 * @Author GT-R
 * @Date 2024/7/23下午4:40
 * @Version 1.0
 */
public class ReceiptBuilder {
    public static List<Receipt> genReceiptList(){
        List<Receipt> receipts = new ArrayList<>();
        receipts.add(new Receipt("MT1101回执","MT1011"));
//        receipts.add(new Receipt("MT2101回执","MT2101"));
//        receipts.add(new Receipt("MT4101回执","MT4101"));
//        receipts.add(new Receipt("MT8104回执","MT8104"));
        return receipts;
    }
}
