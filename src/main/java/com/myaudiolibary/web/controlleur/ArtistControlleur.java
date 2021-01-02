package com.myaudiolibary.web.controlleur;

import com.myaudiolibary.web.entity.Album;
import com.myaudiolibary.web.entity.Artist;
import com.myaudiolibary.web.repository.AlbumRepository;
import com.myaudiolibary.web.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/artists")
public class ArtistControlleur {

    private final ArtistRepository artistRepository;

    @Autowired
    public ArtistControlleur(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    //Ajout de l'album
    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public String avoirArtist(@PathVariable(value="id")Integer id, final ModelMap model){
        Optional<Artist> artist = artistRepository.findById(id);
        Album album= new Album();
        model.addAttribute("album",album);
        model.addAttribute("artist",artist.get());
        return "artist-details";
    }

    // Premier artiste
    @GetMapping("signup")
    public String showSignUpForm(Artist artist) {
        return "add-artist";
    }

    //Page d'accueil avec les liste des artistes
    @GetMapping("list")
    public String showUpdateForm(Model model) {
        model.addAttribute("artists", artistRepository.findAll());
        return "index";
    }

    //Ajouter un artiste
    @PostMapping("add")
    public String addArtist(Artist artist, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-artist";
        }

        artistRepository.save(artist);
        return "redirect:list";
    }

    //Modifier un artiste
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

    //Supprimer un artiste
    @GetMapping("delete/{id}")
    public String deleteArtist(@PathVariable("id") Integer id, Model model) {
        Artist artist = artistRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid artist Id:" + id));
        artistRepository.delete(artist);
        model.addAttribute("artists", artistRepository.findAll());
        return "index";
    }

    //Barre de recherche
    @RequestMapping(params = {"name"},method=RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public String ArtistByName(Model model, @RequestParam(value="name") String name){
        List<Artist> pageArt = artistRepository.findByNameContainsIgnoreCase(name);
        model.addAttribute("artists", pageArt);

        return "recherche";
    }
}
