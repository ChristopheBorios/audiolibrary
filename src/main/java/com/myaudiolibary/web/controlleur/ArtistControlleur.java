package com.myaudiolibary.web.controlleur;

import com.myaudiolibary.web.model.Artist;
import com.myaudiolibary.web.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@RestController
@RequestMapping("/artists")
public class ArtistControlleur {

    @Autowired
    private ArtistRepository artistRepository;

    @RequestMapping(value="/{id}", method= RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public Artist getArtist(@PathVariable(value="id") Integer id){
        Optional<Artist> art = artistRepository.findById(id);
        if (art.isEmpty()) {
            //erreur 404
            throw new EntityNotFoundException("L'artist d'id: "+id+" n'a pas été trouvé");
        }
        return art.get();
    }

    @RequestMapping(params = {"name"},method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public Page<Artist> getArtistByName(@RequestParam(value="name") String name, @RequestParam(value="page") Integer page, @RequestParam(value="size") Integer size, @RequestParam(defaultValue="name") String sortProperty, @RequestParam(value="sortDirection", defaultValue="ASC") String sortDirection){
        Page<Artist> lstArt = artistRepository.findByNameContainsIgnoreCase(name, PageRequest.of(page, size, Sort.Direction.fromString(sortDirection), sortProperty));
        return lstArt;
    }
    //public Page<Artist> getArtistByName(@RequestParam(value="name") String name, @RequestParam(value="page") Integer page, @RequestParam(value="size") Integer size, @RequestParam(defaultValue="name") String sortProperty, @RequestParam(value="sortDirection", defaultValue="ASC") String sortDirection){

    @RequestMapping(value="", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public Page<Artist> getAllArtist(@RequestParam(value="page") Integer page, @RequestParam(value="size") Integer size, @RequestParam(defaultValue="name") String sortProperty, @RequestParam(value="sortDirection", defaultValue="ASC") String sortDirection){
        if(page<0){throw new IllegalArgumentException("Le parametre page doit etre positif ou nul !");}
        if(size <= 0 || size >50){throw new IllegalArgumentException("Le parametre size doit etre entre 0 et 50");}
        if(!"ASC".equalsIgnoreCase(sortDirection) && !"DESC".equalsIgnoreCase(sortDirection)){throw new IllegalArgumentException("Le parametre sort direction doit valoir ASC ou DESC");}
        Page<Artist> lstArt = artistRepository.findAll(PageRequest.of(page, size, Sort.Direction.fromString(sortDirection), sortProperty));
        return lstArt;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Artist createArtist(@RequestBody Artist art)//Request body map les request json, pour transformer du json en java
    {
        if(artistRepository.existsByName(art.getName())){//retourn un boolean true ou false si true exception si false il crée
            throw new EntityExistsException("L'artiste de nom: "+art.getName()+" existe deja!");//EntityExistsException=ERROR 409
        }
        artistRepository.save(art);
        return art;
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces="application/json", value="/{id}")//produces=MediaType.APPLICATION_JSON_VALUE
    public Artist updateArtist(@PathVariable Integer id, @RequestBody Artist artist)
    {
        if(!artistRepository.existsById(id)){
            throw new EntityNotFoundException("L'artiste d'identifiant: "+id+" n'a pas été trouvé !");
        }
        if(artistRepository.existsByName(artist.getName())){//retourn un boolean true ou false si true exception si false il crée
            throw new EntityExistsException("Un artiste de nom: "+artist.getName()+" existe deja!");//on évite que les petis malins nous clone des artistes à partir de d'autre artiste
        }
        artistRepository.save(artist);
        return artist;
    }

    //le delete artist par id
    @RequestMapping(method = RequestMethod.DELETE, value="/{id}")//pas produces car on renvoit rien
    @ResponseStatus(HttpStatus.NO_CONTENT)//204 sa ses bien passé je te renvoie rien mais c'est normal
    public void deleteArtist(@PathVariable Integer id)
    {
        if(!artistRepository.existsById(id)){
            throw new EntityNotFoundException("L'artiste d'identifiant: "+id+" n'a pas été trouvé !");
        }
        //delete les albums de l'artistes en prems

        artistRepository.deleteById(id);
    }



}
