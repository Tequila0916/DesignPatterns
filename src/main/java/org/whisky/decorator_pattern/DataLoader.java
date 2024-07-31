package org.whisky.decorator_pattern;

/**
 * @ClassName DataLoader
 * @Description TODO
 * @Author GT-R
 * @Date 2024/7/2下午4:34
 * @Version 1.0
 */
public interface DataLoader {
    String read();
    void write(String data);
}
