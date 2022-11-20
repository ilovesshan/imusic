package com.ilovesshan.imusic.beans.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ilovesshan
 * @date: 2022/11/17
 * @description:
 */

@Data
@MappedSuperclass
public abstract class BaseVo {
    @JsonFormat(pattern = "yyyy-MM-hh HH:mm:ss", timezone = "GMT+8")
    private Date createdTime;

    @JsonFormat(pattern = "yyyy-MM-hh HH:mm:ss", timezone = "GMT+8")
    private Date updatedTime;
}
