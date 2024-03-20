package com.yupi.example.springboot.consumer;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class ExampleConsumerTests {

    @Resource
    private ExampleServiceImpl exampleService;

    @Test
    void test0() {
        exampleService.test0();
    }
    @Test
    void test() {
        exampleService.test();
    }

    /**
     * mock 接口测试
     */
    @Test
    void test1() {
        exampleService.test1();
    }

}
