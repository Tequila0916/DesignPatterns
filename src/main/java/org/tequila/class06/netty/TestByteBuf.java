package org.tequila.class06.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.junit.Test;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName TestByteBuf
 * @Description TODO
 * @Author GT-R
 * @Date 2023/8/220:13
 * @Version 1.0
 */
public class TestByteBuf {
    @Test
    public void testByteBuf() {
//        ByteBuf buf = ByteBufAllocator.DEFAULT.buffer(8, 20);
//        ByteBuf buf = UnpooledByteBufAllocator.DEFAULT.heapBuffer(8, 20);
        ByteBuf buf = PooledByteBufAllocator.DEFAULT.directBuffer();
        print(buf);
        for (int i = 0; i < 5; i++) {
            buf.writeBytes(new byte[]{1, 2, 3, 4});
            print(buf);
        }

    }

    public void print(ByteBuf buf) {
        System.out.println("buf.isReadable()    = " + buf.isReadable());
        System.out.println("buf.readerIndex()   = " + buf.readerIndex());
        System.out.println("buf.readableBytes() = " + buf.readableBytes());
        System.out.println("buf.isWritable()    = " + buf.isWritable());
        System.out.println("buf.writerIndex()   = " + buf.writerIndex());
        System.out.println("buf.writableBytes() = " + buf.writableBytes());
        System.out.println("buf.capacity()      = " + buf.capacity());
        System.out.println("buf.maxCapacity()   = " + buf.maxCapacity());
        System.out.println("buf.isDirect()      = " + buf.isDirect());
        System.out.println(">--------------------<");
    }

    @Test
    public void testEventLoopGroup() {
        NioEventLoopGroup executors = new NioEventLoopGroup(2);
        executors.execute(() -> {
            try {
                for (; ; ) {
                    System.out.println("Hello - " + Thread.currentThread().getName());
                    TimeUnit.NANOSECONDS.sleep(1);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        executors.execute(() -> {
            try {
                for (; ; ) {
                    System.out.println("Hello - " + Thread.currentThread().getName());
                    TimeUnit.SECONDS.sleep(1);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void clientMode() throws InterruptedException {
        NioEventLoopGroup thread = new NioEventLoopGroup(1);
        NioSocketChannel client = new NioSocketChannel();
        thread.register(client);
        ChannelPipeline pipeline = client.pipeline();
        pipeline.addLast(new InHandler());

        ChannelFuture connect = client.connect(new InetSocketAddress("192.168.1.103", 9090));
        ChannelFuture sync = connect.sync();
        ByteBuf buf = Unpooled.copiedBuffer("Hello server".getBytes());
        ChannelFuture send = client.writeAndFlush(buf);
//        send.sync();
        sync.channel().closeFuture().sync();
        System.out.println("client over ......");
    }

    @Test
    public void serverMode() throws InterruptedException {
        NioEventLoopGroup thread = new NioEventLoopGroup(1);
        NioServerSocketChannel sever = new NioServerSocketChannel();
        thread.register(sever);
        ChannelPipeline pipeline = sever.pipeline();
        pipeline.addLast(new AcceptHandler(thread, new InitHandler()));
        ChannelFuture bind = sever.bind(new InetSocketAddress("192.168.1.103", 9090));
        bind.sync().channel().closeFuture().sync();
        System.out.println("server over ......");
    }

    @Test
    public void nettyClient() throws InterruptedException {
        NioEventLoopGroup thread = new NioEventLoopGroup(1);
        Bootstrap bootstrap = new Bootstrap();
        ChannelFuture connect = bootstrap.group(thread)
                .channel(NioSocketChannel.class)
//                .handler(new InitHandler())
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel socketChannel) throws Exception {
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        pipeline.addLast(new InHandler());
                    }
                })
                .connect(new InetSocketAddress("192.168.1.103", 9090));
        Channel client = connect.sync().channel();
        ByteBuf buf = Unpooled.copiedBuffer("Hello server".getBytes());
        ChannelFuture send = client.writeAndFlush(buf);
        send.sync();
        client.closeFuture().sync();

    }

    @Test
    public void nettyServer() throws InterruptedException {
        NioEventLoopGroup thread = new NioEventLoopGroup(1);
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        ChannelFuture bind = serverBootstrap.group(thread, thread)
                .channel(NioServerSocketChannel.class)
//                .childHandler(new InitHandler())
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel socketChannel) throws Exception {
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        pipeline.addLast(new InHandler());
                    }
                })
                .bind(new InetSocketAddress("192.168.1.103", 9090));
        bind.sync().channel().closeFuture().sync();

    }
}
