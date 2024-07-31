package org.whisky.builder_pattern.example01.director;

import org.whisky.builder_pattern.example01.builder.Builder;
import org.whisky.builder_pattern.example01.product.Bike;

/**
 * @ClassName Director
 * @Description TODO
 * @Author GT-R
 * @Date 2024/6/27上午8:33
 * @Version 1.0
 */
public class Director {
    private Builder builder;
    public Director(Builder builder) {
        this.builder = builder;
    }
    public Bike construct() {
        builder.buildFrame();
        builder.buildSeat();
        return builder.createBike();
    }
}
