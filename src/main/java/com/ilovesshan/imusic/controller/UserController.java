package com.ilovesshan.imusic.controller;

import com.ilovesshan.imusic.common.R;
import com.ilovesshan.imusic.converter.UserConverter;
import com.ilovesshan.imusic.dto.UserCreateDto;
import com.ilovesshan.imusic.dto.UserDto;
import com.ilovesshan.imusic.entity.User;
import com.ilovesshan.imusic.service.UserService;
import com.ilovesshan.imusic.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public R selectAll(UserDto userDto, Integer pageNum, Integer pageSize) {
        Page<User> userList = userService.selectAll(userDto, pageNum, pageSize);
        Page<UserVo> userVos = userList.map(userConverter::toVo);
        return R.success(R.SUCCESS_MESSAGE_SELECT, userVos);
    }

    @GetMapping("/{id}")
    public R selectAll(@PathVariable String id) {
        User user = userService.selectById(id);
        UserVo userVo = userConverter.toVo(user);
        return R.success(R.SUCCESS_MESSAGE_SELECT, userVo);
    }

    @PostMapping
    public R create(@Validated @RequestBody UserCreateDto userCreateDto) {
        User user = userService.createUser(userCreateDto);
        UserVo userVo = userConverter.toVo(user);
        return R.success(R.SUCCESS_MESSAGE_INSERT, userVo);
    }

    @PutMapping
    public R update(@Validated @RequestBody UserDto userDto) {
        User user = userService.update(userDto);
        UserVo userVo = userConverter.toVo(user);
        return R.success(R.SUCCESS_MESSAGE_UPDATE, userVo);
    }


    @DeleteMapping("/{id}")
    public R deleteById(@PathVariable String id) {
        userService.deleteById(id);
        return R.success(R.SUCCESS_MESSAGE_DELETE);
    }
}
