package org.whisky.observer_pattern;

/**
 * @ClassName MessageEventListener
 * @Description TODO
 * @Author GT-R
 * @Date 2024/7/18下午9:16
 * @Version 1.0
 */
public class MessageEventListener implements  EventListener{
    @Override
    public void doEvent(LotteryResult result) {
        System.out.println("发送短信通知用户ID为: " + result.getuId() +
                ",您的摇号结果如下: " + result.getMsg());
    }
}
