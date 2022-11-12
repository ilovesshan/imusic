package com.ilovesshan.imusic.service;

import com.ilovesshan.imusic.dto.UserDto;
import com.ilovesshan.imusic.entity.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ilovesshan
 * @date: 2022/11/12
 * @description:
 */
public interface UserService {

    List<User> selectAll(UserDto userDto);

    User selectById(String id);

}
