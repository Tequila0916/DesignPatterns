package org.whisky.composite_pattern;

/**
 * @ClassName File
 * @Description TODO
 * @Author GT-R
 * @Date 2024/7/16下午9:50
 * @Version 1.0
 */
public class File extends Entry{
    private String name;
    private int size;

    public File(String name, int size) {
        this.name = name;
        this.size = size;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public Entry add(Entry entry) {
        return null;
    }

    @Override
    public void printList(String prefix) {
        System.out.println(prefix + "/" + this);
    }
}
