package org.tequila.class07;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName Dispatcher
 * @Description TODO
 * @Author GT-R
 * @Date 2023/8/619:49
 * @Version 1.0
 */
public class Dispatcher {
    public static ConcurrentHashMap<String,Object> invokeMap = new ConcurrentHashMap<>();
    public void register(String k,Object obj){
        invokeMap.put(k,obj);
    }
    public Object take(String k){
        return invokeMap.get(k);
    }
}
