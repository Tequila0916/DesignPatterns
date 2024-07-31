package org.tequila.class07;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

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
//        ByteBuf buf = (ByteBuf) msg;
//        if (buf.readableBytes() >= 101) {
//            byte[] bytes = new byte[101];
//            buf.readBytes(bytes);
//            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
//            ObjectInputStream ois = new ObjectInputStream(bis);
//            MyHeader header = (MyHeader) ois.readObject();
//            long dataLength = header.getDataLength();
////            System.out.println("Response ... Header.dataLength = " + dataLength);
//            long requestId = header.getRequestId();
////            System.out.println("Response ... Header.requestId = " + requestId);
//            ResponseMappingCallback.runCallBack(requestId);
//        }
        PackageMessage message = (PackageMessage) msg;
        ResponseMappingCallback.runCallBack(message);
    }
}
