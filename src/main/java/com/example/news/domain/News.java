package com.example.news.domain;

import java.time.Instant;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table
public class News {
    
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    private String headline;
    private String author;
    
    @Column(columnDefinition = "TEXT")
    private String content;

    @OneToMany(mappedBy = "ownedByNewID", cascade = CascadeType.ALL)
    private List<Comment> postedComments;
    
    private Instant publicationMoment;
    
    public News(Integer id, String headline, String author, String content, Instant publicationMoment) {
        this.id = id;
        this.headline = headline;
        this.author = author;
        this.content = content;
        this.publicationMoment = publicationMoment;
    }

    public News() {}
    
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

    public List<Comment> getPostedComments() {
        return postedComments;
    }

    public void setPostedComments(List<Comment> postedComments) {
        this.postedComments = postedComments;
    }

}
