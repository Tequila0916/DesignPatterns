package org.whisky.observer_pattern;

import java.util.Date;

/**
 * @ClassName LotteryServiceImpl
 * @Description TODO
 * @Author GT-R
 * @Date 2024/7/19上午11:11
 * @Version 1.0
 */
public class LotteryServiceImpl extends LotteryService{
    private DrawHouseService service = new DrawHouseService();
    @Override
    public LotteryResult lottery(String uId) {
        String msg = service.lots(uId);
        return new LotteryResult(uId,msg,new Date());
    }
}
