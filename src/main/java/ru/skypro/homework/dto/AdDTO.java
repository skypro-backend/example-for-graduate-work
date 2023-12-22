package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class AdDTO {

    private Long author;

    private String image;

    private Long pk;

    private Integer price;

    private String title;
}
