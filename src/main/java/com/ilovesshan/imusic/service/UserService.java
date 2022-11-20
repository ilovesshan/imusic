package com.ilovesshan.imusic.service;

import com.ilovesshan.imusic.beans.dto.UserAuthDto;
import com.ilovesshan.imusic.beans.dto.UserDto;
import com.ilovesshan.imusic.beans.dto.UserRegisterDto;
import com.ilovesshan.imusic.beans.entity.User;
import com.ilovesshan.imusic.beans.vo.UserAuthVo;
import org.springframework.data.domain.Page;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ilovesshan
 * @date: 2022/11/12
 * @description:
 */
public interface UserService  /* extends UserDetailsService */  {

    Page<User> selectAll(UserDto userDto, Integer pageNum, Integer pageSize);

    User selectById(String id);

    User createUser(UserRegisterDto userRegisterDto);

     // @Override
     // User loadUserByUsername(String username);

    void deleteById(String id);

    User update(UserDto userDto);

    UserAuthVo auth(UserAuthDto userAuthDto);
}
