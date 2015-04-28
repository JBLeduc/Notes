package com.notes.notes.Database_books;

/**
 * Created by Jean-Baptiste on 26/03/15.
 */
public class BookIdea {

    private long id;
    private String title;
    private String author;
    private String remark;
    private int penseBete;
    private int forReading;
    private int favorite;


    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getRemark() {
        return remark;
    }

    public int getPenseBete() {
        return penseBete;
    }

    public int getForReading() {
        return forReading;
    }

    public int getFavorite() {
        return favorite;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setPenseBete(int penseBete) {
        this.penseBete = penseBete;
    }

    public void setForReading(int forReading) {
        this.forReading = forReading;
    }

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }
}