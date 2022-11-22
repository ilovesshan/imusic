package com.ilovesshan.imusic.beans.vo;

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
public class MusicVo extends BaseVo {
    private String id;

    private String name;

    private MusicStatus status;

    private String description;
}
