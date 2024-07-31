package org.tequila.class08.rpc.protocol;

import java.io.Serializable;
import java.util.UUID;

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

    public static MyHeader createHeader(byte[] msgBody) {
        MyHeader header = new MyHeader();
        int flag = 0x14141414;
        long requestId = Math.abs(UUID.randomUUID().getLeastSignificantBits());
        int dataLength = msgBody.length;
        header.setFlag(flag);
        header.setRequestId(requestId);
        header.setDataLength(dataLength);
        return header;
    }

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
