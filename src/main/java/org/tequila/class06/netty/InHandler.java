package org.tequila.class06.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.StandardCharsets;

/**
 * @ClassName MyInHandler
 * @Description TODO
 * @Author GT-R
 * @Date 2023/8/308:31
 * @Version 1.0
 */
public class InHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("client registered");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("client active");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
//        CharSequence str = buf.readCharSequence(buf.readableBytes(), StandardCharsets.UTF_8);
        CharSequence str = buf.getCharSequence(0, buf.readableBytes(), StandardCharsets.UTF_8);
        System.out.println(str);
        ctx.writeAndFlush(buf);
    }
}
