package com.verse.poemauthserver.service;

import com.verse.poemauthserver.entity.UserInfo;

public interface UserLoginService {

    void register(UserInfo userInfo);


    String login(UserInfo userInfo);
}
