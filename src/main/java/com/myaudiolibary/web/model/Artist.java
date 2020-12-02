package com.myaudiolibary.web.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="artist")
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ArtistId")
    private Integer id;

    @Column(name="Name")
    private String name;

    @JsonIgnoreProperties("artist")
    @OneToMany(mappedBy="artist", fetch=FetchType.EAGER, cascade = CascadeType.REMOVE)//CascadeType.ALL= suppression si demande de suppression d'artiste
    private Set<Album> albums= new HashSet<>();

    public Artist(Integer id, String name, Set<Album> albums){
        this.id=id;
        this.name=name;
        this.albums=albums;
    }

    public Artist(Integer id, String name){
        this.id=id;
        this.name=name;
    }

    public Artist(){}
    public Set<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
