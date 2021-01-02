package com.myaudiolibary.web.repository;

import java.util.List;
import java.util.Optional;

import com.myaudiolibary.web.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.EntityNotFoundException;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Integer> {
       Artist findByName(String name);

       List<Artist> findByNameContainsIgnoreCase(String name);

}