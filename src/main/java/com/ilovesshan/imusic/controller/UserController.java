package com.ilovesshan.imusic.controller;

import com.ilovesshan.imusic.beans.dto.UserDto;
import com.ilovesshan.imusic.beans.entity.User;
import com.ilovesshan.imusic.beans.vo.UserVo;
import com.ilovesshan.imusic.common.R;
import com.ilovesshan.imusic.converter.UserConverter;
import com.ilovesshan.imusic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('ROLE_ADMIN')")
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


    @PutMapping
    public R update(@Validated @RequestBody UserDto userDto) {
        User user = userService.update(userDto);
        UserVo userVo = userConverter.toVo(user);
        return R.success(R.SUCCESS_MESSAGE_UPDATE, userVo);
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public R deleteById(@PathVariable String id) {
        userService.deleteById(id);
        return R.success(R.SUCCESS_MESSAGE_DELETE);
    }
}
