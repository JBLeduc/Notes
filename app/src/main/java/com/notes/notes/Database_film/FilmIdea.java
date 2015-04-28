package com.notes.notes.Database_film;

/**
 * Created by Jean-Baptiste on 26/03/15.
 */
public class FilmIdea {

    private long id;
    private String title;
    private String director;
    private int film;
    private int video;


    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public int getFilm() {
        return film;
    }

    public int getVideo() {
        return video;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String comment) {
        this.title = comment;
    }

    public void setDirector(String subject) {
        this.director = subject;
    }

    public void setFilm(int urgent) {
        this.film = urgent;
    }

    public void setVideo(int buy) {
        this.video = buy;
    }


}


