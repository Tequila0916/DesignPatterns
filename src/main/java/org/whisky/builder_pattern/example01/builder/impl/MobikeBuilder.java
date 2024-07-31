package org.whisky.builder_pattern.example01.builder.impl;

import org.whisky.builder_pattern.example01.builder.Builder;
import org.whisky.builder_pattern.example01.product.Bike;

/**
 * @ClassName MobikeBuilder
 * @Description TODO
 * @Author GT-R
 * @Date 2024/6/27上午8:32
 * @Version 1.0
 */
public class MobikeBuilder implements Builder {
    @Override
    public void buildFrame() {
        mBike.setFrame("铝合金车架");
    }

    @Override
    public void buildSeat() {
        mBike.setSeat("真皮车座");
    }

    @Override
    public Bike createBike() {
        return mBike;
    }
}
