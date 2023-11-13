package ru.skypro.homework.entity;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Data
@RequiredArgsConstructor
@ToString
@Table(name = "ads")
public class Ad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pk;
    @Getter
    @ManyToOne
    @JoinColumn(name = "author")
    private User author;
    private String title;
    private Integer price;
    private String description;
    private String image;

    public void setAuthor(final User author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ad ad = (Ad) o;
        return Objects.equals(pk, ad.pk) && Objects.equals(title, ad.title) && Objects.equals(price, ad.price) && Objects.equals(description, ad.description) && Objects.equals(image, ad.image) && Objects.equals(author, ad.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pk, title, price, description, image, author);
    }

}
