package com.myaudiolibary.web.repository;

import java.util.List;
import java.util.Set;

import com.myaudiolibary.web.entity.Album;
import com.myaudiolibary.web.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Integer> {
        List<Artist> findByNameContainsIgnoreCase(String name);

}