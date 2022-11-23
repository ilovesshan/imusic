package com.ilovesshan.imusic.controller;

import com.ilovesshan.imusic.beans.dto.MusicCreateDto;
import com.ilovesshan.imusic.beans.dto.MusicUpdateDto;
import com.ilovesshan.imusic.beans.entity.Music;
import com.ilovesshan.imusic.beans.vo.MusicVo;
import com.ilovesshan.imusic.common.R;
import com.ilovesshan.imusic.converter.MusicConverter;
import com.ilovesshan.imusic.enums.MusicStatus;
import com.ilovesshan.imusic.service.MusicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ilovesshan
 * @date: 2022/11/22
 * @description:
 */

@Api(tags = "音乐模块")
@RestController
@RequestMapping("/musics")
public class MusicController {

    @Autowired
    private MusicService musicService;

    @Autowired
    private MusicConverter musicConverter;


    @ApiOperation(value = "根据ID查询")
    @GetMapping("/{id}")
    public R selectById(@PathVariable String id) {
        Music music = musicService.findById(id);
        return R.success(R.SUCCESS_MESSAGE_SELECT, musicConverter.toVo(music));
    }

    @ApiOperation(value = "分页查询")
    @GetMapping
    public R selectAll() {
        List<Music> musicList = musicService.findAll();
        List<MusicVo> musicVoList = musicList.stream().map(music -> musicConverter.toVo(music)).collect(Collectors.toList());
        return R.success(R.SUCCESS_MESSAGE_SELECT, musicVoList);
    }


    @ApiOperation(value = "新增")
    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public R create(@Validated @RequestBody MusicCreateDto musicCreateDto) {
        Music music = musicService.create(musicConverter.toEntity(musicCreateDto));
        return R.success(R.SUCCESS_MESSAGE_INSERT, musicConverter.toVo(music));
    }


    @ApiOperation(value = "更新")
    @PutMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public R update(@Validated @RequestBody MusicUpdateDto musicUpdateDto) {
        Music music = musicService.update(musicConverter.toEntity(musicUpdateDto));
        return R.success(R.SUCCESS_MESSAGE_UPDATE, musicConverter.toVo(music));
    }


    @ApiOperation(value = "上架")
    @PutMapping("/publish/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public R publish(@PathVariable String id) {
        Music music = musicService.updateStatus(id, MusicStatus.PUBLISHED);
        return R.success(R.SUCCESS_MESSAGE_UPDATE, musicConverter.toVo(music));
    }


    @ApiOperation(value = "下架")
    @PutMapping("/close/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public R close(@PathVariable String id) {
        Music music = musicService.updateStatus(id, MusicStatus.CLOSED);
        return R.success(R.SUCCESS_MESSAGE_UPDATE, musicConverter.toVo(music));
    }


    @ApiOperation(value = "根据ID删除")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public R delete(@PathVariable String id) {
        boolean isSuccess = musicService.deleteById(id);
        return isSuccess ? R.success(R.SUCCESS_MESSAGE_DELETE) : R.success(R.ERROR_MESSAGE_DELETE);
    }

}
