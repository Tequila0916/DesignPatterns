package org.whisky.visitor_pattern;

import java.time.LocalDate;

/**
 * @ClassName Product
 * @Description TODO
 * @Author GT-R
 * @Date 2024/9/307:44
 * @Version 1.0
 */
public abstract class Product {
    private String name;
    private double price;
    private LocalDate productionDate;

    public Product(String name, double price, LocalDate productionDate) {
        this.name = name;
        this.price = price;
        this.productionDate = productionDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(LocalDate productionDate) {
        this.productionDate = productionDate;
    }
}
