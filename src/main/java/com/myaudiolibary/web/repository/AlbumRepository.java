package com.myaudiolibary.web.repository;

import com.myaudiolibary.web.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Integer> {
    Album findByTitle(String title);

    void deleteById(Integer id);

    Album save(Album album);

    boolean existsByTitle(String title);
}