package org.tequila.class01;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @ClassName TestRandomAccessFileWrite
 * @Description TODO
 * @Author GT-R
 * @Date 2023/7/519:24
 * @Version 1.0
 */
public class TestRandomAccessFile {
    public static String path = "a.txt";

    public static void main(String[] args) throws IOException {
        RandomAccessFile file = new RandomAccessFile(path, "rw");
        file.write("Hello Tequila\n".getBytes());
        file.write("Hello Turbo\n".getBytes());
        System.out.println("write------------");

//        System.in.read();
        file.seek(0);
        file.write("What?".getBytes());
        System.out.println("seek---------");
//        System.in.read();
        //堆外 和 文件映射的
        FileChannel channel = file.getChannel();
        MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_WRITE, 26, 1024);
        map.put("Fuck!\n".getBytes());
        map.put("Null!\n".getBytes());
        System.out.println("map--put--------");
//        System.in.read();
//        map.force();
        file.seek(0);
        ByteBuffer buffer = ByteBuffer.allocateDirect(4096);

        int read = channel.read(buffer);
        System.out.println(buffer);
        buffer.flip();
        System.out.println(buffer);
        for (int i = 0; i < buffer.limit(); i++) {
            System.out.print((char) buffer.get());
        }


    }
}
