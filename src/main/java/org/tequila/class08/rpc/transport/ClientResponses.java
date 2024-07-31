package org.tequila.class08.rpc.transport;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.tequila.class08.util.PackageMessage;
import org.tequila.class08.rpc.ResponseMappingCallback;

/**
 * @ClassName ClientResponses
 * @Description TODO
 * @Author GT-R
 * @Date 2023/8/317:47
 * @Version 1.0
 */
public class ClientResponses extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        PackageMessage message = (PackageMessage) msg;
        ResponseMappingCallback.runCallBack(message);
    }
}
