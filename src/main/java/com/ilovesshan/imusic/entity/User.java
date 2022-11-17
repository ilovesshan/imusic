package com.ilovesshan.imusic.entity;

import com.ilovesshan.imusic.enums.Gender;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
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
public class User extends BaseEntity implements UserDetails {

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

    private Boolean locked = false;

    private Boolean enabled = true;

    private String lastLoginIp;

    private Date lastLoginTime;

    private Date createdTime;

    private Date updatedTime;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        //账号是否未过期
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // 账号是否未锁定
        return !getLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        //密码是否未过期
        return true;
    }

    @Override
    public boolean isEnabled() {
        //是否激活
        return getEnabled();
    }
}

