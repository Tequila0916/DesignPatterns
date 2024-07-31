package org.tequila.class06.mixed;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.Channel;
import java.nio.channels.ServerSocketChannel;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName SelectorThreadGroup
 * @Description TODO
 * @Author GT-R
 * @Date 2023/8/214:56
 * @Version 1.0
 */
public class SelectorThreadGroup {
    SelectorThread[] threads;
    ServerSocketChannel server;
    AtomicInteger xid = new AtomicInteger(0);
    SelectorThreadGroup group;

    public SelectorThreadGroup(int size) {
        group = this;
        threads = new SelectorThread[size];
        for (int i = 0; i < size; i++) {
            threads[i] = new SelectorThread(this);
            new Thread(threads[i]).start();
        }
    }

    public void bind(int port) {
        try {
            server = ServerSocketChannel.open();
            server.configureBlocking(false);
            server.bind(new InetSocketAddress(port));
            nextSelector(server);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void nextWorkerSelector(Channel server) {
        SelectorThread thread = nextworker();
        thread.queue.add(server);
        thread.selector.wakeup();
    }

    public void nextSelector(Channel channel) {
        try {
            SelectorThread thread = next();
            thread.queue.put(channel);
            thread.setWorker(group);
            thread.selector.wakeup();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    private SelectorThread nextworker(){
        int index = xid.incrementAndGet() % group.threads.length;
        return group.threads[index];
    }

    private SelectorThread next() {
        int index = xid.incrementAndGet() % threads.length;
        return threads[index];
    }
    public void setWorker(SelectorThreadGroup worker) {
        group = worker;
    }
}
