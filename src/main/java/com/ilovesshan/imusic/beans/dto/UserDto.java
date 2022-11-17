package com.ilovesshan.imusic.beans.dto;

import com.ilovesshan.imusic.enums.Gender;
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
public class UserDto {
    private String id;

    @NotNull
    @Size(min = 4, max = 24, message = "用户名长度应该在4-24个字符之间")
    private String username;

    @NotNull
    @Size(min = 4, max = 16, message = "昵称长度应该在4-16个字符之间")
    private String nickname;

    private Gender gender;

    private Boolean locked = false;

    private Boolean enabled = true;
}
