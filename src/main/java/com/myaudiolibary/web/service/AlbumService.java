package com.myaudiolibary.web.service;

import com.myaudiolibary.web.model.Album;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface AlbumService {

    void deleteById(@RequestBody Album album);

    void save(@PathVariable Integer id);
}
