package org.tequila.class06.netty;

import io.netty.channel.*;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @ClassName AcceptHandler
 * @Description TODO
 * @Author GT-R
 * @Date 2023/8/309:04
 * @Version 1.0
 */
public class AcceptHandler extends ChannelInboundHandlerAdapter {
    EventLoopGroup thread;
    ChannelHandler handler;

    public AcceptHandler(EventLoopGroup thread, ChannelHandler handler) {
        this.thread = thread;
        this.handler = handler;
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("server registered");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("server active");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        NioSocketChannel client = (NioSocketChannel) msg;
        ChannelPipeline service = client.pipeline();
        service.addLast(handler);
        thread.register(client);

    }
}
