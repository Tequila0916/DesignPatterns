package org.whisky.memento_pattern;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @ClassName Player
 * @Description TODO
 * @Author GT-R
 * @Date 2024/9/311:20
 * @Version 1.0
 */
public class Player {
    private static String[] fruitsName = {"苹果", "葡萄", "香蕉", "橘子"};   //表示水果种类的数组
    private int money;
    private List<String> fruits = new ArrayList<>();
    private Random random = new Random();

    public Player(int money) {
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public String getFruit() {
        String prefix = "";
        if (random.nextBoolean()) {
            prefix = "好吃的";
        }
        return prefix + fruitsName[random.nextInt(fruitsName.length)];
    }

    public void yacht() {

        int dice = random.nextInt(6) + 1;   //掷骰子
        if (dice == 1) {
            money += 100;
            System.out.println("所持有的金钱增加了..");
        } else if (dice == 2) {
            money /= 2;
            System.out.println("所持有的金钱减半..");
        } else if (dice == 6) {   //获取水果
            String fruit = getFruit();
            System.out.println("获得了水果: " + fruit);
            fruits.add(fruit);
        } else {
            //骰子结果为3、4、5
            System.out.println("无效数字,继续投掷");
        }
    }

    public Memento createMemento() {
        Memento memento = new Memento(money);
        for (String fruit : fruits) {
            if (fruit.startsWith("好吃的")) {
                memento.addFruit(fruit);
            }
        }
        return memento;
    }

    public void restoreMemento(Memento memento) {
        money = memento.getMoney();
        fruits = memento.getFruits();
    }

    @Override
    public String toString() {
        return "Player{" +
                "money=" + money +
                ", fruits=" + fruits +
                '}';
    }
}
