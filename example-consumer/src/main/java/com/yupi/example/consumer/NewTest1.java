package com.yupi.example.consumer;

import com.yupi.example.common.model.User;
import com.yupi.example.common.service.UserService;
import com.yupi.yurpc.proxy.ServiceProxyFactory;

public class NewTest1 {
    public static void main(String[] args) {
        UserService userService = ServiceProxyFactory.getProxy(UserService.class);
        User user = new User();
        user.setName("liu");
        for (int i = 0; i < 3; i++) {
            System.out.println("第"+ i + "次");
            userService.getUser(user);
        }
    }
}
