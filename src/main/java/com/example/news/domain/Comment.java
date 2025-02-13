package com.example.news.domain;

import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class Comment {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String author;

    @Column(columnDefinition = "VARCHAR(600)")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="owned_by_new_id")
    private News ownedByNewID;

    private Instant publicationMoment;

    // TODO Implement lastUpdated;


    public Comment(UUID id, String author, String content, Instant publicationMoment) {
        this.id = id;
        this.author = author;
        this.content = content;
        this.publicationMoment = publicationMoment;
    }

    public Comment() {}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public News getOwnedByNewID() {
        return ownedByNewID;
    }

    public void setOwnedByNewID(News ownedByNewID) {
        this.ownedByNewID = ownedByNewID;
    }
}
