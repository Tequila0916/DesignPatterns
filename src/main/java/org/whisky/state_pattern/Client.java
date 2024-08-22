package org.whisky.state_pattern;

/**
 * @ClassName Client
 * @Description TODO
 * @Author GT-R
 * @Date 2024/8/2217:28
 * @Version 1.0
 */
public class Client {
    public static void main(String[] args) {
        TrafficLight trafficLight = new TrafficLight();
        trafficLight.setState(new GreenState());
        trafficLight.switchToGreen();
        trafficLight.switchToYellow();
        trafficLight.switchToRed();
    }
}
