package com.ilovesshan.imusic.beans.dto;

import com.ilovesshan.imusic.enums.MusicStatus;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ilovesshan
 * @date: 2022/11/22
 * @description:
 */

@Data
public class MusicSelectedDto {

    private String name;

    private String description;

    private MusicStatus status;
}
