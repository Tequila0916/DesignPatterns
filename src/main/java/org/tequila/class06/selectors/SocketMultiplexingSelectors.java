package org.tequila.class06.selectors;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName SocketMultiplexingSelectors
 * @Description TODO
 * @Author GT-R
 * @Date 2023/7/3121:11
 * @Version 1.0
 */
public class SocketMultiplexingSelectors {
    private final int port = 9090;
    private ServerSocketChannel server;
    private Selector selectorFirst;
    private Selector selectorSecond;
    private Selector selectorThird;

    public void initServer() {
        try {
            server = ServerSocketChannel.open();
            server.configureBlocking(false);
            server.bind(new InetSocketAddress(port));
            selectorFirst = Selector.open();
            selectorSecond = Selector.open();
            selectorThird = Selector.open();
            server.register(selectorFirst, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testNioThread(){
        SocketMultiplexingSelectors selectors = new SocketMultiplexingSelectors();
        selectors.initServer();
        NioThread T1 = new NioThread(selectors.selectorFirst, 2);
        NioThread T2 = new NioThread(selectors.selectorSecond);
        NioThread T3 = new NioThread(selectors.selectorThird);
        T1.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        T2.start();
        T3.start();
        System.out.println("服务器启动了。。。。。");

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
