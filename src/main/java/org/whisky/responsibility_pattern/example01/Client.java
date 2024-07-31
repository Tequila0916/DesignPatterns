package org.whisky.responsibility_pattern.example01;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName Client
 * @Description TODO
 * @Author GT-R
 * @Date 2024/7/31下午9:29
 * @Version 1.0
 */
public class Client {
    public static void main(String[] args) throws ParseException {
        AuthController authController = new AuthController();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse("2022-11-09 00:00:00");
        AuthInfo info1 = authController.doAuth("研发小周", "100001000010000", date);
        System.out.println("当前审核状态:  " + info1.getInfo());
        AuthService.auth("1000013", "100001000010000");
        System.out.println("三级负责人审批完成,审批人: 王工");

        System.out.println("===========================================================================");
        AuthInfo info2 = authController.doAuth("研发小周", "100001000010000", date);
        System.out.println("当前审核状态:  " + info2.getInfo());
        AuthService.auth("1000012", "100001000010000");
        System.out.println("二级负责人审批完成,审批人: 张经理");

        System.out.println("===========================================================================");

        AuthInfo info3 = authController.doAuth("研发小周", "100001000010000", date);
        System.out.println("当前审核状态:  " + info3.getInfo());
        AuthService.auth("1000011", "100001000010000");
        System.out.println("一级负责人审批完成,审批人: 罗总");

    }
}
