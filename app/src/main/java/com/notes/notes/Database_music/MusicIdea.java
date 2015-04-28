package com.notes.notes.Database_music;

/**
 * Created by Jean-Baptiste on 26/03/15.
 */
public class MusicIdea {

    private long id;
    private String composer;
    private String title;
    private String interpreter;


    public long getId() {
        return id;
    }

    public String getComposer() {
        return composer;
    }

    public String getTitle() {
        return title;
    }

    public String getInterpreter() {
        return interpreter;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setComposer(String composer) {
        this.composer = composer;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setInterpreter(String interpreter) {
        this.interpreter = interpreter;
    }
}




