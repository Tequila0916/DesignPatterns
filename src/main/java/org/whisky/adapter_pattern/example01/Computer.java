package org.whisky.adapter_pattern.example01;

/**
 * @ClassName Computer
 * @Description TODO
 * @Author GT-R
 * @Date 2024/7/16下午3:37
 * @Version 1.0
 */
public class Computer {
    public String read(SDCard sdCard){
        String data = sdCard.readSD();
        return data;
    }
}
