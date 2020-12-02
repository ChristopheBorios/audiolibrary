package com.myaudiolibary.web.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name="album")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="AlbumId")
    private Integer id;

    @Column(name="Title")
    private String title;

    @ManyToOne
    @JoinColumn(name="ArtistId")
    private Artist artist;

    public Album(Integer id, String title, Artist artist){
        this.id=id;
        this.title=title;
        this.artist=artist;
    }

    public Album(Integer id, String title){
        this.id=id;
        this.title=title;
    }

    public Album(){}

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
