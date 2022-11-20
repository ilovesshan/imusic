package com.ilovesshan.imusic.beans.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class UserVo extends BaseVo {
    private String id;

    private String username;

    private String nickname;

    private Gender gender;

    private List<RoleVo> roleList;

    private boolean locked;

    private boolean enabled;

    private String lastLoginIp;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date lastLoginTime;

}
