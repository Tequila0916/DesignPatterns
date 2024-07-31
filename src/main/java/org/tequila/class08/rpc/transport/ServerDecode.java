package org.tequila.class08.rpc.transport;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.tequila.class08.util.PackageMessage;
import org.tequila.class08.rpc.protocol.MyContent;
import org.tequila.class08.rpc.protocol.MyHeader;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.List;

/**
 * @ClassName ServerDecode
 * @Description TODO
 * @Author GT-R
 * @Date 2023/8/610:13
 * @Version 1.0
 */
public class ServerDecode extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        while (byteBuf.readableBytes() >= 114) {
            byte[] bytes = new byte[114];
            byteBuf.getBytes(byteBuf.readerIndex(), bytes);
            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bis);
            MyHeader header = (MyHeader) ois.readObject();
            long dataLength = header.getDataLength();
            long requestId = header.getRequestId();
            if (byteBuf.readableBytes() >= dataLength + 114) {
                byteBuf.readBytes(114);
                byte[] data = new byte[(int) dataLength];
                byteBuf.readBytes(data);
                bis = new ByteArrayInputStream(data);
                ois = new ObjectInputStream(bis);
                if (header.getFlag() == 0x14141414) {
                    MyContent content = (MyContent) ois.readObject();
                    list.add(new PackageMessage(header, content));
                } else if (header.getFlag() == 0x14141415) {
                    MyContent content = (MyContent) ois.readObject();
                    list.add(new PackageMessage(header, content));
                }
            } else {
                break;
            }
        }
    }
}
