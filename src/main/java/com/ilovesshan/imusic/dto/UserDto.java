package com.ilovesshan.imusic.dto;

import com.ilovesshan.imusic.enums.Gender;
import lombok.Data;

import java.util.Date;

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

    private String username;

    private String nickname;

    private Gender gender;

    private boolean locked;

    private boolean enabled;

    private String lastLoginIp;

    private Date lastLoginTime;
}
