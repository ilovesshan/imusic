package com.ilovesshan.imusic.service;

import com.ilovesshan.imusic.beans.entity.Music;
import com.ilovesshan.imusic.enums.MusicStatus;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ilovesshan
 * @date: 2022/11/22
 * @description:
 */
public interface MusicService {

    List<Music> findAll();

    Music findById(String musicId);

    Music create(Music music);

    Music update(Music music);

    boolean deleteById(String musicId);

    Music updateStatus(String musicId, MusicStatus musicStatus);
}
