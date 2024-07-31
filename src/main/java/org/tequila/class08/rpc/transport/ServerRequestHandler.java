package org.tequila.class08.rpc.transport;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.tequila.class08.util.PackageMessage;
import org.tequila.class08.util.SerDerUtil;
import org.tequila.class08.rpc.Dispatcher;
import org.tequila.class08.rpc.protocol.MyContent;
import org.tequila.class08.rpc.protocol.MyHeader;

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
        PackageMessage requestMessage = (PackageMessage) msg;
        String ioThreadName = Thread.currentThread().getName();
//        ctx.executor().execute(new Runnable() {
        ctx.executor().parent().next().execute(new Runnable() {
            @Override
            public void run() {

                String serviceName = requestMessage.getContent().getClassName();
                String methodName = requestMessage.getContent().getMethodName();
                Object object = dispatcher.take(serviceName);
                Class<?> clazz = object.getClass();
                Object res = null;
                try {
                    Method method = clazz.getMethod(methodName, requestMessage.getContent().getParameterTypes());
                    res = method.invoke(object,requestMessage.getContent().getArgs());

                } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }

//                String execThreadName = Thread.currentThread().getName();
                MyContent content = new MyContent();
//                String message = "ioThread = " + ioThreadName + " execThread = " + execThreadName + " from args = " + requestMessage.getContent().getArgs()[0];
//                System.out.println(message);
//                content.setMessage(message);
                content.setMessage(res);
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
