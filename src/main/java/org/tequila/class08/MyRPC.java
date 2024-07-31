package org.tequila.class08;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.junit.Test;
import org.tequila.class08.proxy.MyProxy;
import org.tequila.class08.rpc.Dispatcher;
import org.tequila.class08.rpc.transport.HttpRequestHandler;
import org.tequila.class08.rpc.transport.MyHttpRPCHandler;
import org.tequila.class08.service.*;

import java.io.IOException;
import java.net.InetSocketAddress;
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
        Dispatcher dispatcher = Dispatcher.getDispatcher();
        dispatcher.register(Car.class.getName(), myCar);
        dispatcher.register(Plane.class.getName(), myPlane);
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
                        //rpc协议
//                        pipeline.addLast(new ServerDecode())
//                                .addLast(new ServerRequestHandler(dispatcher));
                        pipeline.addLast(new HttpServerCodec())
                                .addLast(new HttpObjectAggregator(1024*512))
                                .addLast(new HttpRequestHandler(dispatcher));
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
    public void startHttpServer(){
        MyCar myCar = new MyCar();
        MyPlane myPlane = new MyPlane();
        Dispatcher dispatcher = Dispatcher.getDispatcher();
        dispatcher.register(Car.class.getName(), myCar);
        dispatcher.register(Plane.class.getName(), myPlane);

        //tomcat jetty  【servlet】
        Server server = new Server(new InetSocketAddress("localhost", 9090));
        ServletContextHandler handler = new ServletContextHandler(server, "/");
        server.setHandler(handler);
        handler.addServlet(MyHttpRPCHandler.class,"/*");  //web.xml

        try {
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void get() {
//        new Thread(() -> {
//            startServer();
//        }).start();
        System.out.println("sever started...");
        int size = 20;
        AtomicInteger nums = new AtomicInteger(0);
        Thread[] threads = new Thread[size];
        for (int i = 0; i < size; i++) {
            threads[i] = new Thread(() -> {
                Car car = MyProxy.proxyGet(Car.class);
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
    @Test
    public void testRPC(){
        Car car = MyProxy.proxyGet(Car.class);
        Person tequila = car.getOff("Tequila",28);
        System.out.println(tequila);
    }

}
