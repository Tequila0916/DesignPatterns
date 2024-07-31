package org.tequila.class08.rpc.transport;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;
import org.tequila.class08.rpc.ResponseMappingCallback;
import org.tequila.class08.rpc.protocol.MyContent;
import org.tequila.class08.rpc.protocol.MyHeader;
import org.tequila.class08.util.SerDerUtil;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.URL;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
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

    public static CompletableFuture<Object> transport(MyContent content) {
        String type = "http";
        CompletableFuture<Object> res = new CompletableFuture<>();
        if (type.equals("rpc")) {
            rpcTransport(content, res);
        } else {
//            urlTransport(content, res);
            nettyTransport(content, res);
        }
        return res;
    }

    private static void rpcTransport(MyContent content, CompletableFuture<Object> res) {
        byte[] msgBody = SerDerUtil.serialize(content);
        // 2.requestId + message --> 本地要缓存
        MyHeader header = MyHeader.createHeader(msgBody);
        byte[] msgHeader = SerDerUtil.serialize(header);
//        System.out.println("main:::" + msgHeader.length);
        // 3.连接池 --> 取得连接
        NioSocketChannel client = factory.getClient(new InetSocketAddress("localhost", 9090));
        long requestId = header.getRequestId();

        ResponseMappingCallback.addCallBack(requestId, res);
        // 4.发送 --> 走IO(out)
        ByteBuf byteBuf = PooledByteBufAllocator.DEFAULT.directBuffer(msgBody.length + msgHeader.length);
        byteBuf.writeBytes(msgHeader);
        byteBuf.writeBytes(msgBody);
        ChannelFuture future = client.writeAndFlush(byteBuf);
    }

    private static void urlTransport(MyContent content, CompletableFuture<Object> res) {
        Object object = null;
        try {
            URL url = new URL("http://localhost:9090/");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //post
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setDoInput(true);

            OutputStream os = connection.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(content);

            if (connection.getResponseCode() == 200) {
                InputStream is = connection.getInputStream();
                ObjectInputStream ois = new ObjectInputStream(is);
                MyContent response = (MyContent) ois.readObject();
                object = response.getMessage();
            }

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        res.complete(object);
    }

    private static void nettyTransport(MyContent content, CompletableFuture<Object> res) {
        NioEventLoopGroup group = new NioEventLoopGroup(1);
        Bootstrap bootstrap = new Bootstrap();
        Bootstrap client = bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                        ChannelPipeline pipeline = nioSocketChannel.pipeline();
                        pipeline.addLast(new HttpClientCodec())
                                .addLast(new HttpObjectAggregator(1024 * 512))
                                .addLast(new ClientRequestHandler(res));
                    }
                });
        try {
            ChannelFuture future = client.connect("localhost", 9090).sync();
            Channel clientChannel = future.channel();
            byte[] serialize = SerDerUtil.serialize(content);
            DefaultFullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_0, HttpMethod.POST, "/", Unpooled.copiedBuffer(serialize));
            request.headers().set(HttpHeaderNames.CONTENT_LENGTH,serialize.length);
            clientChannel.writeAndFlush(request).sync();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }

    public NioSocketChannel getClient(InetSocketAddress address) {
        ClientPool clientPool = outBoxes.get(address);
        if (clientPool == null) {
            synchronized (outBoxes) {
                clientPool = outBoxes.get(address);
                if (clientPool == null) {
                    outBoxes.putIfAbsent(address, new ClientPool(poolSize));
                    clientPool = outBoxes.get(address);
                }
            }
        }
        int index = random.nextInt(poolSize);
        if (clientPool.clients[index] != null && clientPool.clients[index].isActive()) {
            return clientPool.clients[index];
        } else {
            synchronized (clientPool.lock[index]) {
                if (clientPool.clients[index] == null || !clientPool.clients[index].isActive()) {
                    clientPool.clients[index] = create(address);
                }
            }
            return clientPool.clients[index];
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
