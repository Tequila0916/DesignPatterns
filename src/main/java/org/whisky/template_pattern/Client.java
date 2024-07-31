package org.whisky.template_pattern;

/**
 * @ClassName Client
 * @Description TODO
 * @Author GT-R
 * @Date 2024/7/22下午7:26
 * @Version 1.0
 */
public class Client {
    public static void main(String[] args) {

        Account a1 = new LoanSevenDays();
        a1.handle("tom","123456");

        System.out.println("==========================");

        Account a2 = new LoanOneMonth();
        a2.handle("tom","123456");
    }
}
