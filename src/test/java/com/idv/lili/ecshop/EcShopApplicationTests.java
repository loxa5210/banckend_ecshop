package com.idv.lili.ecshop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class EcShopApplicationTests {

    @Value("${testUrl}")
    List<String> viewUrl;

    @Test
    void contextLoads() {

        System.err.println(viewUrl);

    }


}
