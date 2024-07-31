package org.whisky.builder_pattern.example02;

/**
 * @ClassName MainAPP
 * @Description TODO
 * @Author GT-R
 * @Date 2024/6/27上午9:35
 * @Version 1.0
 */
public class MainAPP {
    public static void main(String[] args) {
        //使用链式编程设置参数
        RabbitMQClient client = new RabbitMQClient.Builder().setHost("192.168.52.123").setMode(2).setExchange("text-exchange")
                .setPort(5672).setDurable(true).build();
        client.sendMessage("Test");
    }
}
