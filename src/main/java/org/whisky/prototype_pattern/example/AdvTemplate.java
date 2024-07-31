package org.whisky.prototype_pattern.example;

/**
 * @ClassName AdvTemplate
 * @Description TODO
 * @Author GT-R
 * @Date 2024/6/27上午11:12
 * @Version 1.0
 */
public class AdvTemplate {
    //广告信名称
    private String advSubject = "xx银行本月还款达标,可抽iPhone 13等好礼!";

    //广告信内容
    private String advContext = "达标用户请在2022年3月1日到2022年3月30参与抽奖......";

    public String getAdvSubject() {
        return this.advSubject;
    }

    public String getAdvContext() {
        return this.advContext;
    }
}
