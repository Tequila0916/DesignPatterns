package org.whisky.adapter_pattern.example01;

/**
 * @ClassName SDAdapterTF2
 * @Description TODO
 * @Author GT-R
 * @Date 2024/7/16下午3:35
 * @Version 1.0
 */
public class SDAdapterTF extends TFCardImpl implements SDCard{
    @Override
    public String readSD() {
        System.out.println("adapter read tf card ");
        return readTF();
    }

    @Override
    public void writeSD(String msg) {
        System.out.println("adapter write tf card");
        writeTF(msg);
    }
}
