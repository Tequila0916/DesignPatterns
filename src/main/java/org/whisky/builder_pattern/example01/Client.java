package org.whisky.builder_pattern.example01;

import org.whisky.builder_pattern.example01.builder.Builder;
import org.whisky.builder_pattern.example01.builder.impl.HelloBuilder;
import org.whisky.builder_pattern.example01.builder.impl.MobikeBuilder;
import org.whisky.builder_pattern.example01.director.Director;
import org.whisky.builder_pattern.example01.product.Bike;

/**
 * @ClassName Client
 * @Description TODO
 * @Author GT-R
 * @Date 2024/6/27上午8:53
 * @Version 1.0
 */
public class Client {
    public static void main(String[] args) {
        showBike(new HelloBuilder());
        showBike(new MobikeBuilder());
    }

    private static void showBike(Builder builder) {
        Director director = new Director(builder);
        Bike bike = director.construct();
        System.out.println(bike.getFrame());
        System.out.println(bike.getSeat());
    }
}
