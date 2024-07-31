package org.whisky.adapter_pattern.example02;

import org.whisky.adapter_pattern.example01.SDCard;
import org.whisky.adapter_pattern.example01.TFCard;

/**
 * @ClassName SDAdapterTF2
 * @Description TODO
 * @Author GT-R
 * @Date 2024/7/16下午3:49
 * @Version 1.0
 */
public class SDAdapterTF2 implements SDCard {
    private TFCard tfCard;

    public SDAdapterTF2(TFCard tfCard) {
        this.tfCard = tfCard;
    }

    @Override
    public String readSD() {
        System.out.println("adapter read tf card ");
        return tfCard.readTF();
    }

    @Override
    public void writeSD(String msg) {
        System.out.println("adapter write tf card");
        tfCard.writeTF(msg);
    }
}
