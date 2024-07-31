package org.whisky.observer_pattern;

/**
 * @ClassName LotteryService
 * @Description TODO
 * @Author GT-R
 * @Date 2024/7/19上午11:06
 * @Version 1.0
 */
public abstract class LotteryService {
    private EventManager manager;

    public LotteryService() {
        manager = new EventManager(EventManager.EventType.MQ, EventManager.EventType.Message);
        manager.addListener(EventManager.EventType.MQ, new MQEventListener());
        manager.addListener(EventManager.EventType.Message, new MessageEventListener());
    }

    public LotteryResult lotteryAndMsg(String uId){
        LotteryResult result = lottery(uId);
        manager.notify(EventManager.EventType.MQ,result);
        manager.notify(EventManager.EventType.Message,result);
        return result;
    }

    public abstract LotteryResult lottery(String uId);
}
