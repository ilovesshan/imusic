package com.ilovesshan.imusic.service;

import com.ilovesshan.imusic.beans.dto.UserCreateDto;
import com.ilovesshan.imusic.beans.dto.UserDto;
import com.ilovesshan.imusic.beans.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ilovesshan
 * @date: 2022/11/12
 * @description:
 */
public interface UserService extends UserDetailsService {

    Page<User> selectAll(UserDto userDto, Integer pageNum, Integer pageSize);

    User selectById(String id);

    User createUser(UserCreateDto userCreateDto);

    @Override
    User loadUserByUsername(String username);

    void deleteById(String id);

    User update(UserDto userDto);
}
