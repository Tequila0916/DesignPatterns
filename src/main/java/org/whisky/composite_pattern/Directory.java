package org.whisky.composite_pattern;

import java.util.ArrayList;

/**
 * @ClassName Directory
 * @Description TODO
 * @Author GT-R
 * @Date 2024/7/16下午9:59
 * @Version 1.0
 */
public class Directory extends Entry{
    private String path;
    private ArrayList<Entry> directory = new ArrayList<>();

    public Directory(String path) {
        this.path = path;
    }

    @Override
    public String getName() {
        return path;
    }

    @Override
    public int getSize() {
        int size = 0;
        for (Entry entry : directory) {
            size += entry.getSize();
        }
        return size;
    }

    @Override
    public Entry add(Entry entry) {
       directory.add(entry);
        return this;
    }

    @Override
    public void printList(String prefix) {
        System.out.println(prefix+"/"+this);
         for (Entry entry : directory) {
             entry.printList(prefix+"/"+path);
         }
    }
}
