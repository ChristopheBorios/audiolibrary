package com.myaudiolibary.web.service.impl;

import com.myaudiolibary.web.entity.Album;
import com.myaudiolibary.web.repository.AlbumRepository;
import com.myaudiolibary.web.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    @Override
    public Album createAlbum(@RequestBody Album album)
    {
        albumRepository.save(album);
        return album;
    }

    @Override
    public void deleteAlbum(@PathVariable Integer id)
    {
        albumRepository.deleteById(id);
    }

}
