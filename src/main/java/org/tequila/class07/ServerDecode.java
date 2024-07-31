package org.tequila.class07;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

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
//        System.out.println("channel ... start = " + byteBuf.readableBytes());
        while (byteBuf.readableBytes() >= 101) {
            byte[] bytes = new byte[101];
//            byteBuf.readBytes(bytes);
            byteBuf.getBytes(byteBuf.readerIndex(), bytes);
            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bis);
            MyHeader header = (MyHeader) ois.readObject();
            long dataLength = header.getDataLength();
//            System.out.println("Decode ... Header.dataLength = " + dataLength);
            long requestId = header.getRequestId();
//            System.out.println("Decode ... Header.requestId = " + requestId);
            if (byteBuf.readableBytes() >= dataLength + 101) {
                byteBuf.readBytes(101);
                byte[] data = new byte[(int) dataLength];
                byteBuf.readBytes(data);
                bis = new ByteArrayInputStream(data);
                ois = new ObjectInputStream(bis);
                if(header.getFlag()==0x14141414){
                    MyContent content = (MyContent) ois.readObject();
//                System.out.println("Content.className" + content.getClassName());
                    list.add(new PackageMessage(header,content));
                }else if(header.getFlag()==0x14141415){
                    MyContent content = (MyContent) ois.readObject();
//                System.out.println("Content.className" + content.getClassName());
                    list.add(new PackageMessage(header,content));
                }
            } else {
                break;
            }
        }
    }
}
