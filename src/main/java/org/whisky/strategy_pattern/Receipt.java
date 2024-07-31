package org.whisky.strategy_pattern;

/**
 * @ClassName Receipt
 * @Description TODO
 * @Author GT-R
 * @Date 2024/7/23下午4:38
 * @Version 1.0
 */
public class Receipt {
    private String type;
    private String message;

    public Receipt() {
    }

    public Receipt(String message, String type) {
        this.type = type;
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
