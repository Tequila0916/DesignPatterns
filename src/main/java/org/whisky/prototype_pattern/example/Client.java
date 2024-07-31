package org.whisky.prototype_pattern.example;

import java.util.Random;

/**
 * @ClassName Client
 * @Description TODO
 * @Author GT-R
 * @Date 2024/6/27上午11:14
 * @Version 1.0
 */
public class Client {
    //发送信息的是数量,这个值可以从数据库获取
    private static int MAX_COUNT = 6;

    //发送邮件
    public static void sendMail(Mail mail) {
        System.out.println("标题: " + mail.getSubject() + "\t收件人: " + mail.getReceiver()
                + "\t..发送成功!");
    }

    public static void main(String[] args) {

        //模拟邮件发送
        int i = 0;

        //把模板定义出来,数据是从数据库获取的
        Mail mail = new Mail(new AdvTemplate());
        mail.setTail("xxx银行版权所有");
        while (i < MAX_COUNT) {
            //下面是每封邮件不同的地方
            Mail cloneMail = mail.clone();
            cloneMail.setAppellation(" 先生 (女士)");
            Random random = new Random();
            int num = random.nextInt(9999999);
            cloneMail.setReceiver(num + "@" + "liuliuqiu.com");
            //发送 邮件
            sendMail(cloneMail);
            i++;
        }
    }
}
