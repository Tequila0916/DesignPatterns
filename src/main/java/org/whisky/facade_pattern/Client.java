package org.whisky.facade_pattern;

/**
 * @ClassName Client
 * @Description TODO
 * @Author GT-R
 * @Date 2024/7/16下午5:55
 * @Version 1.0
 */
public class Client {
    public static void main(String[] args) {
        //创建外观对象
        SmartAppliancesFacade facade = new SmartAppliancesFacade();

        facade.say("打开家电");
        facade.say("关闭家电");
    }
}
