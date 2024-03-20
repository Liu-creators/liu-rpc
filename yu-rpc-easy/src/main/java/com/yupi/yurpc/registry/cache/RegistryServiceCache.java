package com.yupi.yurpc.registry.cache;

import com.yupi.yurpc.model.ServiceMetaInfo;

import java.util.List;

/**
 * 注册中心服务本地缓存
 */
public class RegistryServiceCache {
    /**
     *  服务缓存
     */
    List<ServiceMetaInfo> serviceCache;

    /**
     * 写缓存
     * @param newServiceCache
     */
    public void writeCache(List<ServiceMetaInfo> newServiceCache) {
        this.serviceCache = newServiceCache;
    }

    /**
     * 读缓存
     * @return
     */
    public List<ServiceMetaInfo> readCache(){
        return this.serviceCache;
    }

    /**
     * 清空缓存
     */
    public void clearCache() {
        this.serviceCache = null;
    }
}
