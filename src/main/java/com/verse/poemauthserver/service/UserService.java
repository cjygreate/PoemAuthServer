package com.verse.poemauthserver.service;

import com.verse.poemauthserver.entity.UserInfo;

public interface UserService {


    UserInfo queryUserByUsername(String username);


}
