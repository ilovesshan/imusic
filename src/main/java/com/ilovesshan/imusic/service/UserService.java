package com.ilovesshan.imusic.service;

import com.ilovesshan.imusic.dto.UserCreateDto;
import com.ilovesshan.imusic.dto.UserDto;
import com.ilovesshan.imusic.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ilovesshan
 * @date: 2022/11/12
 * @description:
 */
public interface UserService extends UserDetailsService {

    List<User> selectAll(UserDto userDto);

    User selectById(String id);

    User createUser(UserCreateDto userCreateDto);

    @Override
    User loadUserByUsername(String username);
}
