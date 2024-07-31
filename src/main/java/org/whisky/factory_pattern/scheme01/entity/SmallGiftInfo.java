package org.whisky.factory_pattern.scheme01.entity;

/**
 * @ClassName SmallGiftInfo
 * @Description 小礼品实体类
 * @Author GT-R
 * @Date 2024/5/30下午1:19
 * @Version 1.0
 */
public class SmallGiftInfo {
    private String userName;
    private String userPhone;
    private String orderId;
    private String address;

    public SmallGiftInfo() {
    }

    @Override
    public String toString() {
        return "SmallGiftInfo{" +
                "userName='" + userName + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", orderId='" + orderId + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
