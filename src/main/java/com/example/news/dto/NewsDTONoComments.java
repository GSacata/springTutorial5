package com.example.news.dto;

import java.time.Instant;

public class NewsDTONoComments {
    private Integer id;
    private String headline;
    private String author;
    private String content;
    private Instant publicationMoment;
    
    public NewsDTONoComments(Integer id, String headline, String author, String content, Instant publicationMoment) {
        this.id = id;
        this.headline = headline;
        this.author = author;
        this.content = content;
        this.publicationMoment = publicationMoment;
    }

    public NewsDTONoComments() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Instant getPublicationMoment() {
        return publicationMoment;
    }

    public void setPublicationMoment(Instant publicationMoment) {
        this.publicationMoment = publicationMoment;
    }
}
