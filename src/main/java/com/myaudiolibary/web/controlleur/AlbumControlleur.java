package com.myaudiolibary.web.controlleur;

import com.myaudiolibary.web.entity.Album;
import com.myaudiolibary.web.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;


@Controller
@RequestMapping("/albums")
public class AlbumControlleur {
    @Autowired
    private AlbumRepository albumRepository;

   //Creation d'un album (ne fonctionne pas)
   @PostMapping("/createAlbum")
    public RedirectView createAlbum(@ModelAttribute("album") Album album)
    {

        if(albumRepository.existsByTitle(album.getTitle())){
            throw new EntityExistsException("Album title: "+album.getTitle()+" doesn't exist");
        }
        if(album.getTitle().isEmpty()){
            throw new EntityNotFoundException("Album doesn't have title !");
        }
        albumRepository.save(album);

        return new RedirectView("/artists/"+album.getArtist().getId());
    }

    //Suppression d'un album
    @RequestMapping(method = RequestMethod.GET, value="/{id}/{artid}/delete")
    public RedirectView suppAlbum(@PathVariable("id") Integer id, @PathVariable("artid") int artid)
    {

        if(!albumRepository.existsById(id)){
            throw new EntityNotFoundException("Album: "+id+" not found !");
        }
        albumRepository.deleteById(id);

        return new RedirectView("/artists/"+artid);
    }
}
