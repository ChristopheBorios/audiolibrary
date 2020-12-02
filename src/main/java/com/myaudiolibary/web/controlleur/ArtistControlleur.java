package com.myaudiolibary.web.controlleur;

import com.myaudiolibary.web.model.Artist;
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

    @RequestMapping(value="/{id}", method= RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public Artist getArtist(@PathVariable(value="id") Integer id){
        return artistService.getArtist(id);
    }

    @RequestMapping(params = {"name"},method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public Page<Artist> getArtistByName(@RequestParam(value="name") String name, @RequestParam(value="page") Integer page, @RequestParam(value="size") Integer size, @RequestParam(defaultValue="name") String sortProperty, @RequestParam(value="sortDirection", defaultValue="ASC") String sortDirection){
        return artistService.getArtistByName(name, page, size, sortProperty, sortDirection);
    }

    @RequestMapping(value="", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public Page<Artist> getAllArtist(@RequestParam(value="page") Integer page, @RequestParam(value="size") Integer size, @RequestParam(defaultValue="name") String sortProperty, @RequestParam(value="sortDirection", defaultValue="ASC") String sortDirection){
        return artistService.getAllArtist(page, size, sortProperty, sortDirection);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Artist createArtist(@RequestBody Artist art)//Request body map les request json, pour transformer du json en java
    {
        return artistService.createArtist(art);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces="application/json", value="/{id}")//produces=MediaType.APPLICATION_JSON_VALUE
    public Artist updateArtist(@PathVariable Integer id, @RequestBody Artist artist)
    {
        return artistService.updateArtist(id, artist);
    }

    @RequestMapping(method = RequestMethod.DELETE, value="/{id}")//pas produces car on renvoit rien
    @ResponseStatus(HttpStatus.NO_CONTENT)//204 sa ses bien passé je te renvoie rien mais c'est normal
    public void deleteArtist(@PathVariable Integer id)
    {
        artistService.deleteArtist(id);
    }



}
