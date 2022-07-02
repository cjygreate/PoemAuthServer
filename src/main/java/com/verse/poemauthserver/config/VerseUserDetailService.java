package com.verse.poemauthserver.config;

import com.verse.poemauthserver.entity.UserInfo;
import com.verse.poemauthserver.entity.VerseUserDetail;
import com.verse.poemauthserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class VerseUserDetailService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserInfo user = userService.queryUserByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("用户未注册");
        }
//        boolean matches = passwordEncoder.matches("123", user.getPassword());
//        System.out.println(matches);
        VerseUserDetail verseUserDetail = new VerseUserDetail(user);
        return verseUserDetail;
    }

}
