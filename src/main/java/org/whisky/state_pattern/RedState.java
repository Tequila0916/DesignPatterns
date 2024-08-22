package org.whisky.state_pattern;

/**
 * @ClassName RedState
 * @Description TODO
 * @Author GT-R
 * @Date 2024/8/2217:15
 * @Version 1.0
 */
public class RedState implements State {
    @Override
    public void switchToRed(TrafficLight light) {
        System.out.println("已是红灯状态无须再切换!");
    }

    @Override
    public void swtichToYellow(TrafficLight light) {
        System.out.println("红灯不能切换为黄灯!");
    }

    @Override
    public void switchToGreen(TrafficLight light) {
        System.out.println("绿灯亮起...时长:60秒!");
    }
}
