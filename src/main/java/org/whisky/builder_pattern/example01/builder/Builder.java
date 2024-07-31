package org.whisky.builder_pattern.example01.builder;

import org.whisky.builder_pattern.example01.product.Bike;

/**
 * @ClassName Builder
 * @Description TODO
 * @Author GT-R
 * @Date 2024/6/27上午8:27
 * @Version 1.0
 */
public interface Builder {
    Bike mBike = new Bike();
    void buildFrame();
    void buildSeat();
    Bike createBike();
}
