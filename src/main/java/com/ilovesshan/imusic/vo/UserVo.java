package com.ilovesshan.imusic.vo;

import com.ilovesshan.imusic.enums.Gender;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ilovesshan
 * @date: 2022/11/12
 * @description:
 */

@Data
public class UserVo {
    private String id;

    private String username;

    private String nickname;

    private Gender gender;

    private List<RoleVo> roleList;

    private boolean locked;

    private boolean enabled;

    private String lastLoginIp;

    private Date lastLoginTime;
}
