package com.yupi.yurpc.registry;

import com.yupi.yurpc.spi.SpiLoader;

/**
 * 注册中心工厂（用语获取注册中心对象）
 */
public class RegistryFactory {
    static {
        SpiLoader.load(Registry.class);
    }

    /**
     * 默认注册中心
     */
    private static final Registry DEGAULT_REGISTRY = new EtcdRegistry();

    /**
     * 根据key（etcd）获取实例
     * @param key
     * @return
     */
    public static Registry getInstance(String key) {
        return SpiLoader.getInstance(Registry.class, key);
    }
}
