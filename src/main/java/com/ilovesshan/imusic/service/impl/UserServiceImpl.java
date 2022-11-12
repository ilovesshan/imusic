package com.ilovesshan.imusic.service.impl;

import com.ilovesshan.imusic.converter.UserConverter;
import com.ilovesshan.imusic.dto.UserDto;
import com.ilovesshan.imusic.entity.User;
import com.ilovesshan.imusic.repository.UserRepository;
import com.ilovesshan.imusic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ilovesshan
 * @date: 2022/11/12
 * @description:
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    @Override
    public List<User> selectAll(UserDto userDto) {
        return userRepository.findAll();
    }

    @Override
    public User selectById(String id) {
        return userRepository.findById(id).orElse(null);
    }
}
