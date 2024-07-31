package org.tequila.class08.rpc.transport;

import org.tequila.class08.rpc.Dispatcher;
import org.tequila.class08.rpc.protocol.MyContent;
import org.tequila.class08.util.SerDerUtil;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @ClassName MyHttpRPCHandler
 * @Description TODO
 * @Author GT-R
 * @Date 2023/8/715:28
 * @Version 1.0
 */
public class  MyHttpRPCHandler extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletInputStream sis = req.getInputStream();
        ObjectInputStream ois = new ObjectInputStream(sis);
        try {
            MyContent content = (MyContent) ois.readObject();

            String serviceName = content.getClassName();
            String methodName = content.getMethodName();
            Object object = Dispatcher.getDispatcher().take(serviceName);
            Class<?> clazz = object.getClass();
            Object res = null;
            Method method = clazz.getMethod(methodName, content.getParameterTypes());
            res = method.invoke(object, content.getArgs());
            MyContent resContent = new MyContent();
            resContent.setMessage(res);
//            byte[] serialize = SerDerUtil.serialize(resContent);

            ServletOutputStream sos = resp.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(sos);
            oos.writeObject(resContent);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
