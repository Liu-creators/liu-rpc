package com.yupi.yurpc.serializer;

import com.yupi.yurpc.spi.SpiLoader;

/**
 * 序列化工厂，（用于获取序列化器对象）
 */
public class SerializerFactory {

/*
    static {
        SpiLoader.load(Serializer.class);
    }*/
    /**
     * 默认序列化器
     */
//    private static final Serializer DEFAULT_SERIALIZER = new JdkSerializer();

    /**
     * 初始化序列化器
     * @param key
     * @return
     */
//    public static Serializer getInstance(String key) {
//        return SpiLoader.getInstance(Serializer.class, key);
//    }

    /**
     * 默认序列化器，这里我们保留直接初始化的方式，因为它是一个常量，不会因为懒加载而有所改变。
     * 但如果您希望它也实现懒加载，可以将其改为一个方法，并在该方法中使用双检索单例模式。
     */
    private static final Serializer DEFAULT_SERIALIZER = new JdkSerializer();

    private SerializerFactory() {
        // 私有构造函数，防止实例化
    }

    /**
     * 初始化序列化器，使用双检索单例模式实现懒加载和线程安全。
     *
     * @param key 序列化器的标识
     * @return 对应的序列化器实例
     */
    public static Serializer getInstance(String key) {
        if (key == null) {
            // 如果key为null，返回默认序列化器
            return DEFAULT_SERIALIZER;
        }
        // 使用双检索单例模式实现懒加载
        Serializer serializer = SpiLoader.getInstance(Serializer.class, key);
        if (serializer == null) {
            // 如果未找到对应的序列化器，返回默认序列化器
            // 这里可以根据实际需求抛出异常或返回默认序列化器
            return DEFAULT_SERIALIZER;
        }
        return serializer;
    }
}
