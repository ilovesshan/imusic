package com.ilovesshan.imusic.service.impl;

import com.ilovesshan.imusic.converter.UserConverter;
import com.ilovesshan.imusic.beans.dto.UserCreateDto;
import com.ilovesshan.imusic.beans.dto.UserDto;
import com.ilovesshan.imusic.beans.entity.User;
import com.ilovesshan.imusic.exception.CustomException;
import com.ilovesshan.imusic.repository.UserRepository;
import com.ilovesshan.imusic.service.UserService;
import com.ilovesshan.imusic.utils.IRealIPAddressUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

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
    public Page<User> selectAll(UserDto userDto, Integer pageNum, Integer pageSize) {
        // if (pageSize != null && pageNum != null) {
        //     // 分页查询
        //     Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        //     return userRepository.findBy(userDto, pageable);
        // }
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        return userRepository.findAll(pageable);
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
        // 更新登录时间 和  登录IP
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String ipAddress = IRealIPAddressUtil.getIpAddress(request);
        user.setLastLoginTime(new Date());
        user.setLastLoginIp(ipAddress);
        userRepository.save(user);
        return user;
    }

    @Override
    public void deleteById(String id) {
        if (selectById(id) == null) {
            throw new CustomException("用户不存在");
        }
        userRepository.deleteById(id);
    }

    @Override
    public User update(UserDto userDto) {
        User selectedUser = selectById(userDto.getId());
        if (selectedUser == null) {
            throw new CustomException("用户不存在");
        }
        selectedUser = userConverter.updateEntity(selectedUser, userDto);
        return userRepository.save(selectedUser);
    }
}
