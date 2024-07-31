package org.tequila.class08.util;

import java.io.*;

/**
 * @ClassName SerDerUtil
 * @Description TODO
 * @Author GT-R
 * @Date 2023/8/617:10
 * @Version 1.0
 */
public class SerDerUtil {
    static ByteArrayOutputStream bos = new ByteArrayOutputStream();
    static ByteArrayInputStream bis = null;

    public synchronized static byte[] serialize(Object obj) {
        bos.reset();
        ObjectOutputStream oos = null;
        byte[] result = null;
        try {
            oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            result = bos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;

    }

    public synchronized static Object deserialize(byte[] bytes) {
        bis = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = null;
        Object object = null;
        try {
            ois = new ObjectInputStream(bis);
            object = ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return object;
    }
}
