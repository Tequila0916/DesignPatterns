package org.whisky.factory_pattern.scheme01.entity;

import java.util.Map;

/**
 * @ClassName AwardInfo
 * @Description TODO
 * @Author GT-R
 * @Date 2024/5/30下午1:14
 * @Version 1.0
 */
public class AwardInfo {
    private String uid; // 用户id
    private Integer awardType; // 奖品类型：1.打折券 2.优酷会员 3.小礼品
    private String awardNumber; // 奖品编号
    private Map<String, String> extMap; // 额外信息

    public AwardInfo() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Integer getAwardType() {
        return awardType;
    }

    public void setAwardType(Integer awardType) {
        this.awardType = awardType;
    }

    public String getAwardNumber() {
        return awardNumber;
    }

    public void setAwardNumber(String awardNumber) {
        this.awardNumber = awardNumber;
    }

    public Map<String, String> getExtMap() {
        return extMap;
    }

    public void setExtMap(Map<String, String> extMap) {
        this.extMap = extMap;
    }

    @Override
    public String toString() {
        return "AwardInfo{" +
                "uid='" + uid + '\'' +
                ", awardType=" + awardType +
                ", awardName='" + awardNumber + '\'' +
                ", extMap=" + extMap +
                '}';
    }
}
