package com.ilovesshan.imusic.repository;

import com.ilovesshan.imusic.beans.entity.User;
import com.ilovesshan.imusic.enums.Gender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ilovesshan
 * @date: 2022/11/12
 * @description:
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    @Test
    public void testInsertAndSelectUser() {
        User user = new User();
        user.setUsername("ilovesshan");
        user.setEnabled(true);
        user.setGender(Gender.MALE);
        user.setLocked(false);
        user.setLastLoginIp("127.0.0.1");
        user.setPassword("dcnifdkjfjru9hj9#$%^&*hxsud");
        user.setLastLoginTime(new Date());
        repository.save(user);

        List<User> users = repository.findAll();
        for (User u : users) {
            System.out.println(u);
        }
    }
}