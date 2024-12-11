package org.whisky.mediator_pattern;

/**
 * @ClassName Tenant
 * @Description TODO
 * @Author GT-R
 * @Date 2024/9/2517:33
 * @Version 1.0
 */
public class Tenant extends Person{
    public Tenant(String name, Mediator mediator) {
        super(name, mediator);
    }
    //与中介者联系
    public void contact(String message){
        mediator.contact(message,this);
    }

    //获取信息
    public void getMessage(String message){
        System.out.println("租房者" + name + "获取到的信息: " + message);
    }
}
