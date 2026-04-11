package ru.skypro.homework.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "comments")

public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "text", nullable = false, length = 64)
    private String text;

    @Column(name = "createAt", nullable = false)
    private Long createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private UserEntity author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ad_id", nullable = false)
    private AdEntity ad;

    public Integer getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public AdEntity getAd() {
        return ad;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public void setAuthor(UserEntity author) {
        this.author = author;
    }

    public void setAd(AdEntity ad) {
        this.ad = ad;
    }
}
