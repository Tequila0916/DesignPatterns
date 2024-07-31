package org.whisky.facade_pattern;

/**
 * @ClassName SmartAppliancesFacade
 * @Description TODO
 * @Author GT-R
 * @Date 2024/7/16下午5:53
 * @Version 1.0
 */
public class SmartAppliancesFacade {
    private TV tv;
    private Light light;
    private AirCondition airCondition;

    public SmartAppliancesFacade() {
        tv = new TV();
        light = new Light();
        airCondition = new AirCondition();
    }
    public void say(String message){
        if(message.contains("打开")){
            on();
        }else if(message.contains("关闭")){
            off();
        }else{
            System.out.println("对不起没有听清楚您说什么! 请重新再说一遍");
        }

    }

    //起床后 语音开启 电灯 电视 空调
    private void on() {
        System.out.println("起床了!");
        light.on();
        tv.on();
        airCondition.on();
    }

    //睡觉前 语音关闭 电灯 电视 空调
    private void off() {
        System.out.println("睡觉了!");
        light.off();
        tv.off();
        airCondition.off();
    }
}
