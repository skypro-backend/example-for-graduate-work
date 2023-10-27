package ru.skypro.homework.entity;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int pk;

    @ManyToOne
    @JoinColumn(name = "author")
    private User author;

    private String text;

    private Long createdAt;

    @ManyToOne
    @JoinColumn(name = "ad")
    private Ad ad;

    public Comment() {
    }

    public Comment(int pk, User author, String text, Long createdAt, Ad ad) {
        this.pk = pk;
        this.author = author;
        this.text = text;
        this.createdAt = createdAt;
        this.ad = ad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return pk == comment.pk && Objects.equals(author, comment.author) && Objects.equals(text, comment.text) && Objects.equals(createdAt, comment.createdAt) && Objects.equals(ad, comment.ad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pk, author, text, createdAt, ad);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "pk=" + pk +
                ", author=" + author +
                ", text='" + text + '\'' +
                ", createdAt=" + createdAt +
                ", ad=" + ad +
                '}';
    }

    public int getPk() {
        return pk;
    }

    public void setPk(int pk) {
        this.pk = pk;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public Ad getAd() {
        return ad;
    }

    public void setAd(Ad ad) {
        this.ad = ad;
    }
}
