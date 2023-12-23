package ru.skypro.homework.entity;

import lombok.Data;

import javax.persistence.Entity;

//@Entity
@Data
public class AdEntity {
    private Integer author;

    private String image;

    private Integer pk;

    private Integer price;

    private String title;

    public Integer getAuthor() {
        return author;
    }


}
