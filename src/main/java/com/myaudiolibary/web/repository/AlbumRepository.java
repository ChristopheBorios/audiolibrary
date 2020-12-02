package com.myaudiolibary.web.repository;

import com.myaudiolibary.web.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Integer> {

    void deleteByArtistId(Integer id);

    void deleteById(Integer id);
    Album save(Album album);
}
