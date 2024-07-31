package org.whisky.decorator_pattern;

/**
 * @ClassName TestDecorator
 * @Description TODO
 * @Author GT-R
 * @Date 2024/7/2下午4:51
 * @Version 1.0
 */
public class TestDecorator {
    public static void main(String[] args) {

        String info = "name:tom,age:15";
        DataLoaderDecorator decorator = new EncryptionDataDecorator(new BaseFileDataLoader("demo.txt"));
        decorator.write(info);
        String read = decorator.read();
        System.out.println(read);
    }
}
