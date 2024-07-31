package org.whisky.prototype_pattern.shallow_clone;

import java.io.Serializable;

/**
 * @ClassName Person
 * @Description TODO
 * @Author GT-R
 * @Date 2024/6/27上午10:09
 * @Version 1.0
 */
public class Person implements Serializable {
    private String name;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
