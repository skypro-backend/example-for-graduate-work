package ru.skypro.homework.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "ad")
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ad_id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "image_id")
    private Image image;

    @Column(length = 32 ,nullable = false)
    private String title;

    @Column(length = 1000, nullable = false)
    private String description;

    @Column(nullable = false)
    private Integer price;

    @OneToMany(mappedBy = "ad", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    public Ad(int id, User author, String title, String description, Image image, int price) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.description = description;
        this.image = image;
        this.price = price;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ad ad = (Ad) o;
        if (id != 0) {
            return id.equals(ad.id);
        } else {
            return price == ad.price && Objects.equals(author, ad.author) && Objects.equals(title, ad.title)
                    && Objects.equals(description, ad.description) && Objects.equals(image, ad.image)
                    && Objects.equals(comments, ad.comments);
        }
    }

    @Override
    public int hashCode() {
        if (id != 0) {
            return Objects.hash(id);
        } else {
            return Objects.hash(author, title, description, image, price, comments);
        }
    }
}

