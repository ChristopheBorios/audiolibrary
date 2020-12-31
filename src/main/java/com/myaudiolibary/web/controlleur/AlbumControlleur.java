/*
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
*/

package com.myaudiolibary.web.controlleur;

import com.myaudiolibary.web.entity.Album;
import com.myaudiolibary.web.entity.Artist;
import com.myaudiolibary.web.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/albums")
public class AlbumControlleur {

    private final AlbumRepository albumRepository;

    @Autowired
    public AlbumControlleur(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @GetMapping("signup")
    public String showSignUpForm(Album album) {
        return "add-artist";
    }

    @GetMapping("list")
    public String showUpdateForm(Model model) {
        model.addAttribute("albums", albumRepository.findAll());
        return "index";
    }

    @PostMapping("add")
    public String addAlbum(Album album, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-artist";
        }

        albumRepository.save(album);
        return "redirect:list";
    }

    @GetMapping("edit/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Album album = albumRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid album Id:" + id));
        model.addAttribute("album", album);
        return "update-artist";
    }

    @PostMapping("update/{id}")
    public String updateAlbum(@PathVariable("id") Integer id, Album album, BindingResult result,
                               Model model) {
        if (result.hasErrors()) {
            album.setId(id);
            return "update-artist";
        }

        albumRepository.save(album);
        model.addAttribute("albums", albumRepository.findAll());
        return "index";
    }

    @GetMapping("delete/{id}")
    public String deleteAlbum(@PathVariable("id") Integer id, Model model) {
        Album album = albumRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid artist Id:" + id));
        albumRepository.delete(album);
        model.addAttribute("albums", albumRepository.findAll());
        return "index";
    }
}
