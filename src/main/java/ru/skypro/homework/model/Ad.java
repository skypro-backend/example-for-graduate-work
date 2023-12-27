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


    public Ad(Long author, String image, Integer pk, Integer price, String title, String description) {
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

    public Long getAuthor() {
        return author;
    }

    public void setAuthor(Long author) {
        this.author = author;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getPk() {
        return pk;
    }

    public void setPk(Integer pk) {
        this.pk = pk;
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

}
