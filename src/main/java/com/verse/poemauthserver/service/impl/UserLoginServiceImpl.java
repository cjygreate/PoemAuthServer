package com.verse.poemauthserver.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.verse.common.util.JwtTokenUtils;
import com.verse.common.util.RedisUtils;
import com.verse.poemauthserver.dao.UserDao;
import com.verse.poemauthserver.entity.UserInfo;
import com.verse.poemauthserver.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class UserLoginServiceImpl implements UserLoginService {

    private static final Random RANDOM = new SecureRandom();

    @Autowired
    private UserDao userDao;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public void register(UserInfo userInfo) {

        UserInfo user = userDao.queryUserByUsername(userInfo.getUsername());
        if(user != null) {
            throw new RuntimeException("用户名已存在");
        }
        String encode = passwordEncoder.encode(userInfo.getPassword());
        userInfo.setPassword(encode);
        int insert = userDao.insert(userInfo);
        if(insert != 1) {
            throw new RuntimeException("系统异常");
        }
    }

    @Override
    public String login(UserInfo userInfo) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userInfo.getUsername(), userInfo.getPassword());
        Authentication authenticate = authenticationManager.authenticate(token);
        boolean authenticated = authenticate.isAuthenticated();
        String jwtToken = "";
        if(authenticated) {
            //登录成功生成token返回到前端
            try {
                jwtToken = JwtTokenUtils.createToken(userInfo.getUsername());
                UserInfo user = userDao.queryUserByUsername(userInfo.getUsername());
                user.setPassword(null);
                redisUtils.setExpire("user_" + userInfo.getUsername(), JSONObject.toJSONString(user), (long) (60 * 60 + RANDOM.nextInt(20)));
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }
        return jwtToken;
    }
}
