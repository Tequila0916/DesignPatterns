package org.whisky.adapter_pattern.example01;

/**
 * @ClassName SDCardImpl
 * @Description TODO
 * @Author GT-R
 * @Date 2024/7/16下午3:34
 * @Version 1.0
 */
public class SDCardImpl implements SDCard {
    @Override
    public String readSD() {
        String msg = "sd card reading data";
        return msg;
    }

    @Override
    public void writeSD(String msg) {
        System.out.println("sd card write data : " + msg);
    }
}
