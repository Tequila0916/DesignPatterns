package org.whisky.bridge_pattern;

/**
 * @ClassName PayFingerPrintMode
 * @Description TODO
 * @Author GT-R
 * @Date 2024/6/29下午1:51
 * @Version 1.0
 */
public class PayFingerPrintMode implements PayMode{
    @Override
    public boolean security(String uId) {
        System.out.println("指纹支付,风控校验-指纹信息");
        return true;
    }
}
