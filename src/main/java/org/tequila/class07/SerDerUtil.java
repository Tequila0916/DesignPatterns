package org.tequila.class07;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * @ClassName SerDerUtil
 * @Description TODO
 * @Author GT-R
 * @Date 2023/8/617:10
 * @Version 1.0
 */
public class SerDerUtil {
    static ByteArrayOutputStream bos = new ByteArrayOutputStream();

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
}
