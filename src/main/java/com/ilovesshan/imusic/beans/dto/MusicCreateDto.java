package com.ilovesshan.imusic.beans.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ilovesshan
 * @date: 2022/11/22
 * @description:
 */

@Data
public class MusicCreateDto {

    @NotNull(message = "音乐名称不能为空")
    private String name;

    private String description;
}
