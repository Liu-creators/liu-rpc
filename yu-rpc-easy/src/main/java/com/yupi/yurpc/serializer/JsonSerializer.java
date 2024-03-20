package com.yupi.yurpc.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yupi.yurpc.model.RpcRequest;
import com.yupi.yurpc.model.RpcResponse;

import java.io.IOException;

public class JsonSerializer implements Serializer{

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public <T> byte[] serialize(T object) throws IOException {
        return OBJECT_MAPPER.writeValueAsBytes(object);
    }

    @Override
    public <T> T deserialize(byte[] bytes, Class<T> type) throws IOException {
        T obj = OBJECT_MAPPER.readValue(bytes, type);
        if (obj instanceof RpcRequest) {
            return handleRequest((RpcRequest) obj, type);
        }
        if (obj instanceof RpcResponse) {
            return handleResponse((RpcResponse) obj, type);
        }
        return obj;
    }
    /**
     * 由于 Objcet 的原因对象会被擦除，导致反序列化时会被作为 LinkedHashMap 无法转
     * 换成原始对象，因此这里做了特殊处理
     * @param rpcResponse
     * @param type
     * @return
     * @param <T>
     */
    private <T> T handleResponse(RpcResponse rpcResponse, Class<T> type) throws IOException {
        // 处理响应数据
        byte[] dataBytes = OBJECT_MAPPER.writeValueAsBytes(rpcResponse.getData());
        rpcResponse.setData(OBJECT_MAPPER.readValue(dataBytes, rpcResponse.getDataType()));
        return type.cast(rpcResponse);
    }

    /**
     * 由于 Objcet 的原因对象会被擦除，导致反序列化时会被作为 LinkedHashMap 无法转
     * 换成原始对象，因此这里做了特殊处理
     * @param rpcRequest
     * @param type
     * @return
     * @param <T>
     */
    private <T> T handleRequest(RpcRequest rpcRequest, Class<T> type) throws IOException {
        Class<?>[] parameterTypes = rpcRequest.getParameterTypes();
        Object[] args = rpcRequest.getArgs();

        // 循环处理每个参数的类型
        for (int i = 0; i < parameterTypes.length; i++) {
            Class<?> parameterType = parameterTypes[i];
            // 如果类型不同，则重新处理一下类型
            if (!parameterType.isAssignableFrom(args[i].getClass())) {
                // 序列化为字节数组
                byte[] argBytes = OBJECT_MAPPER.writeValueAsBytes(args[i]);
                // 反序列化为结果类型
                args[i] = OBJECT_MAPPER.readValue(argBytes,parameterType);
            }
        }
        return type.cast(rpcRequest); // 将rpcRequest强制转化为type类型
    }
}
