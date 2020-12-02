package com.myaudiolibary.web.service;

import com.myaudiolibary.web.entity.Artist;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

public interface ArtistService {

    Page<Artist> getArtistByName(@RequestParam(value="name") String name, @RequestParam(value="page") Integer page, @RequestParam(value="size") Integer size, @RequestParam(defaultValue="name") String sortProperty, @RequestParam(value="sortDirection", defaultValue="ASC") String sortDirection);

    Page<Artist> getAllArtist(@RequestParam(value="page") Integer page, @RequestParam(value="size") Integer size, @RequestParam(defaultValue="name") String sortProperty, @RequestParam(value="sortDirection", defaultValue="ASC") String sortDirection);

    Artist createArtist(@RequestBody Artist art);

    Artist updateArtist(@PathVariable Integer id, @RequestBody Artist artist);

    void deleteArtist(@PathVariable Integer id);

    Artist getArtist(@PathVariable(value="id") Integer id);

}
