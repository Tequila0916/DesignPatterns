package org.tequila.class07;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName ClientFactory
 * @Description TODO
 * @Author GT-R
 * @Date 2023/8/317:32
 * @Version 1.0
 */
public class ClientFactory {
    private static ClientFactory factory;

    static {
        factory = new ClientFactory();
    }

    int poolSize = 10;
    Random random = new Random();
    NioEventLoopGroup clientWorker;
    ConcurrentHashMap<InetSocketAddress, ClientPool> outBoxes = new ConcurrentHashMap<>();

    private ClientFactory() {
    }

    public static ClientFactory getFactory() {
        return factory;
    }

    public synchronized NioSocketChannel getClient(InetSocketAddress address) {
        ClientPool clientPool = outBoxes.get(address);
        if (clientPool == null) {
            outBoxes.putIfAbsent(address, new ClientPool(poolSize));
            clientPool = outBoxes.get(address);
        }
        int index = random.nextInt(poolSize);
        if (clientPool.clients[index] != null && clientPool.clients[index].isActive()) {
            return clientPool.clients[index];
        }
        synchronized (clientPool.lock[index]) {
            return clientPool.clients[index] = create(address);
        }

    }

    private NioSocketChannel create(InetSocketAddress address) {
        clientWorker = new NioEventLoopGroup(1);
        Bootstrap bootstrap = new Bootstrap();
        ChannelFuture connect = bootstrap.group(clientWorker)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                        ChannelPipeline pipeline = nioSocketChannel.pipeline();
                        pipeline.addLast(new ServerDecode())
                                .addLast(new ClientResponses());
                    }
                }).connect(address);
        try {
            NioSocketChannel client = (NioSocketChannel) connect.sync().channel();
            return client;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
