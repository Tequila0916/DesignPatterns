package org.tequila.class08.util;


import org.tequila.class08.rpc.protocol.MyContent;
import org.tequila.class08.rpc.protocol.MyHeader;

/**
 * @ClassName PackageMessage
 * @Description TODO
 * @Author GT-R
 * @Date 2023/8/616:09
 * @Version 1.0
 */
public class PackageMessage {
    private MyHeader header;
    private MyContent content;
    public PackageMessage(MyHeader header, MyContent content) {
        this.header=header;
        this.content = content;
    }

    public MyHeader getHeader() {
        return header;
    }

    public void setHeader(MyHeader header) {
        this.header = header;
    }

    public MyContent getContent() {
        return content;
    }

    public void setContent(MyContent content) {
        this.content = content;
    }
}
