package com.yupi.yurpc.proxy;

import java.lang.reflect.Proxy;

public class MockServiceProxyFactory {

    /**
     * 根据服务类获取 Mock 代取对象
     * @param serviceClass
     * @return
     * @param <T>
     */
    public static <T> T getMockProxy(Class<T> serviceClass) {
        return (T) Proxy.newProxyInstance(serviceClass.getClassLoader(),
                new Class[]{serviceClass},
                new MockServiceProxy());
    }
}
