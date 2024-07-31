package org.whisky.decorator_pattern;

/**
 * @ClassName DataLoaderDecorator
 * @Description TODO
 * @Author GT-R
 * @Date 2024/7/2下午4:39
 * @Version 1.0
 */
public class DataLoaderDecorator implements DataLoader {
    private DataLoader dataLoader;

    public DataLoaderDecorator(DataLoader dataLoader) {
        this.dataLoader = dataLoader;
    }

    @Override
    public String read() {
        return dataLoader.read();
    }

    @Override
    public void write(String data) {
        dataLoader.write(data);
    }
}
