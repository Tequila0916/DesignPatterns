package org.tequila.class08.service;

/**
 * @ClassName MyCar
 * @Description TODO
 * @Author GT-R
 * @Date 2023/8/619:44
 * @Version 1.0
 */
public class MyCar implements Car {
    @Override
    public String drive(String msg) {
        System.out.println("server,get client arg:" + msg);
        return "server res" + msg;
    }

    @Override
    public Person getOff(String name, Integer age) {
        Person person = new Person(name, age);
        return person;
    }
}
