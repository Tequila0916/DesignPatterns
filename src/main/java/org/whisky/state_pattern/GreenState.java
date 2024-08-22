package org.whisky.state_pattern;

/**
 * @ClassName GreenState
 * @Description TODO
 * @Author GT-R
 * @Date 2024/8/2217:20
 * @Version 1.0
 */
public class GreenState implements State {
    @Override
    public void switchToRed(TrafficLight light) {
        System.out.println("绿灯不能立刻切换为红灯!");
    }

    @Override
    public void swtichToYellow(TrafficLight light) {
        System.out.println("黄灯亮起...时长:10秒!");
    }

    @Override
    public void switchToGreen(TrafficLight light) {
        System.out.println("已是绿灯状态无须再切换!");
    }
}
