package com.ilovesshan.imusic.repository;

import com.ilovesshan.imusic.beans.entity.Music;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ilovesshan
 * @date: 2022/11/12
 * @description:
 */
public interface MusicRepository extends JpaRepository<Music, String>, JpaSpecificationExecutor<Music> {
}
