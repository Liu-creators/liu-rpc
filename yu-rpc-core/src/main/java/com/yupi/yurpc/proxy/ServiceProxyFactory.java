package com.yupi.yurpc.proxy;

import com.yupi.yurpc.RpcApplication;

import java.lang.reflect.Proxy;

import static com.yupi.yurpc.proxy.MockServiceProxyFactory.getMockProxy;

/**
 * 服务代理工厂（用于创建代理对象）
 */
public class ServiceProxyFactory {
    /**
     * 根据服务类获取代理对象
     * @param serviceClass
     * @return
     * @param <T>
     */
    public static <T> T getProxy(Class<T> serviceClass) {
        if (RpcApplication.getRpcConfig().isMock()) {
            return getMockProxy(serviceClass);
        }
        return (T) Proxy.newProxyInstance(serviceClass.getClassLoader(),
                new Class[]{serviceClass},
                new ServiceProxy());
    }

}
