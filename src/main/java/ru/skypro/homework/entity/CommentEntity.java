package ru.skypro.homework.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "comment")
public class CommentEntity {
    @Column(name = "authors_id")
    private Integer author;
    @Column(name = "authors_image")
    private String authorImage;
    @Column(name = "first_name")
    private String authorFirstName;
    @Column(name = "comment_time")
    private Integer createdAt;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Integer pk;
    @Column(name = "text")
    private String text;
   // @ManyToOne
   // @JoinColumn(name = "ad_id")
    private Integer adId;

    public CommentEntity(Integer author, String authorImage,
                         String authorFirstName, Integer createdAt,
                         Integer pk, String text, Integer adId) {
        this.author = author;
        this.authorImage = authorImage;
        this.authorFirstName = authorFirstName;
        this.createdAt = createdAt;
        this.pk = pk;
        this.text = text;
        this.adId = adId;
    }
    public CommentEntity(){

    }


    public Integer getAuthor() {
        return author;
    }

    public void setAuthor(Integer author) {
        this.author = author;
    }

    public String getAuthorImage() {
        return authorImage;
    }

    public void setAuthorImage(String authorImage) {
        this.authorImage = authorImage;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public Integer getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Integer createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getPk() {
        return pk;
    }

    public void setPk(Integer pk) {
        this.pk = pk;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getAdId() {
        return adId;
    }

    public void setAdId(Integer adId) {
        this.adId = adId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CommentEntity that = (CommentEntity) o;
        return Objects.equals(author, that.author)
                && Objects.equals(authorImage, that.authorImage)
                && Objects.equals(authorFirstName, that.authorFirstName)
                && Objects.equals(createdAt, that.createdAt)
                && Objects.equals(pk, that.pk)
                && Objects.equals(text, that.text)
                && Objects.equals(adId, that.adId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, authorImage, authorFirstName, createdAt, pk, text, adId);
    }
}
