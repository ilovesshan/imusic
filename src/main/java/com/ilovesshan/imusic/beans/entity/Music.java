package com.ilovesshan.imusic.beans.entity;

import com.ilovesshan.imusic.enums.MusicStatus;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ilovesshan
 * @date: 2022/11/22
 * @description:
 */

@Entity
@Data
public class Music extends BaseEntity {

    private String name;

    @Enumerated(EnumType.STRING)
    private MusicStatus status;

    private String description;
}
