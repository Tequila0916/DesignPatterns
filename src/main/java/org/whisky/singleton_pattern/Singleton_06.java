package org.whisky.singleton_pattern;

/**
 * @ClassName Singletion_06
 * @Description TODO
 * @Author GT-R
 * @Date 2024/5/29下午9:36
 * @Version 1.0
 */
public enum Singleton_06 {
    INSTANCE;
    private Object data;

    public static Singleton_06 getInstance() {
        return INSTANCE;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
