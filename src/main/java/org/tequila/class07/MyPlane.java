package org.tequila.class07;

/**
 * @ClassName MyPlane
 * @Description TODO
 * @Author GT-R
 * @Date 2023/8/619:47
 * @Version 1.0
 */
public class MyPlane implements Plane{
    @Override
    public void fly(String msg) {
        System.out.println("sever,get client arg:" + msg);
    }
}
