package com.ilovesshan.imusic.entity;

import com.ilovesshan.imusic.enums.Gender;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ilovesshan
 * @date: 2022/11/12
 * @description:
 */

@Entity
@Data
public class User extends BaseEntity {
    private String username;

    private String nickname;

    private String password;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private boolean locked;

    private boolean enabled;

    private String lastLoginIp;

    private Date lastLoginTime;

}

