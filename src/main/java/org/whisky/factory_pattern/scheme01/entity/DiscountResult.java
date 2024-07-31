package org.whisky.factory_pattern.scheme01.entity;

/**
 * @ClassName DisCountResult
 * @Description TODO
 * @Author GT-R
 * @Date 2024/5/30下午1:21
 * @Version 1.0
 */
public class DiscountResult {
    private String status;
    private String message;

    public DiscountResult(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ResponseResult{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
