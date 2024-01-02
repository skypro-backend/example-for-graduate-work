package ru.skypro.homework.model;

import lombok.Data;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.UserDto;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ad")
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long author;

    private String image;

    private Integer pk;

    private Integer price;

    private String title;

    private String description;


    public Ad(AdDto adDto) {
        this.author = adDto.getAuthor();
        this.image = adDto.getImage();
        this.pk = adDto.getPk();
        this.price = adDto.getPrice();
        this.title = adDto.getTitle();
    }
}
