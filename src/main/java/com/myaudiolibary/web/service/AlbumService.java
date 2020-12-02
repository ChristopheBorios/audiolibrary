package com.myaudiolibary.web.service;

import com.myaudiolibary.web.entity.Album;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface AlbumService {

    void deleteAlbum(@PathVariable Integer id);

    Album createAlbum(@RequestBody Album album);
}
