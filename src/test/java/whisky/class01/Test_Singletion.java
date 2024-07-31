package whisky.class01;

import org.junit.Test;
import org.whisky.singleton_pattern.*;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @ClassName Test_Singletion
 * @Description TODO
 * @Author GT-R
 * @Date 2024/5/29下午8:29
 * @Version 1.0
 */
public class Test_Singletion {
    @Test
    public void test01() {
        Singleton_01 instance01 = Singleton_01.getInstance();
        Singleton_01 instance02 = Singleton_01.getInstance();
        System.out.println(instance01 == instance02);
    }

    @Test
    public void test02() {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                Singleton_02 instance = Singleton_02.getInstance();
                System.out.println(Thread.currentThread().getName() + "-----" + instance);
            }).start();
        }
    }

    @Test
    public void test03() {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                Singleton_03 instance = Singleton_03.getInstance();
                System.out.println(Thread.currentThread().getName() + "-----" + instance);
            }).start();
        }
    }

    @Test
    public void test04() {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                Singleton_04 instance = Singleton_04.getInstance();
                System.out.println(Thread.currentThread().getName() + "-----" + instance);
            }).start();
        }
    }

    @Test
    public void test05() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<Singleton_05> aClass = Singleton_05.class;
        Constructor<Singleton_05> constructor = aClass.getDeclaredConstructor();
        constructor.setAccessible(true);
        Singleton_05 instance1 = constructor.newInstance();
        Singleton_05 instance2 = constructor.newInstance();

        System.out.println(instance1 == instance2);
    }

    @Test
    public void test06() throws IOException, ClassNotFoundException {
        ObjectOutputStream oop = new ObjectOutputStream(new FileOutputStream("tempFile.obj"));
        oop.writeObject(Singleton_04.getInstance());
        ObjectInputStream pis = new ObjectInputStream(new FileInputStream("tempFile.obj"));
        Singleton_04 instance01 = (Singleton_04) pis.readObject();
        Singleton_04 instance02 = Singleton_04.getInstance();
        System.out.println(instance01 == instance02);
    }
    @Test
    public void test07() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<Singleton_06> aClass = Singleton_06.class;
        Constructor<Singleton_06> constructor = aClass.getDeclaredConstructor();
        constructor.setAccessible(true);
        Singleton_06 instance1 = constructor.newInstance();
        Singleton_06 instance2 = constructor.newInstance();

        System.out.println(instance1 == instance2);
    }

    @Test
    public void test08() throws IOException, ClassNotFoundException {
        ObjectOutputStream oop = new ObjectOutputStream(new FileOutputStream("tempFile.obj"));
        oop.writeObject(Singleton_06.getInstance());
        ObjectInputStream pis = new ObjectInputStream(new FileInputStream("tempFile.obj"));
        Singleton_06 instance01 = (Singleton_06) pis.readObject();
        Singleton_06 instance02 = Singleton_06.getInstance();
        System.out.println(instance01 == instance02);
    }

}
