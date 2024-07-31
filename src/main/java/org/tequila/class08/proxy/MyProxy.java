package org.tequila.class08.proxy;

import org.tequila.class08.rpc.Dispatcher;
import org.tequila.class08.rpc.protocol.MyContent;
import org.tequila.class08.rpc.transport.ClientFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.concurrent.CompletableFuture;

/**
 * @ClassName MyProxy
 * @Description TODO
 * @Author GT-R
 * @Date 2023/8/708:17
 * @Version 1.0
 */
public class MyProxy {
    public static <T> T proxyGet(Class<T> interfaceInfo) {
        ClassLoader classLoader = interfaceInfo.getClassLoader();
        Class<?>[] methodInfo = {interfaceInfo};
        Dispatcher dispatcher = Dispatcher.getDispatcher();
        return (T) Proxy.newProxyInstance(classLoader, methodInfo, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Object object = dispatcher.take(interfaceInfo.getName());
                Object res = null;
                if (object == null) {
                    // 1.调用的服务，方法，参数 --> 封装成message
                    String className = interfaceInfo.getName();
                    String methodName = method.getName();
                    Class<?>[] parameterTypes = method.getParameterTypes();

                    MyContent content = new MyContent();
                    content.setClassName(className);
                    content.setMethodName(methodName);
                    content.setParameterTypes(parameterTypes);
                    content.setArgs(args);

                    CompletableFuture future = ClientFactory.transport(content);
                    // 5.如果从IO返回来了，如何（睡眠/回调/让线程停下来）等待然后继续执行
                    res = future.get();
                } else {
                    System.out.println("local FC....");
                    Class<?> clazz = object.getClass();
                    Method clazzMethod = clazz.getMethod(method.getName(), method.getParameterTypes());
                    res = clazzMethod.invoke(object, args);
                }
                return res;
            }
        });
    }
}
