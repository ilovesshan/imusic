package com.ilovesshan.imusic.beans.entity;

import lombok.Data;

import javax.persistence.Entity;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ilovesshan
 * @date: 2022/11/12
 * @description:
 */

@Entity
@Data
public class Role extends BaseEntity {

    private String name;

    private String title;
}
