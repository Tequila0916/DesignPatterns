package org.tequila.class08.rpc.transport;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.util.concurrent.EventExecutorGroup;
import org.tequila.class08.rpc.protocol.MyContent;
import org.tequila.class08.util.SerDerUtil;

import java.util.concurrent.CompletableFuture;

/**
 * @ClassName ClientRequestHandler
 * @Description TODO
 * @Author GT-R
 * @Date 2023/8/714:53
 * @Version 1.0
 */
public class ClientRequestHandler extends ChannelInboundHandlerAdapter {
    CompletableFuture res;
    public ClientRequestHandler(CompletableFuture<Object> res) {
       this.res = res;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        FullHttpResponse response = (FullHttpResponse) msg;
        System.out.println(msg.toString());
        ByteBuf byteBuf = response.content();
        byte[] bytes = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(bytes);
        MyContent content = (MyContent) SerDerUtil.deserialize(bytes);
        res.complete(content.getMessage());
    }
}
