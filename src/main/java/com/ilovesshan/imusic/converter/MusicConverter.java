package com.ilovesshan.imusic.converter;

import com.ilovesshan.imusic.beans.dto.MusicCreateDto;
import com.ilovesshan.imusic.beans.dto.MusicSelectedDto;
import com.ilovesshan.imusic.beans.dto.MusicUpdateDto;
import com.ilovesshan.imusic.beans.entity.Music;
import com.ilovesshan.imusic.beans.vo.MusicVo;
import org.mapstruct.Builder;
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

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
@Component
public interface MusicConverter {
    MusicVo toVo(Music music);

    Music toEntity(MusicCreateDto musicCreateDto);

    Music toEntity(MusicUpdateDto musicUpdateDto);

    Music toEntity(MusicSelectedDto musicSelectedDto);

    Music mergeEntity(@MappingTarget Music music, Music music1);

}
