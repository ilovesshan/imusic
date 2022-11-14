package com.ilovesshan.imusic.service.impl;

import com.ilovesshan.imusic.converter.UserConverter;
import com.ilovesshan.imusic.dto.UserCreateDto;
import com.ilovesshan.imusic.dto.UserDto;
import com.ilovesshan.imusic.entity.User;
import com.ilovesshan.imusic.exception.CustomException;
import com.ilovesshan.imusic.repository.UserRepository;
import com.ilovesshan.imusic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> selectAll(UserDto userDto) {
        return userRepository.findAll();
    }

    @Override
    public User selectById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User createUser(UserCreateDto userCreateDto) {
        User user = userConverter.toEntity(userCreateDto);
        if (userRepository.findByUsername(user.getUsername()) != null) {
            // 用户已经存在了
            throw new CustomException("用户已经存在");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new CustomException("用户不存在");
        }
        return user;
    }
}
