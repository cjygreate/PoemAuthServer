package com.verse.poemauthserver;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class PoemAuthServerApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testEncoding() {
        String encode = passwordEncoder.encode("123");
        System.out.println(encode);
    }

}
