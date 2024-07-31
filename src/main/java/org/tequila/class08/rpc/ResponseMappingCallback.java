package org.tequila.class08.rpc;


import org.tequila.class08.util.PackageMessage;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName ResponseMappingCallback
 * @Description TODO
 * @Author GT-R
 * @Date 2023/8/320:18
 * @Version 1.0
 */
public class ResponseMappingCallback {
    static ConcurrentHashMap<Long, CompletableFuture> mapping = new ConcurrentHashMap<>();

    public static void addCallBack(long requestId, CompletableFuture task) {
        mapping.putIfAbsent(requestId, task);
    }

    public static void runCallBack(PackageMessage message) {
        CompletableFuture future = mapping.get(message.getHeader().getRequestId());
        future.complete(message.getContent().getMessage());
        removeCallBack(message.getHeader().getRequestId());
    }

    private static void removeCallBack(long requestId) {
        mapping.remove(requestId);
    }

}
