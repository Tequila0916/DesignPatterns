package org.whisky.memento_pattern;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Memento
 * @Description TODO
 * @Author GT-R
 * @Date 2024/9/311:32
 * @Version 1.0
 */
public class Memento {
    int money;
    ArrayList fruits;

    Memento(int money) {
        this.money = money;
        this.fruits = new ArrayList();
    }

    int getMoney() {
        return money;
    }


    List getFruits() {
        return (List) fruits.clone();
    }

    void addFruit(String fruit) {
        fruits.add(fruit);
    }

}
