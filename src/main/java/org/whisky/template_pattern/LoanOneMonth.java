package org.whisky.template_pattern;

/**
 * @ClassName LoanOneMonth
 * @Description TODO
 * @Author GT-R
 * @Date 2024/7/22下午7:25
 * @Version 1.0
 */
public class LoanOneMonth extends Account {
    @Override
    public void calculate() {
        System.out.println("借款周期30天,利率为10%!");
    }
}
