package org.tequila.class07;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @ClassName ServerRequestHandler
 * @Description TODO
 * @Author GT-R
 * @Date 2023/8/320:35
 * @Version 1.0
 */
public class ServerRequestHandler extends ChannelInboundHandlerAdapter {
    Dispatcher dispatcher;

    public ServerRequestHandler(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        ByteBuf buf = (ByteBuf) msg;
//        ByteBuf copy = buf.copy();
//        System.out.println("channel ... start = " + buf.readableBytes());
//        while (buf.readableBytes() >= 101) {
//            byte[] bytes = new byte[101];
////            buf.readBytes(bytes);
//            buf.getBytes(buf.readerIndex(),bytes);
//            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
//            ObjectInputStream ois = new ObjectInputStream(bis);
//            MyHeader header = (MyHeader) ois.readObject();
//            long dataLength = header.getDataLength();
//            System.out.println("Request ... Header.dataLength = " + dataLength);
//            long requestId = header.getRequestId();
//            System.out.println("Request ... Header.requestId = " + requestId);
//            if (buf.readableBytes() >= header.getDataLength()) {
//                buf.readBytes(101);
//                byte[] data = new byte[(int) dataLength];
//                buf.readBytes(data);
//                bis = new ByteArrayInputStream(data);
//                ois = new ObjectInputStream(bis);
//                MyContent content = (MyContent) ois.readObject();
//                System.out.println("Content.className" + content.getClassName());
//
//            } else {
//                System.out.println("channel ... end = " + buf.readableBytes());
//                break;
//            }
//        }
//        ChannelFuture future = ctx.writeAndFlush(copy);
//        future.sync();
        PackageMessage requestMessage = (PackageMessage) msg;
//        System.out.println("Request ... Content.methodName = "+ message.content.methodName);
//        System.out.println("Request ... Content.args[0] = " + requestMessage.getContent().getArgs()[0]);

        String ioThreadName = Thread.currentThread().getName();

//        ctx.executor().execute(new Runnable() {
        ctx.executor().parent().next().execute(new Runnable() {
            @Override
            public void run() {

                String serviceName = requestMessage.getContent().getClassName();
                String methodName = requestMessage.getContent().getMethodName();
                Object object = dispatcher.take(serviceName);
                Class<?> aClazz = object.getClass();
                Object res = null;
                try {
                    Method method = aClazz.getMethod(methodName, requestMessage.getContent().getParameterTypes());
                    res = method.invoke(object,requestMessage.getContent().getArgs());

                } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }

//                String execThreadName = Thread.currentThread().getName();
                MyContent content = new MyContent();
//                String message = "ioThread = " + ioThreadName + " execThread = " + execThreadName + " from args = " + requestMessage.getContent().getArgs()[0];
//                System.out.println(message);
//                content.setMessage(message);
                content.setMessage((String) res);
                byte[] contentByte = SerDerUtil.serialize(content);
                MyHeader header = new MyHeader();
                header.setRequestId(requestMessage.getHeader().getRequestId());
                header.setFlag(0x14141415);
                header.setDataLength(contentByte.length);
                byte[] headByte = SerDerUtil.serialize(header);
                ByteBuf byteBuf = PooledByteBufAllocator.DEFAULT.directBuffer(contentByte.length + headByte.length);
                byteBuf.writeBytes(headByte);
                byteBuf.writeBytes(contentByte);
                ctx.writeAndFlush(byteBuf);
            }
        });


    }
}
