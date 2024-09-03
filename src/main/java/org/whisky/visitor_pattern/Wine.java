package org.whisky.visitor_pattern;

import java.time.LocalDate;

/**
 * @ClassName Wine
 * @Description TODO
 * @Author GT-R
 * @Date 2024/9/307:47
 * @Version 1.0
 */
public class Wine extends Product implements Acceptable{
    public Wine(String name, double price, LocalDate productionDate) {
        super(name, price, productionDate);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
