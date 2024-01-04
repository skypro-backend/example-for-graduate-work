package ru.skypro.kakavito.dto;

import lombok.Data;

@Data
public class AdDTO {
    private Integer author;
    private String image;
    private Integer pk;
    private Integer price;
    private String title;
}
