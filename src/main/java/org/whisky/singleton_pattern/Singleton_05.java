package org.whisky.singleton_pattern;

/**
 * @ClassName Singleton_05
 * @Description TODO
 * @Author GT-R
 * @Date 2024/5/29下午8:57
 * @Version 1.0
 */
public class Singleton_05 {
    private Singleton_05(){
        if (Singleton_Holder.INSTANCE!=null){
            throw new RuntimeException("不允许非法访问");
        }
    }
    private static class Singleton_Holder{
        private static  Singleton_05 INSTANCE = new Singleton_05();
    }
    public static Singleton_05 getInstance(){
        return Singleton_Holder.INSTANCE;
    }
}
