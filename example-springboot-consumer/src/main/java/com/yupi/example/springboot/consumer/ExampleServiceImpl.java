package com.yupi.example.springboot.consumer;

import com.yupi.example.common.model.User;
import com.yupi.example.common.service.UserService;
import com.yupi.yurpc.springboot.starter.annotation.RpcReference;
import org.springframework.stereotype.Service;

@Service
public class ExampleServiceImpl {
    @RpcReference
    private UserService userService;

    public void test(){
        User user = new User();
        user.setName("liu");
        User resultUser = userService.getUser(user);
        System.out.println(resultUser.getName());
    }
}