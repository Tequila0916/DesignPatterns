package org.whisky.mediator_pattern;

/**
 * @ClassName Person
 * @Description TODO
 * @Author GT-R
 * @Date 2024/9/2516:40
 * @Version 1.0
 */
public abstract class Person {
    protected String name;
    protected Mediator mediator;

    public Person(String name, Mediator mediator) {
        this.name = name;
        this.mediator = mediator;
    }
}
