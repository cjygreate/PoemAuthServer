package com.verse.poemauthserver.service.impl;

import com.verse.poemauthserver.dao.UserDao;
import com.verse.poemauthserver.service.UserService;
import com.verse.poemauthserver.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserInfo queryUserByUsername(String username) {
        return userDao.queryUserByUsername(username);
    }
}
