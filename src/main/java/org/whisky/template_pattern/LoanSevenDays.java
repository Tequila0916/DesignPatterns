package org.whisky.template_pattern;

/**
 * @ClassName LoanSevenDays
 * @Description TODO
 * @Author GT-R
 * @Date 2024/7/22下午7:25
 * @Version 1.0
 */
public class LoanSevenDays extends Account {
    @Override
    public void calculate() {
        System.out.println("借款周期7天,无利息!仅收取贷款金额1%的服务费!");
    }
    @Override
    public void display(){
        System.out.println("七日内借款无利息!");
    }
}
