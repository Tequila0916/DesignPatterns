package org.whisky.visitor_pattern;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName Client
 * @Description TODO
 * @Author GT-R
 * @Date 2024/9/308:36
 * @Version 1.0
 */
public class Client {
    public static void main(String[] args) {

        //德芙巧克力,生产日期2002-5-1 ,原价 10元
//        Candy candy = new Candy("德芙巧克力", 10.0, LocalDate.of(2022, 5, 1));
//
//        Visitor visitor = new DiscountVisitor(LocalDate.of(2022, 10, 11));
//        visitor.visit(candy);
        List<Acceptable> products = Arrays.asList(
                new Candy("金丝猴奶糖", 10.00, LocalDate.of(2022,6,10)),
                new Wine("衡水老白干", 100.00, LocalDate.of(2020,6,10)),
                new Fruit("草莓", 50.00, LocalDate.of(2022,10,12),1)
        );

        Visitor visitor = new DiscountVisitor(LocalDate.of(2022,10,17));
        for (Acceptable product : products) {
            product.accept(visitor);
        }
    }
}
