package com.verse.poemauthserver.service.impl;

import com.verse.poemauthserver.dao.UserDao;
import com.verse.poemauthserver.entity.UserInfo;
import com.verse.poemauthserver.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

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
}
