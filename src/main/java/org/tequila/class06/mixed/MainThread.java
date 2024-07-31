package org.tequila.class06.mixed;

import org.junit.Test;

/**
 * @ClassName MainThread
 * @Description TODO
 * @Author GT-R
 * @Date 2023/8/214:48
 * @Version 1.0
 */
public class MainThread {
    public static void main(String[] args) {
        SelectorThreadGroup boss = new SelectorThreadGroup(3);
        SelectorThreadGroup worker = new SelectorThreadGroup(3);
        boss.setWorker(worker);
        boss.bind(9999);
        boss.bind(8888);
        boss.bind(6666);
        boss.bind(7777);
    }
}
