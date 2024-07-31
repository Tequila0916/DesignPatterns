package org.whisky.bridge_pattern;

/**
 * @ClassName PayMode
 * @Description TODO
 * @Author GT-R
 * @Date 2024/6/29下午1:50
 * @Version 1.0
 */
public interface PayMode {
    //安全校验功能: 对各种支付模式进行风控校验
    boolean security(String uId);
}
