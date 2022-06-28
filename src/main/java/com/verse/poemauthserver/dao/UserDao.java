package com.verse.poemauthserver.dao;

import com.verse.poemauthserver.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserDao {

    UserInfo queryUserByUsername(@Param("username") String username);

    int insert(UserInfo user);

}
