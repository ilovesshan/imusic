package com.ilovesshan.imusic.service.impl;

import com.ilovesshan.imusic.beans.entity.Music;
import com.ilovesshan.imusic.enums.MusicStatus;
import com.ilovesshan.imusic.exception.CustomException;
import com.ilovesshan.imusic.repository.MusicRepository;
import com.ilovesshan.imusic.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ilovesshan
 * @date: 2022/11/22
 * @description:
 */

@Service
public class MusicServiceImpl implements MusicService {

    @Autowired
    private MusicRepository musicRepository;


    @Override
    public List<Music> findAll() {
        return musicRepository.findAll();
    }

    @Override
    public Music findById(String musicId) {
        Optional<Music> selectedMusic = musicRepository.findById(musicId);
        if (!selectedMusic.isPresent()) {
            throw new CustomException("该歌曲信息不存在");
        } else {
            return selectedMusic.get();
        }
    }


    @Override
    public Music create(Music music) {
        music.setStatus(MusicStatus.DRAFT);
        return musicRepository.save(music);
    }


    @Override
    public Music update(Music music) {
        Music selectedMusic = findById(music.getId());
        selectedMusic.setName(music.getName());
        selectedMusic.setDescription(music.getDescription());
        return musicRepository.save(selectedMusic);
    }


    @Override
    public boolean deleteById(String musicId) {
        findById(musicId);
        musicRepository.deleteById(musicId);
        return true;
    }

    @Override
    public Music updateStatus(String musicId, MusicStatus musicStatus) {
        Music selectedMusic = findById(musicId);
        selectedMusic.setStatus(musicStatus);
        return musicRepository.save(selectedMusic);
    }
}
