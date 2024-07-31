package org.whisky.responsibility_pattern.example01;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName AuthService
 * @Description TODO
 * @Author GT-R
 * @Date 2024/7/31下午9:19
 * @Version 1.0
 */
public class AuthService {
    private static Map<String, Date> authMap = new HashMap<>();

    public static void auth(String uId, String orderId) {
        System.out.println("进入审批流程,审批人ID: " + uId);
        authMap.put(uId.concat(orderId), new Date());
    }

    public static Date queryAuthInfo(String uId, String orderId) {
        return authMap.get(uId.concat(orderId));
    }
}
