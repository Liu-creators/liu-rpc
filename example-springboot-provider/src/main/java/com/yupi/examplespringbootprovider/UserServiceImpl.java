package com.yupi.examplespringbootprovider;

import com.yupi.example.common.model.User;
import com.yupi.example.common.service.UserService;
import com.yupi.yurpc.springboot.starter.annotation.RpcService;
import org.springframework.stereotype.Service;

/**
 * 用户服务实现类
 */
@Service
@RpcService
public class UserServiceImpl implements UserService {
    @Override
    public User getUser(User user) {
        System.out.println("用户名：" + user.getName());
        user.setName("处理后："+user.getName());
        return user;
    }

    @Override
    public Integer addTowNum(Integer x, Integer y) {
        if (x != null && y != null) {
            return x + y;
        } else {
            System.out.println("参数有误！");
            return null;
        }
    }
}
