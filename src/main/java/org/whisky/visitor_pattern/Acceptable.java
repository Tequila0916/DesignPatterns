package org.whisky.visitor_pattern;

/**
 * @ClassName Acceptable
 * @Description TODO
 * @Author GT-R
 * @Date 2024/9/308:37
 * @Version 1.0
 */
public interface Acceptable {
    void accept(Visitor visitor);
}
