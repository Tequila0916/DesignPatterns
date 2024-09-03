package org.whisky.visitor_pattern;

import java.time.LocalDate;

/**
 * @ClassName Fruit
 * @Description TODO
 * @Author GT-R
 * @Date 2024/9/307:47
 * @Version 1.0
 */
public class Fruit extends Product implements Acceptable{
    private float weight;

    public Fruit(String name, double price, LocalDate productionDate, float weight) {
        super(name, price, productionDate);
        this.weight = weight;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
