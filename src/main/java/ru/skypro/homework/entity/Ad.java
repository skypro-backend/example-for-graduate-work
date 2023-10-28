package ru.skypro.homework.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Data
@RequiredArgsConstructor
@ToString
public class Ad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pk;
    @NonNull
    private String title;
    @NonNull
    private Integer price;
    private String description;
    private String image;
    @ManyToOne
    @JoinColumn(name = "author")
    private User author;

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
