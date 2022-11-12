package com.ilovesshan.imusic.controller;

import com.ilovesshan.imusic.common.R;
import com.ilovesshan.imusic.converter.UserConverter;
import com.ilovesshan.imusic.dto.UserDto;
import com.ilovesshan.imusic.entity.User;
import com.ilovesshan.imusic.service.UserService;
import com.ilovesshan.imusic.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ilovesshan
 * @date: 2022/11/12
 * @description:
 */

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserConverter userConverter;

    @GetMapping
    public R selectAll(UserDto userDto) {
        List<User> userList = userService.selectAll(userDto);
        List<UserVo> userVos = userList.stream().map(userConverter::toVo).collect(Collectors.toList());
        return R.success(R.SUCCESS_MESSAGE_SELECT, userVos);
    }

    @GetMapping("/{id}")
    public R selectAll(@PathVariable String id) {
        User user = userService.selectById(id);
        UserVo userVo = userConverter.toVo(user);
        return R.success(R.SUCCESS_MESSAGE_SELECT, userVo);
    }
}
