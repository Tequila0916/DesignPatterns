package org.whisky.responsibility_pattern.example01;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName AuthController
 * @Description TODO
 * @Author GT-R
 * @Date 2024/7/31下午9:21
 * @Version 1.0
 */
public class AuthController {
    public AuthInfo doAuth(String name, String orderId, Date authDate) throws ParseException {
        Date date = null;
        date = AuthService.queryAuthInfo("1000013",orderId);
        if(date==null){
            return new AuthInfo("0001","单号: "+orderId,"状态: 等待三级审批负责人进行审批");
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(authDate.after(dateFormat.parse("2022-11-01 00:00:00")) && authDate.before(dateFormat.parse("2022-11-11 00:00:00"))){
            date = AuthService.queryAuthInfo("1000012",orderId);
            if(date == null){
                return new AuthInfo("0001","单号: "+orderId,"状态: 等待二级审批负责人进行审批");
            }
        }

        if(authDate.after(dateFormat.parse("2022-11-11 00:00:00")) && authDate.before(dateFormat.parse("2022-11-31 00:00:00"))){
            date = AuthService.queryAuthInfo("1000011",orderId);
            if(date == null){
                return new AuthInfo("0001","单号: "+orderId,"状态: 等待一级审批负责人进行审批");
            }
        }
        return new AuthInfo("0001","单号: "+orderId,"申请人:"+ name +", 状态: 审批完成!");

    }
}
