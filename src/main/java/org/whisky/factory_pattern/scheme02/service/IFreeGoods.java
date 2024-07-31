package org.whisky.factory_pattern.scheme02.service;

import org.whisky.factory_pattern.scheme02.entity.AwardInfo;
import org.whisky.factory_pattern.scheme02.entity.ResponseResult;

/**
 * @ClassName IFreeGoods
 * @Description TODO
 * @Author GT-R
 * @Date 2024/6/4上午8:07
 * @Version 1.0
 */
public interface IFreeGoods {
    ResponseResult sendFreeGoods(AwardInfo awardInfo);
}
