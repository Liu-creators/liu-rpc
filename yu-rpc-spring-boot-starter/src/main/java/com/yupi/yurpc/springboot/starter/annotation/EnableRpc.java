package com.yupi.yurpc.springboot.starter.annotation;

import com.yupi.yurpc.springboot.starter.bootstrap.RpcConsummerBootstrap;
import com.yupi.yurpc.springboot.starter.bootstrap.RpcInitBootstrap;
import com.yupi.yurpc.springboot.starter.bootstrap.RpcProviderBootstrap;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 启用 Rpc 注解
 */
@Target({ElementType.TYPE}) //只能用于类、接口（包括注解类型）或枚举声明。不能应用于方法、字段等其他程序元素
@Retention(RetentionPolicy.RUNTIME) //运行时仍然保留，因此可以通过反射来访问它
@Import({RpcInitBootstrap.class, RpcProviderBootstrap.class, RpcConsummerBootstrap.class})
public @interface EnableRpc {
    /**
     * 需要启动 server
     * @return
     */
    boolean needServer() default true;
}
