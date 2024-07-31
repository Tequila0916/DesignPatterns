package org.whisky.observer_pattern;

import java.util.*;

/**
 * @ClassName EventManager
 * @Description TODO
 * @Author GT-R
 * @Date 2024/7/19上午10:58
 * @Version 1.0
 */
public class EventManager {
    public enum EventType {
        MQ,Message
    }

    public EventManager(Enum<EventType>... eventType) {
        for (Enum<EventType> eventTypeEnum : eventType) {
            this.listeners.put(eventTypeEnum,new ArrayList<>());
        }
    }

    Map<Enum<EventType>, List<EventListener>> listeners = new HashMap<>();
    public void addListener(Enum<EventType> type, EventListener listener) {
       List<EventListener> list = listeners.get(type);
       list.add(listener);
    }
    public void removeListener(Enum<EventType> type, EventListener listener) {
        List<EventListener> list = listeners.get(type);
        list.remove(listener);
    }
    public void notify(Enum<EventType> type, LotteryResult result) {
        List<EventListener> list = listeners.get(type);
        for (EventListener eventListener : list) {
            eventListener.doEvent(result);
        }
    }
}
