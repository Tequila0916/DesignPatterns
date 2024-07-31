package org.tequila.class06.netty;

import io.netty.channel.*;

/**
 * @ClassName HandlerInit
 * @Description TODO
 * @Author GT-R
 * @Date 2023/8/310:07
 * @Version 1.0
 */
@ChannelHandler.Sharable
public class InitHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        Channel client = ctx.channel();
        ChannelPipeline pipeline = ctx.pipeline();
        pipeline.addLast(new InHandler());
        pipeline.remove(this);
    }
}
