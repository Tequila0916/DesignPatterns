package org.tequila.class08.rpc.transport;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import org.tequila.class08.rpc.Dispatcher;
import org.tequila.class08.rpc.protocol.MyContent;
import org.tequila.class08.util.SerDerUtil;

import java.lang.reflect.Method;

/**
 * @ClassName HttpRequestHandler
 * @Description TODO
 * @Author GT-R
 * @Date 2023/8/713:54
 * @Version 1.0
 */
public class HttpRequestHandler extends ChannelInboundHandlerAdapter {
    Dispatcher dispatcher;

    public HttpRequestHandler(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        FullHttpRequest request = (FullHttpRequest) msg;
        System.out.println(request.toString());
        ByteBuf byteBuf = request.content();
        byte[] data = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(data);
        MyContent content = (MyContent) SerDerUtil.deserialize(data);

        String serviceName = content.getClassName();
        String methodName = content.getMethodName();
        Object object = dispatcher.take(serviceName);
        Class<?> clazz = object.getClass();
        Object res = null;
        Method method = clazz.getMethod(methodName, content.getParameterTypes());
        res = method.invoke(object, content.getArgs());

        MyContent resContent = new MyContent();
        resContent.setMessage(res);
        byte[] serialize = SerDerUtil.serialize(resContent);

        DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_0, HttpResponseStatus.OK, Unpooled.copiedBuffer(serialize));
        response.headers().set(HttpHeaderNames.CONTENT_LENGTH,serialize.length);
        ctx.writeAndFlush(response);

    }
}
