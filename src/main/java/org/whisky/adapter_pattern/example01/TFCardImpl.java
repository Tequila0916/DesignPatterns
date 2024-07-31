package org.whisky.adapter_pattern.example01;

/**
 * @ClassName TFCardImpl
 * @Description TODO
 * @Author GT-R
 * @Date 2024/7/16下午3:34
 * @Version 1.0
 */
public class TFCardImpl implements TFCard {
    @Override
    public String readTF() {
        String msg = "tf card reading data";
        return msg;
    }

    @Override
    public void writeTF(String msg) {
        System.out.println("tf card write data : " + msg);
    }
}
