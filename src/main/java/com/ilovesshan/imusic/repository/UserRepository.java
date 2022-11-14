package com.ilovesshan.imusic.repository;

import com.ilovesshan.imusic.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ilovesshan
 * @date: 2022/11/12
 * @description:
 */
public interface UserRepository extends JpaRepository<User, String> {

    User findByUsername(String username);

}
