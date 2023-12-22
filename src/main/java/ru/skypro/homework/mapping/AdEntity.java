package ru.skypro.homework.mapping;

import lombok.Data;

//@Entity
@Data
public class AdEntity {
    private Integer author;

    private String image;

    private Integer pk;

    private Integer price;

    private String title;
}
