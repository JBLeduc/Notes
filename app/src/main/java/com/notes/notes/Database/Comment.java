package com.notes.notes.Database;

/**
 * Created by Jean-Baptiste on 26/03/15.
 */
public class Comment {

    private long id;
    private String subject;
    private String comment;
    private int urgent;
    private int buy;


    public long getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }

    public String getSubject() {
        return subject;
    }

    public int getUrgent() {
        return urgent;
    }

    public int getBuy() {
        return buy;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setUrgent(int urgent) {
        this.urgent = urgent;
    }

    public void setBuy(int buy) {
        this.buy = buy;
    }


    // Sera utilisée par ArrayAdapter dans la ListView
    @Override
    public String toString() {
        return comment;
    }
}

