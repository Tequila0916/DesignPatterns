package org.whisky.adapter_pattern.example01;

/**
 * @ClassName TFCard
 * @Description TODO
 * @Author GT-R
 * @Date 2024/7/16下午3:33
 * @Version 1.0
 */
public interface TFCard {
    //读取TF卡方法
    String readTF();
    //写入TF卡功能
    void writeTF(String msg);
}
