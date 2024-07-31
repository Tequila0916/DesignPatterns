package org.whisky.builder_pattern.example01.builder.impl;

import org.whisky.builder_pattern.example01.builder.Builder;
import org.whisky.builder_pattern.example01.product.Bike;

/**
 * @ClassName HelloBuilder
 * @Description TODO
 * @Author GT-R
 * @Date 2024/6/27上午8:29
 * @Version 1.0
 */
public class HelloBuilder implements Builder {
    @Override
    public void buildFrame() {
        mBike.setFrame("碳纤维车架");
    }

    @Override
    public void buildSeat() {
        mBike.setSeat("橡胶车座");
    }

    @Override
    public Bike createBike() {
        return mBike;
    }
}
