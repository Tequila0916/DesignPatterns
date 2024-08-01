package org.whisky.responsibility_pattern.example02;

import org.whisky.responsibility_pattern.example01.AuthInfo;
import org.whisky.responsibility_pattern.example01.AuthService;

import java.text.ParseException;
import java.util.Date;

/**
 * @ClassName Level1AuthLink
 * @Description TODO
 * @Author GT-R
 * @Date 2024/8/1上午9:59
 * @Version 1.0
 */
public class Level2AuthLink extends  AuthLink{
    private Date beginDate = dateFormat.parse("2020-11-11 00:00:00");
    private Date endDate = dateFormat.parse("2020-11-31 23:59:59");
    public Level2AuthLink(String levelUserId, String levelUserName) throws ParseException {
        super(levelUserId, levelUserName);
    }
    @Override
    public AuthInfo doAuth(String uId, String orderId, Date authDate) {
        Date date = AuthService.queryAuthInfo(levelUserId, orderId);
        if (null == date) {
            return new AuthInfo("0001", "单号：", orderId, " 状态：待二级审批负责人 ", levelUserName);
        }
        AuthLink next = super.getNextLink();
        if (null == next) {
            return new AuthInfo("0001", "单号：", orderId, " 状态：二级审批完成", " 时间：", dateFormat.format(date), " 审批人：", levelUserName);
        }
        if (authDate.before(beginDate) || authDate.after(endDate)) {
            return new AuthInfo("0001", "单号：", orderId, " 状态：二级审批完成", " 时间：", dateFormat.format(date), " 审批人：", levelUserName);
        }
        return next.doAuth(uId, orderId, authDate);
    }
}
