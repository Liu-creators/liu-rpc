package com.yupi.example.provider;

import com.yupi.example.common.service.UserService;
import com.yupi.yurpc.bootstrap.ProviderBootstrap;
import com.yupi.yurpc.model.ServiceMetaInfo;
import com.yupi.yurpc.model.ServiceRegisterInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 服务提供者示例
 */
public class ProviderExample_now {

    public static void main(String[] args) {
        // 要注册的服务
        List<ServiceRegisterInfo> serviceRegisterInfoList = new ArrayList<>();
        ServiceRegisterInfo serviceRegisterInfo = new ServiceRegisterInfo(UserService.class.getName(), UserServiceImpl.class);
        serviceRegisterInfoList.add(serviceRegisterInfo);
        // 服务提供者初始化
        ProviderBootstrap.init(serviceRegisterInfoList);
    }
}
