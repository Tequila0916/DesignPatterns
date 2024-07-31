package org.tequila.class08.rpc.transport;

import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @ClassName ClientPool
 * @Description TODO
 * @Author GT-R
 * @Date 2023/8/317:09
 * @Version 1.0
 */
public class ClientPool {
    NioSocketChannel[] clients;
    Object[] lock;

    public ClientPool(int size) {
        clients = new NioSocketChannel[size];
        lock = new Object[size];
        for (int i = 0; i < size; i++) {
            lock[i] = new Object();
        }
    }
}
