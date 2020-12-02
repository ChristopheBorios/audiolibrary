package com.myaudiolibary.web.repository;

import com.myaudiolibary.web.entity.Artist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Integer> {
    Page<Artist> findByNameContainsIgnoreCase(String name, Pageable pageable);

    boolean existsByName(String name);
}
