package com.ilovesshan.imusic.converter;

import com.ilovesshan.imusic.beans.dto.UserCreateDto;
import com.ilovesshan.imusic.beans.dto.UserDto;
import com.ilovesshan.imusic.beans.entity.User;
import com.ilovesshan.imusic.beans.vo.UserVo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ilovesshan
 * @date: 2022/11/12
 * @description:
 */

@Mapper(componentModel = "spring")
@Component
public interface UserConverter {
    UserVo toVo(User user);

    User toEntity(UserDto userDto);

    User toEntity(UserCreateDto userCreateDto);

    User updateEntity(@MappingTarget User user, UserDto userDto);
}
