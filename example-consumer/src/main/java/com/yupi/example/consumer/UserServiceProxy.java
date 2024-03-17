package com.yupi.example.consumer;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.yupi.example.common.model.User;
import com.yupi.example.common.service.UserService;
import com.yupi.yurpc.RpcApplication;
import com.yupi.yurpc.model.RpcRequest;
import com.yupi.yurpc.model.RpcResponse;
import com.yupi.yurpc.serializer.JdkSerializer;
import com.yupi.yurpc.serializer.Serializer;
import com.yupi.yurpc.serializer.SerializerFactory;

import java.io.IOException;
import java.util.ServiceLoader;

/**
 * 静态代理
 */
public class UserServiceProxy implements UserService {
    @Override
    public User getUser(User user) {
        // 指定序列化器
        Serializer serializer = SerializerFactory.getInstance(RpcApplication.getRpcConfig().getSerializer());


        // 发请求
        RpcRequest rpcRequest = RpcRequest.builder()
                .serviceName(UserService.class.getName())
                .methodName("getUser")
                .parameterTypes(new Class[]{User.class})
                .args(new Object[]{user})
                .build();
        try {
            byte[] bodyBytes = serializer.serialize(rpcRequest);
            byte[] result;
            try (HttpResponse httpResponse =
                         HttpRequest.post("http://localhost:8080") // 发送请求
                                 .body(bodyBytes) // 设置请求体
                                 .execute()) { // 执行并接收响应
                result = httpResponse.bodyBytes(); // 获取响应体
            }
            RpcResponse rpcResponse = serializer.deserialize(result,RpcResponse.class);
            return (User) rpcResponse.getData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
