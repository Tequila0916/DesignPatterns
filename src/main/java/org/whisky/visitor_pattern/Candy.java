package org.whisky.visitor_pattern;

import java.time.LocalDate;

/**
 * @ClassName Candy
 * @Description TODO
 * @Author GT-R
 * @Date 2024/9/307:46
 * @Version 1.0
 */
public class Candy extends Product implements Acceptable{
    public Candy(String name, double price, LocalDate productionDate) {
        super(name, price, productionDate);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
