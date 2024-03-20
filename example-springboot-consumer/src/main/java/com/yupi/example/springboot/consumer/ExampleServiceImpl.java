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
        user.setAge(18);
        for (int i = 1; i <= 3; i++) {
            User resultUser = userService.getUser(user);
            System.out.println("第 " + i +" 次 "+resultUser);
        }
        System.out.println(userService.addTowNum(1,2));
    }

    public void test0(){
        User user = new User();
        user.setName("liu");
        user.setAge(18);
        User resultUser = userService.getUser(user);
        System.out.println(resultUser);
        System.out.println(userService.addTowNum(1,2));
    }

    public void test1(){
        User user = new User();
        user.setName("yupi");
        User newUser = userService.getUser(user);
        if (newUser != null) {
            System.out.println(newUser.getName());
        } else {
            System.out.println("newUser == null");
        }
        long number = userService.getNumber();
        System.out.println(number);
    }

}
