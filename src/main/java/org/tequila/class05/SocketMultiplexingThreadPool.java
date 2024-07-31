package org.tequila.class05;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName SocketMultiplexingThreadPool
 * @Description TODO
 * @Author GT-R
 * @Date 2023/7/3116:12
 * @Version 1.0
 */
public class SocketMultiplexingThreadPool {
    private ServerSocketChannel server = null;
    private int port = 9090;
    private Selector selector = null;
    private ExecutorService pool = null;

    public void initServer() {
        try {
            server = ServerSocketChannel.open();
            server.bind(new InetSocketAddress(port));
            server.configureBlocking(false);
            selector = Selector.open();
            server.register(selector, SelectionKey.OP_ACCEPT);
            pool = Executors.newFixedThreadPool(10);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void start() {
        initServer();
        System.out.println("服务器启动了。。。。。");
        try {
            while (true) {
                while (selector.select(50) > 0) {
                    Set<SelectionKey> keySet = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = keySet.iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        iterator.remove();
                        if (key.isAcceptable()) {
                            acceptHandler(key);
                        } else if (key.isReadable()) {
                            key.cancel();
                            readHandler(key);
                        } else if (key.isWritable()) {
                            key.cancel();
                            writeHandler(key);
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeHandler(SelectionKey key) {
        pool.execute(()->{
            System.out.println("write handler...");
            SocketChannel client = (SocketChannel) key.channel();
            ByteBuffer buffer = (ByteBuffer) key.attachment();
            buffer.flip();
            while (buffer.hasRemaining()){
                try {
                    client.write(buffer);
                    System.out.println(Thread.currentThread().getName()+ " " + "write");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            buffer.clear();
            try {
                client.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void readHandler(SelectionKey key) {
        pool.execute(() -> {
            System.out.println("read handler.....");
            SocketChannel client = (SocketChannel) key.channel();
            ByteBuffer buffer = (ByteBuffer) key.attachment();
            buffer.clear();
            int read = 0;
            try {
                while (true) {
                    read = client.read(buffer);
                    System.out.println(Thread.currentThread().getName()+ " " + read);
                    if (read > 0) {
                        client.register(selector, SelectionKey.OP_WRITE, buffer);
                    } else if (read == 0) {
                        break;
                    } else {
                        client.close();
                        break;
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void acceptHandler(SelectionKey key) {
            try {
                ServerSocketChannel server = (ServerSocketChannel) key.channel();
                SocketChannel client = server.accept();

                client.configureBlocking(false);
                ByteBuffer byteBuffer = ByteBuffer.allocate(8192);
                client.register(selector, SelectionKey.OP_READ, byteBuffer);
                System.out.println("新客户端：" + client.getRemoteAddress());
                System.out.println(Thread.currentThread().getName()+ " " + "accept");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }
    @Test
    public void testWrite(){
        SocketMultiplexingThreadPool threadPool = new SocketMultiplexingThreadPool();
        threadPool.start();
    }
}
