package org.whisky.state_pattern;

/**
 * @ClassName TrafficLight
 * @Description TODO
 * @Author GT-R
 * @Date 2024/8/2214:17
 * @Version 1.0
 */
public class TrafficLight {
    State state = new RedState();

    public void setState(State state) {
        this.state = state;
    }
    public void switchToGreen() {
        state.switchToGreen(this);
    }
    public void switchToYellow() {
        state.swtichToYellow(this);
    }
    public void switchToRed() {
        state.switchToRed(this);
    }
}
