package com.ilovesshan.imusic.beans.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ilovesshan
 * @date: 2022/11/12
 * @description:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthVo{
    private String id;

    private String username;

    private String token;
}
