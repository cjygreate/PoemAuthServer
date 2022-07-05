package com.verse.poemauthserver;

import com.verse.common.util.RedisUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class PoemAuthServerApplicationTests {


    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisUtils redisUtils;

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

    @Test
    public void testRedis() {
//        System.out.println(redisTemplate);
//        redisTemplate.opsForValue().set("aaa2", "123");
//        Object aaa = redisTemplate.opsForValue().get("aaa");
//        System.out.println(aaa);

//        System.out.println(redisTemplate);
//        redisUtils.set("aaa", "bbbb");
//        System.out.println("成功");
        System.out.println(redisUtils.get("aaa"));

    }

}
