package org.tequila.class08.rpc.protocol;

import java.io.Serializable;

/**
 * @ClassName MyContent
 * @Description 调用的服务，方法，参数 --> 封装成message
 * @Author GT-R
 * @Date 2023/8/316:37
 * @Version 1.0
 */
public class MyContent implements Serializable {
    private String className;
    private String methodName;
    private Class<?>[] parameterTypes;
    private Object[] args;
    private Object message;

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(Class<?>[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }
}
