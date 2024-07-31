package org.whisky.bridge_pattern;

/**
 * @ClassName PayFaceMode
 * @Description TODO
 * @Author GT-R
 * @Date 2024/6/29下午1:51
 * @Version 1.0
 */
public class PayFaceMode implements PayMode{
    @Override
    public boolean security(String uId) {
        System.out.println("人脸支付,风控校验-脸部识别");
        return true;
    }
}
