package org.whisky.adapter_pattern.example01;

/**
 * @ClassName SDCard
 * @Description TODO
 * @Author GT-R
 * @Date 2024/7/16下午3:32
 * @Version 1.0
 */
public interface SDCard {
    //读取SD卡方法
    String readSD();
    //写入SD卡功能
    void writeSD(String msg);
}
