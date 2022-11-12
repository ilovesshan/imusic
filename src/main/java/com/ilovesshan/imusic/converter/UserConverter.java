package com.ilovesshan.imusic.converter;

import com.ilovesshan.imusic.dto.UserDto;
import com.ilovesshan.imusic.entity.User;
import com.ilovesshan.imusic.vo.UserVo;
import org.mapstruct.Mapper;
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

    UserDto toDto(User user);
}
