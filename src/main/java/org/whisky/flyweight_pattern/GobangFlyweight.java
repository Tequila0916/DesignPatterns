package org.whisky.flyweight_pattern;

/**
 * @ClassName GobangFlyweight
 * @Description TODO
 * @Author GT-R
 * @Date 2024/7/17下午3:51
 * @Version 1.0
 */
public abstract class GobangFlyweight {
    public abstract String getColor();
    public void display(){
        System.out.println("棋子颜色: " + this.getColor());
    }
}
