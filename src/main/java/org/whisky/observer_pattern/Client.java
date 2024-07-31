package org.whisky.observer_pattern;

/**
 * @ClassName Client
 * @Description TODO
 * @Author GT-R
 * @Date 2024/7/19上午11:14
 * @Version 1.0
 */
public class Client {
    public static void main(String[] args) {
        LotteryService service = new LotteryServiceImpl();
        LotteryResult result = service.lotteryAndMsg("1234567");
        System.out.println(result);
    }
}
