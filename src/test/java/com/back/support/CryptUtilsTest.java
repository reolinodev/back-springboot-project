package com.back.support;


import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CryptUtilsTest {

    @Test
    void encrypt() throws IOException {
        String str = "bdrnd";
        String ecnryptStr = CryptUtils.encrypt(str);
        System.out.println(ecnryptStr);
    }

    @Test
    void encrypt256() throws IOException, NoSuchAlgorithmException {
        String str = "1234";
        String ecnryptStr = CryptUtils.encryptSha256(str);
        System.out.println(ecnryptStr);
    }

    @Test
    void decrypt() throws IOException {
        String str = "";
        String decryptStr = CryptUtils.decrypt(str);
        System.out.println(decryptStr);
    }
}

