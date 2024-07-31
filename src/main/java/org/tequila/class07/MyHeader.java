package org.tequila.class07;

import java.io.Serializable;

/**
 * @ClassName MyHeader
 * @Description 通信协议
 * @Author GT-R
 * @Date 2023/8/316:53
 * @Version 1.0
 */
public class MyHeader implements Serializable {
    private int flag;
    private long requestId;
    private long dataLength;

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }

    public long getDataLength() {
        return dataLength;
    }

    public void setDataLength(long dataLength) {
        this.dataLength = dataLength;
    }
}
