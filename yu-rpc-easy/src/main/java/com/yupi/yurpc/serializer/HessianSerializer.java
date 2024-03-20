package com.yupi.yurpc.serializer;

import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;
import com.caucho.hessian.io.HessianProtocolException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class HessianSerializer implements Serializer{
    @Override
    public <T> byte[] serialize(T object) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        HessianOutput hessianOutput = null;
        try {
            hessianOutput = new HessianOutput(byteArrayOutputStream);
            hessianOutput.writeObject(object);
            hessianOutput.flush(); // 确保所有数据都被写入输出流
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new IOException("Error during serialization", e);
        } finally {
            if (hessianOutput != null) {
                try {
                    hessianOutput.close();
                } catch (IOException e) {
                    // 通常这里不需要再次抛出异常，因为主操作已经完成
                    // 但可以根据需要记录日志或执行其他清理操作
                }
            }
        }
    }

    @Override
    public <T> T deserialize(byte[] bytes, Class<T> type) throws IOException {
        if (bytes == null || bytes.length == 0) {
            throw new IllegalArgumentException("Bytes to deserialize are null or empty");
        }

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        HessianInput hessianInput = new HessianInput(byteArrayInputStream);
        try {
            T result = (T) hessianInput.readObject(type);
            if (result == null) {
                throw new IOException("Deserialized object is null");
            }
            return result;
        } catch (HessianProtocolException e) {
            throw new IOException("Hessian protocol error during deserialization", e);
        }
    }

    public static void main(String[] args) {

    }

}
