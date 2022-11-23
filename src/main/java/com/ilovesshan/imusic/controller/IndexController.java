package com.ilovesshan.imusic.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ilovesshan
 * @date: 2022/11/10
 * @description:
 */

@Api(tags = "首页")
@RestController
@RequestMapping
public class IndexController {

    @GetMapping
    public String index() {
        return "欢迎来到Imusic音乐盒!";
    }
}
