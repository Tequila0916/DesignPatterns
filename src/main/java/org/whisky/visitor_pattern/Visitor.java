package org.whisky.visitor_pattern;

/**
 * @ClassName Visitor
 * @Description TODO
 * @Author GT-R
 * @Date 2024/9/307:49
 * @Version 1.0
 */
public interface Visitor {
    void visit(Candy candy);

    void visit(Fruit fruit);

    void visit(Wine wine);
}
