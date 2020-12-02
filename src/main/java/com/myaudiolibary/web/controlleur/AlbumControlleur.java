package com.myaudiolibary.web.controlleur;

import com.myaudiolibary.web.entity.Album;
import com.myaudiolibary.web.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/albums")
public class AlbumControlleur {
    @Autowired
    private AlbumService albumService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Album createAlbum(@RequestBody Album album)
    {
        return albumService.createAlbum(album);
    }

    @DeleteMapping(value="/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAlbum(@PathVariable Integer id)
    {
        albumService.deleteAlbum(id);
    }
}
