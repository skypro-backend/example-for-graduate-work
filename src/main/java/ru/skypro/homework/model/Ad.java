package ru.skypro.homework.model;

import javax.persistence.*;

@Entity
@Table(name = "ads")
public class Ad {
    @Id
    @Column(name = "ad_id")
    private Integer adId;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "user_id")
    private User author;

    @Column(name = "ad_price", nullable = false)
    private Integer price;

    @Column(name = "ad_title", nullable = false)
    private String title;

    @Column(name = "ad_description")
    private String description;

    @Column(name = "image_path")
    private String image;

    public Integer getAdId() {
        return adId;
    }

    public void setAdId(Integer adId) {
        this.adId = adId;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Ad{" +
                "adId=" + adId +
                ", author=" + author +
                ", price=" + price +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
