package ru.skypro.homework.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "ads")
@Data
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long pk;

    @Column(name = "Author")
    private Integer author;

    @Column(name = "URL")
    private String image;

    @Column(name = "Price")
    @Size(min = 0, max = 1000000000)
    private Integer price;

    @Column(name = "Title")
    @Size(min = 4, max = 32)
    private String title;

    public Ad(Long pk, Integer author, String image, Integer price, String title) {
        this.pk = pk;
        this.author = author;
        this.image = image;
        this.price = price;
        this.title = title;
    }
    // removed commented out code
}
