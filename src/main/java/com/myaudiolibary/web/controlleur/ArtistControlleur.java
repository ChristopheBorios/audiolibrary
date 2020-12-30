package com.myaudiolibary.web.controlleur;

import com.myaudiolibary.web.entity.Artist;
import com.myaudiolibary.web.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/artists")
public class ArtistControlleur {

    @Autowired
    private ArtistService artistService;

    @GetMapping(value="index", produces= MediaType.APPLICATION_JSON_VALUE)
    public @ModelAttribute("artists") Artist getArtist(@PathVariable(value="id") Integer id){
        Artist artists = artistService.getArtist(id);
        return artists;
    }

    @GetMapping(params = {"name"}, produces=MediaType.APPLICATION_JSON_VALUE)
    public Page<Artist> getArtistByName(@RequestParam(value="name") String name, @RequestParam(value="page") Integer page, @RequestParam(value="size") Integer size, @RequestParam(defaultValue="name") String sortProperty, @RequestParam(value="sortDirection", defaultValue="ASC") String sortDirection){
        return artistService.getArtistByName(name, page, size, sortProperty, sortDirection);
    }

    @GetMapping(value="", produces=MediaType.APPLICATION_JSON_VALUE)
    public Page<Artist> getAllArtist(@RequestParam(value="page") Integer page, @RequestParam(value="size") Integer size, @RequestParam(defaultValue="name") String sortProperty, @RequestParam(value="sortDirection", defaultValue="ASC") String sortDirection){
        return artistService.getAllArtist(page, size, sortProperty, sortDirection);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Artist createArtist(@RequestBody Artist art)
    {
        return artistService.createArtist(art);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces="application/json", value="/{id}")
    public Artist updateArtist(@PathVariable Integer id, @RequestBody Artist artist)
    {
        return artistService.updateArtist(id, artist);
    }

    @DeleteMapping(value="/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteArtist(@PathVariable Integer id)
    {
        artistService.deleteArtist(id);
    }



}
