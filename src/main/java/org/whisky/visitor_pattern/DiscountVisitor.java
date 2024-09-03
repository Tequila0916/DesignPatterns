package org.whisky.visitor_pattern;

import java.text.NumberFormat;
import java.time.LocalDate;

/**
 * @ClassName DiscountVisitor
 * @Description TODO
 * @Author GT-R
 * @Date 2024/9/307:51
 * @Version 1.0
 */
public class DiscountVisitor implements Visitor {
    private LocalDate begin;

    public DiscountVisitor(LocalDate begin) {
        this.begin = begin;
        System.out.println("结算日期: " + begin);
    }

    @Override
    public void visit(Candy candy) {
        System.out.println("糖果: " + candy.getName());
        long days = begin.toEpochDay() - candy.getProductionDate().toEpochDay();
        if (days > 180) {
            System.out.println("超过半年的糖果,请勿食用!");
        } else {
            double rate = 0.9;
            double price = candy.getPrice() * rate;
            System.out.println("糖果打折后的价格" + NumberFormat.getCurrencyInstance().format(price));
        }

    }

    @Override
    public void visit(Fruit fruit) {
        System.out.println("水果: " + fruit.getName());
        long days = begin.toEpochDay() - fruit.getProductionDate().toEpochDay();
        double rate = 0;

        if (days > 7) {
            System.out.println("超过七天的水果,请勿食用!");
        } else if (days > 3) {
            rate = 0.5;
        } else {
            rate = 1;
        }
        double price = fruit.getPrice() * fruit.getWeight() * rate;
        System.out.println("水果价格: "+NumberFormat.getCurrencyInstance().format(price));
    }

    @Override
    public void visit(Wine wine) {
        System.out.println("酒类: " + wine.getName() + ",无折扣价格!");
        System.out.println("原价: " + NumberFormat.getCurrencyInstance().format(wine.getPrice()));
    }
}
