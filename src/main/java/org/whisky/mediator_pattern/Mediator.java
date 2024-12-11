package org.whisky.mediator_pattern;

/**
 * @ClassName Mediator
 * @Description TODO
 * @Author GT-R
 * @Date 2024/9/2516:40
 * @Version 1.0
 */
public abstract class Mediator {

    //申明一个联络方法
    public abstract void contact(String message,Person person);
}
