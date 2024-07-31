package org.tequila.class07;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName MyRPC
 * @Description TODO
 * @Author GT-R
 * @Date 2023/8/316:16
 * @Version 1.0
 */
public class MyRPC {
    @Test
    public void startServer() {
        MyCar myCar = new MyCar();
        MyPlane myPlane = new MyPlane();
        Dispatcher dispatcher = new Dispatcher();
        dispatcher.register(Car.class.getName(),myCar);
        dispatcher.register(Plane.class.getName(),myPlane);
        NioEventLoopGroup boss = new NioEventLoopGroup(50);
        NioEventLoopGroup worker = boss;
        ServerBootstrap bootstrap = new ServerBootstrap();
        ChannelFuture bind = bootstrap.group(boss, worker)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                        System.out.println("server accept client port : " + nioSocketChannel.remoteAddress().getPort());
                        ChannelPipeline pipeline = nioSocketChannel.pipeline();

                        pipeline.addLast(new ServerDecode())
                                .addLast(new ServerRequestHandler(dispatcher));
                    }
                })
                .bind(new InetSocketAddress("localhost", 9090));
        try {
            bind.sync().channel().closeFuture().sync();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void get() {
        new Thread(() -> {
            startServer();
        }).start();
        System.out.println("sever started...");
        int size = 20;
        AtomicInteger nums = new AtomicInteger(0);
        Thread[] threads = new Thread[size];
        for (int i = 0; i < size; i++) {
            threads[i] = new Thread(() -> {
                Car car = proxyGet(Car.class);
                String args = "Ferrari " + nums.incrementAndGet();
                String drive = car.drive(args);
                System.out.println("client over msg = " + drive + " src args = " + args);
            });
        }
        for (Thread thread : threads) {
            thread.start();
        }
        try {
            System.in.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


//        Plane plane = proxyGet(Plane.class);
//        plane.fly("Boeing");
    }

    private <T> T proxyGet(Class<T> interfaceInfo) {
        ClassLoader classLoader = interfaceInfo.getClassLoader();
        Class<?>[] methodInfo = {interfaceInfo};
        return (T) Proxy.newProxyInstance(classLoader, methodInfo, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // 1.调用的服务，方法，参数 --> 封装成message
                String className = interfaceInfo.getName();
                String methodName = method.getName();
                Class<?>[] parameterTypes = method.getParameterTypes();

                MyContent content = new MyContent();
                content.setClassName(className);
                content.setMethodName(methodName);
                content.setParameterTypes(parameterTypes);
                content.setArgs(args);

                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(bos);
                oos.writeObject(content);
                byte[] msgBody = bos.toByteArray();

                // 2.requestId + message --> 本地要缓存
                MyHeader header = createHeader(msgBody);
                bos.reset();
                oos = new ObjectOutputStream(bos);
                oos.writeObject(header);
                byte[] msgHeader = bos.toByteArray();
                // 3.连接池 --> 取得连接
                ClientFactory factory = ClientFactory.getFactory();
                NioSocketChannel client = factory.getClient(new InetSocketAddress("localhost", 9090));
//                CountDownLatch countDownLatch = new CountDownLatch(1);
                long requestId = header.getRequestId();
                CompletableFuture<String> res = new CompletableFuture<>();
//                ResponseMappingCallback.addCallBack(requestId, new Runnable() {
//                    @Override
//                    public void run() {
//                        countDownLatch.countDown();
//                    }
//                });
                ResponseMappingCallback.addCallBack(requestId, res);
                // 4.发送 --> 走IO(out)
                ByteBuf byteBuf = PooledByteBufAllocator.DEFAULT.directBuffer(msgBody.length + msgHeader.length);
                byteBuf.writeBytes(msgHeader);
                byteBuf.writeBytes(msgBody);
                ChannelFuture future = client.writeAndFlush(byteBuf);
                future.sync();

                // 5.如果从IO返回来了，如何（睡眠/回调/让线程停下来）等待然后继续执行

//                countDownLatch.await();
                return res.get();
            }
        });
    }

    private MyHeader createHeader(byte[] msgBody) {
        MyHeader header = new MyHeader();
        int flag = 0x14141414;
        long requestId = Math.abs(UUID.randomUUID().getLeastSignificantBits());
        int dataLength = msgBody.length;
        header.setFlag(flag);
        header.setRequestId(requestId);
        header.setDataLength(dataLength);
        return header;
    }
}
