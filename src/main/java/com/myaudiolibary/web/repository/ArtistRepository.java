package com.myaudiolibary.web.repository;

import java.util.ArrayList;
import java.util.List;
import com.myaudiolibary.web.entity.Artist;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends CrudRepository<Artist, Integer> {
        List<Artist> findByNameContainsIgnoreCase(String name);
}