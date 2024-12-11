package org.whisky.command_pattern;

import java.util.Set;

/**
 * @ClassName OrderCommand
 * @Description TODO
 * @Author GT-R
 * @Date 2024/9/2321:02
 * @Version 1.0
 */
public class OrderCommand implements Command {
    private Chef receiver;

    private Order order;

    public OrderCommand(Chef receiver, Order order) {
        this.receiver = receiver;
        this.order = order;
    }

    @Override
    public void execute() {
        System.out.println(order.getDiningTable() + "桌的订单: ");
        Set<String> keys = order.getFoodMenu().keySet();
        for (String key : keys) {
            receiver.makeFood(order.getFoodMenu().get(key), key);
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(order.getDiningTable() + "桌的菜已上齐.");
    }
}
