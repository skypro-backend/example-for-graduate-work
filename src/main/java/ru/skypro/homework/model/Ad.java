package ru.skypro.homework.model;

import lombok.Data;
import ru.skypro.homework.dto.AdDto;


import javax.persistence.*;

@Data
@Entity
@Table(name = "ad")
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Integer author;
    private String image;
    private Integer pk;
    private Integer price;
    private String title;
    private String description;


    public Ad(Integer author, String image, Integer pk, Integer price, String title, String description) {
        this.author = author;
        this.image = image;
        this.pk = pk;
        this.price = price;
        this.title = title;
        this.description = description;
    }

    public Ad(AdDto adDto) {
        this.author = adDto.getAuthor();
        this.image = adDto.getImage();
        this.pk = adDto.getPk();
        this.price = adDto.getPrice();
        this.title = adDto.getTitle();
    }

}
