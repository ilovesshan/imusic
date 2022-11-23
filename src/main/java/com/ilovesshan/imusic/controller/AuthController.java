package com.ilovesshan.imusic.controller;

import com.ilovesshan.imusic.beans.dto.UserAuthDto;
import com.ilovesshan.imusic.beans.dto.UserRegisterDto;
import com.ilovesshan.imusic.beans.entity.User;
import com.ilovesshan.imusic.beans.vo.UserAuthVo;
import com.ilovesshan.imusic.beans.vo.UserVo;
import com.ilovesshan.imusic.common.R;
import com.ilovesshan.imusic.converter.UserConverter;
import com.ilovesshan.imusic.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ilovesshan
 * @date: 2022/11/10
 * @description:
 */

@Api(tags = "授权模块")
@Controller
@RequestMapping
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserConverter userConverter;


    @ApiOperation(value = "登录")
    @PostMapping("/auth")
    @ResponseBody
    public R auth(@Validated @RequestBody UserAuthDto userAuthDto) {
        UserAuthVo userAuthVo = userService.auth(userAuthDto);
        return R.success(R.SUCCESS_MESSAGE_LOGIN, userAuthVo);
    }


    @ApiOperation(value = "登出")
    @PostMapping("/logout")
    @ResponseBody
    public R logout() {
        return R.success(R.SUCCESS_MESSAGE_LOGOUT);
    }


    @ApiOperation(value = "注册")
    @PostMapping("/register")
    @ResponseBody
    public R create(@Validated @RequestBody UserRegisterDto userRegisterDto) {
        User user = userService.createUser(userRegisterDto);
        UserVo userVo = userConverter.toVo(user);
        return R.success(R.SUCCESS_MESSAGE_REGISTER, userVo);
    }
}
