package org.whisky.prototype_pattern.shallow_clone;

import java.io.Serializable;

/**
 * @ClassName ConcretePrototype
 * @Description TODO
 * @Author GT-R
 * @Date 2024/6/27上午10:10
 * @Version 1.0
 */
public class ConcretePrototype implements Cloneable,Serializable {
    private Person  person;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
    public void show(){
        System.out.println("嫌疑人姓名: " +person.getName());
    }

    public ConcretePrototype() {
        System.out.println("具体的原型对象创建完成!");
    }

    @Override
    public ConcretePrototype clone() throws CloneNotSupportedException {
        System.out.println("具体的原型对象复制成功!");
        return (ConcretePrototype)super.clone();
    }
}
