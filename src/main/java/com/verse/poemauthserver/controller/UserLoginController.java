package com.verse.poemauthserver.controller;

import com.verse.common.data.VerseResponse;
import com.verse.poemauthserver.entity.UserInfo;
import com.verse.poemauthserver.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/verse")
public class UserLoginController {

    @Autowired
    private UserLoginService userLoginService;

    @PostMapping("/user/login")
    public VerseResponse login(@RequestBody UserInfo userInfo) {
        String jwtToken = userLoginService.login(userInfo);
        System.out.println("login....");
        VerseResponse response = new VerseResponse();
        return response.success(jwtToken);
    }

    @PostMapping("/user/register")
    public VerseResponse register(@RequestBody UserInfo userInfo) {
        userLoginService.register(userInfo);
        return new VerseResponse().success();
    }

    @GetMapping("/user/test")
    public String register() {

        System.out.println("test....");
        return "test";
    }


}
