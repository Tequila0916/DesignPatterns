package org.whisky.bridge_pattern;

/**
 * @ClassName PayCypher
 * @Description TODO
 * @Author GT-R
 * @Date 2024/6/29下午1:52
 * @Version 1.0
 */
public class PayCypher implements PayMode{
    @Override
    public boolean security(String uId) {
        System.out.println("密码支付,风控校验-环境安全");
        return false;
    }
}
