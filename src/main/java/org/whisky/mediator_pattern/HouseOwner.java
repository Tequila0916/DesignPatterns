package org.whisky.mediator_pattern;

/**
 * @ClassName HouseOwner
 * @Description TODO
 * @Author GT-R
 * @Date 2024/9/2517:32
 * @Version 1.0
 */
public class HouseOwner extends Person{
    public HouseOwner(String name, Mediator mediator) {
        super(name, mediator);
    }
    public void contact(String message){
        mediator.contact(message,this);
    }
    public void getMessage(String message){
        System.out.println("房主" + name + "获取到的信息: " + message);
    }
}
