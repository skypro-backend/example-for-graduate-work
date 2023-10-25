package ru.skypro.homework.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Comment {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int pk;

    @ManyToOne
    @JoinColumn(name = "author")
    private int author;

    private String text;

    private Long createdAt;

    @ManyToOne
    @JoinColumn(name = "Ad")
    private int Ad;

    public Comment() {
    }

    public Comment(int pk, int author, String text, Long createdAt, int ad) {
        this.pk = pk;
        this.author = author;
        this.text = text;
        this.createdAt = createdAt;
        Ad = ad;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "pk=" + pk +
                ", author=" + author +
                ", text='" + text + '\'' +
                ", createdAt=" + createdAt +
                ", Ad=" + Ad +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return pk == comment.pk && author == comment.author && Ad == comment.Ad && Objects.equals(text, comment.text) && Objects.equals(createdAt, comment.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pk, author, text, createdAt, Ad);
    }

    public int getPk() {
        return pk;
    }

    public void setPk(int pk) {
        this.pk = pk;
    }

    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
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

    public int getAd() {
        return Ad;
    }

    public void setAd(int ad) {
        Ad = ad;
    }
}
