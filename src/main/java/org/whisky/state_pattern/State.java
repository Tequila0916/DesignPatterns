package org.whisky.state_pattern;

/**
 * @ClassName State
 * @Description TODO
 * @Author GT-R
 * @Date 2024/8/2217:14
 * @Version 1.0
 */
public interface State {
    void switchToRed(TrafficLight light);
    void swtichToYellow(TrafficLight light);
    void switchToGreen(TrafficLight light);
}
