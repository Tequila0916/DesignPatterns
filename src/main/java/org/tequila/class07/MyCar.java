package org.tequila.class07;

/**
 * @ClassName MyCar
 * @Description TODO
 * @Author GT-R
 * @Date 2023/8/619:44
 * @Version 1.0
 */
public class MyCar implements Car{
    @Override
    public String drive(String msg) {
        System.out.println("server,get client arg:" + msg);
        return "server res" + msg;
    }
}
