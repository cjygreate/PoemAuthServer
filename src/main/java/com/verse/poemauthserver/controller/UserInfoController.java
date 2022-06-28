package com.verse.poemauthserver.controller;

import com.verse.common.data.VerseResponse;
import com.verse.poemauthserver.entity.UserInfo;
import com.verse.poemauthserver.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/verse")
public class UserInfoController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserInfoService userInfoService;

    @PostMapping("/user/login")
    public void login(@RequestBody UserInfo userInfo) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userInfo.getUsername(), userInfo.getPassword());
        Authentication authenticate = authenticationManager.authenticate(token);
        boolean authenticated = authenticate.isAuthenticated();
        System.out.println(authenticated);
        System.out.println("login....");
    }

    @PostMapping("/user/register")
    public VerseResponse register(@RequestBody UserInfo userInfo) {
        userInfoService.register(userInfo);
        return new VerseResponse().success();
    }

    @GetMapping("/user/test")
    public String register() {

        System.out.println("test....");
        return "test";
    }


}
