package org.whisky.factory_pattern.scheme01.service;

/**
 * @ClassName YouKuMemberService
 * @Description TODO
 * @Author GT-R
 * @Date 2024/5/30下午1:24
 * @Version 1.0
 */
public class YouKuMemberService {
    public void openMember(String bindMobile, String number) {
        System.out.println("发放优酷会员: " + bindMobile + " , " + number);
    }
}
