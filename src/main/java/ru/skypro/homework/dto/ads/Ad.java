package ru.skypro.homework.dto.ads;

import lombok.Data;

@Data
public class Ad {

    private Integer author;
    private String image;
    private Integer pk;
    private Integer price;
    private String title;

}
