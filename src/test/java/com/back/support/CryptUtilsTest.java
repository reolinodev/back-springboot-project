package com.back.support;

import static org.junit.jupiter.api.Assertions.*;

import com.back.domain.User;
import java.io.IOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CryptUtilsTest {

    @Test
    void encrypt() throws IOException {
        String str = "test";
        String ecnryptStr = CryptUtils.encrypt(str);
        System.out.println("=="+ecnryptStr);
    }
}