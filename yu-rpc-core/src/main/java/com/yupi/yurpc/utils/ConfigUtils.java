package com.yupi.yurpc.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.setting.dialect.Props;
import cn.hutool.setting.yaml.YamlUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

/**
 * 配置工具类（读取配置文件并返回配置对象）
 */
public class ConfigUtils {

    /**
     * 加载配置对象
     * @param tClass
     * @param prefix
     * @return
     * @param <T>
     */
    public static <T> T loadConfig(Class<T> tClass, String prefix) {
        return loadConfig_yaml(tClass, prefix, "");
    }

    /**
     * 加载配置对象，支持区分环境
     * @param tClass
     * @param prefix
     * @param environment
     * @return
     * @param <T>
     */
    public static <T> T loadConfig(Class<T> tClass, String prefix, String environment) {
        StringBuilder configFileBuilder = new StringBuilder("properties/application");
        if (StrUtil.isNotBlank(environment)) {
            configFileBuilder.append("-").append(environment);
        }
        configFileBuilder.append(".properties");
        Props props = new Props(configFileBuilder.toString());
        System.out.println(configFileBuilder.toString());
        return props.toBean(tClass, prefix);
    }

    /**
     * 加载配置对象，支持区分环境，多级前缀，yaml文件
     * @param tClass
     * @param prefix
     * @param environment
     * @return
     * @param <T>
     */
    public static <T> T loadConfig_yaml(Class<T> tClass,String prefix, String environment) {
        ObjectMapper objectMapper = new ObjectMapper(); // 创建 ObjectMapper 实例
        StringBuilder configFileBuilder = new StringBuilder("yaml/application");
        if (StrUtil.isNotBlank(environment)) {
            configFileBuilder.append("-").append(environment);
        }
        configFileBuilder.append(".yaml");
        // YamlUtil.loadByPath 正确加载了 YAML 文件并返回了一个 Map 对象
        Map<String, Object> yamlMap = YamlUtil.loadByPath(configFileBuilder.toString());
        // 现在我们将这个 Map 转换为指定的 Java 类型 T
        // 解析多级前缀
        if (!prefix.isEmpty()) {
            String[] strs = prefix.split("/");
            for (int i = 0; i < strs.length; i++) {
                yamlMap = (Map) yamlMap.get(strs[i]);
            }
        }
        return objectMapper.convertValue(yamlMap, tClass);

    }

}
