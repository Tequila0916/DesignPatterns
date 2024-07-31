package org.whisky.adapter_pattern.example01;

import org.whisky.adapter_pattern.example02.SDAdapterTF2;

/**
 * @ClassName Client
 * @Description TODO
 * @Author GT-R
 * @Date 2024/7/16下午3:37
 * @Version 1.0
 */
public class Client {
    public static void main(String[] args) {
        Computer computer = new Computer();
        SDCard sdCard = new SDCardImpl();
        System.out.println(computer.read(sdCard));
//        SDAdapterTF  adapterTF = new SDAdapterTF();
//        System.out.println(computer.read(adapterTF));
        TFCard tfCard = new TFCardImpl();
        SDAdapterTF2 sdAdapterTF2 = new SDAdapterTF2(tfCard);
        System.out.println(computer.read(sdAdapterTF2));
    }
}
