package org.whisky.observer_pattern;

/**
 * @ClassName DrawHouseService
 * @Description TODO
 * @Author GT-R
 * @Date 2024/7/19上午11:12
 * @Version 1.0
 */
public class DrawHouseService {
    //摇号抽签
    public String lots(String uId){
        if(uId.hashCode() % 2 == 0){
            return "恭喜ID为: " + uId + " 的用户,在本次摇号中中签! !";
        }else{
            return "很遗憾,ID为: " + uId + "的用户,您本次未中签! !";
        }
    }
}
