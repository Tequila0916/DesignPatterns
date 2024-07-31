package org.whisky.proxy_pattern.example03;

import java.util.Collections;
import java.util.List;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author GT-R
 * @Date 2024/6/28上午10:30
 * @Version 1.0
 */
public class UserServiceImpl {
    public List<User> findUserList() {
        return Collections.singletonList(new User("tom", 18));
    }
}
