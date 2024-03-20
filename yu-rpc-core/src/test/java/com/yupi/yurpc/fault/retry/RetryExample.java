package com.yupi.yurpc.fault.retry;

import com.github.rholder.retry.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
// 导入重试库的相关类（根据实际使用的库导入）
// import ... (Retryer, RetryerBuilder, Attempt, StopStrategies, WaitStrategies, RetryListener)

public class RetryExample {
    private static final Logger log = LoggerFactory.getLogger(RetryExample.class);

    public static void main(String[] args) throws Exception {
        Retryer<String> retryer = RetryerBuilder.<String>newBuilder()
                // 指定当出现特定异常时重试
                .retryIfExceptionOfType(Exception.class)
                // 设置等待策略，比如固定等待3秒
                .withWaitStrategy(WaitStrategies.fixedWait(3, TimeUnit.SECONDS))
                // 设置停止策略，比如最多重试3次
                .withStopStrategy(StopStrategies.stopAfterAttempt(3))
                // 设置重试监听器
                .withRetryListener(new RetryListener() {
                    @Override
                    public <V> void onRetry(Attempt<V> attempt) {
                        // 在这里记录尝试次数
                        log.info("尝试次数: {}", attempt.getAttemptNumber());
                    }
                })
                .build();

        // 定义一个可能会失败的操作
        Callable<String> callable = () -> {
            // 模拟一个可能会抛出异常的操作
            if (false) {
                throw new Exception("模拟的异常");
            }
            return "操作成功";
        };

        try {
            // 执行可能会失败的操作，并在必要时重试
            String result = retryer.call(callable);
            log.info("最终结果: {}", result);
        } catch (Exception e) {
            log.error("操作失败", e);
        }
    }
}
