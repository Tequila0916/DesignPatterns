package org.whisky.state_pattern;

/**
 * @ClassName YellowState
 * @Description TODO
 * @Author GT-R
 * @Date 2024/8/2217:17
 * @Version 1.0
 */
public class YellowState implements State {
    @Override
    public void switchToRed(TrafficLight light) {
        System.out.println("红灯亮起...时长:90秒!");
    }

    @Override
    public void swtichToYellow(TrafficLight light) {
        System.out.println("已是黄灯状态无须再切换!");
    }

    @Override
    public void switchToGreen(TrafficLight light) {
        System.out.println("黄灯不能切换为绿灯!");
    }
}
