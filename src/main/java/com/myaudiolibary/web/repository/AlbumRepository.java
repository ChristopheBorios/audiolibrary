package com.myaudiolibary.web.repository;

import com.myaudiolibary.web.entity.Album;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends CrudRepository<Album, Integer> { }