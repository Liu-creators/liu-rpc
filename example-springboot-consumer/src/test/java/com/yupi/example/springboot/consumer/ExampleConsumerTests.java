package com.yupi.example.springboot.consumer;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class ExampleConsumerTests {

    @Resource
    private ExampleServiceImpl exampleService;
    @Test
    void test1() {
        exampleService.test();
    }

}
