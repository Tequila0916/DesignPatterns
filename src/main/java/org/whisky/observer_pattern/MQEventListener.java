package org.whisky.observer_pattern;

/**
 * @ClassName MQEventListener
 * @Description TODO
 * @Author GT-R
 * @Date 2024/7/18下午9:17
 * @Version 1.0
 */
public class MQEventListener implements EventListener{
    @Override
    public void doEvent(LotteryResult result) {
        System.out.println("记录用户摇号结果(MQ), 用户ID:" +  result.getuId() +
                ",摇号结果:" + result.getMsg());
    }
}
