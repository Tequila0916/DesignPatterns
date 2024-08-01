package org.whisky.responsibility_pattern.example02;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.whisky.responsibility_pattern.example01.AuthInfo;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName AuthLink
 * @Description TODO
 * @Author GT-R
 * @Date 2024/8/1上午8:26
 * @Version 1.0
 */
public abstract class AuthLink {
    protected Logger logger = LoggerFactory.getLogger(AuthLink.class);
    protected SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    protected String levelUserId;
    protected String levelUserName;
    protected AuthLink nextLink;

    public AuthLink(String levelUserId, String levelUserName) {
        this.levelUserId = levelUserId;
        this.levelUserName = levelUserName;
    }

    public AuthLink getNextLink() {
        return nextLink;
    }

    public AuthLink appendNextLink(AuthLink nextLink) {
        this.nextLink = nextLink;
        return this;
    }

    public abstract AuthInfo doAuth(String uId, String orderId, Date authDate);
}
