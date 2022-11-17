package com.ilovesshan.imusic.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ilovesshan
 * @date: 2022/11/12
 * @description:
 */

@Data
public class UserCreateDto {

    @NotNull
    @Size(min = 4, max = 24, message = "用户名长度应该在4-24个字符之间")
    private String username;

    @NotNull
    @Size(min = 8, max = 24, message = "密码长度应该在 8-24个字符之间")
    private String password;
}
