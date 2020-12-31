package com.myaudiolibary.web.controlleur;

import com.myaudiolibary.web.entity.Artist;
import com.myaudiolibary.web.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/artists")
public class ArtistControlleur {

/*    @Autowired
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
    }*/

    private final ArtistRepository artistRepository;

    @Autowired
    public ArtistControlleur(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @GetMapping("signup")
    public String showSignUpForm(Artist artist) {
        return "add-artist";
    }

    @GetMapping("list")
    public String showUpdateForm(Model model) {
        model.addAttribute("artists", artistRepository.findAll());
        return "index";
    }

    @PostMapping("add")
    public String addArtist(Artist artist, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-artist";
        }

        artistRepository.save(artist);
        return "redirect:list";
    }

    @GetMapping("edit/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Artist artist = artistRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid artist Id:" + id));
        model.addAttribute("artist", artist);
        return "update-artist";
    }

    @PostMapping("update/{id}")
    public String updateArtist(@PathVariable("id") Integer id, Artist artist, BindingResult result,
                               Model model) {
        if (result.hasErrors()) {
            artist.setId(id);
            return "update-artist";
        }

        artistRepository.save(artist);
        model.addAttribute("artists", artistRepository.findAll());
        return "index";
    }

    @GetMapping("delete/{id}")
    public String deleteArtist(@PathVariable("id") Integer id, Model model) {
        Artist artist = artistRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid artist Id:" + id));
        artistRepository.delete(artist);
        model.addAttribute("artists", artistRepository.findAll());
        return "index";
    }

//    @GetMapping("")
    @RequestMapping(params = {"name"},method=RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public String avoirArtistByName(Model model, @RequestParam(value="name") String name){
        List<Artist> pageArt = artistRepository.findByNameContainsIgnoreCase(name);
        model.addAttribute("artists", pageArt);

        return "recherche";
    }
}
