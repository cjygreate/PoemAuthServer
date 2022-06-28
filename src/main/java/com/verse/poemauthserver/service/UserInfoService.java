package com.verse.poemauthserver.service;

import com.verse.poemauthserver.entity.UserInfo;
import org.springframework.web.bind.annotation.RequestBody;

public interface UserInfoService {

    void register(UserInfo userInfo);


}
