package org.whisky.command_pattern;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName Order
 * @Description TODO
 * @Author GT-R
 * @Date 2024/9/2321:00
 * @Version 1.0
 */
public class Order {
    private int diningTable;
    private Map<String, Integer> foodMenu = new HashMap<>();

    public int getDiningTable() {
        return diningTable;
    }

    public void setDiningTable(int diningTable) {
        this.diningTable = diningTable;
    }

    public Map<String, Integer> getFoodMenu() {
        return foodMenu;
    }

    public void setFoodMenu(Map<String, Integer> foodMenu) {
        this.foodMenu = foodMenu;
    }
}
