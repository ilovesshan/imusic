package com.ilovesshan.imusic.entity;

import com.ilovesshan.imusic.enums.Gender;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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

    @Column(unique = true)
    private String username;

    private String nickname;

    private String password;

    @ManyToMany(cascade = {}, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = {
                    // user_id 数据表user_role   id java对象User
                    @JoinColumn(name = "user_id", referencedColumnName = "id"),
            },
            inverseJoinColumns = {
                    // role_id 数据表user_role   id java对象Role
                    @JoinColumn(name = "role_id", referencedColumnName = "id")
            })
    private List<Role> roleList;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private boolean locked;

    private boolean enabled;

    private String lastLoginIp;

    private Date lastLoginTime;

}

