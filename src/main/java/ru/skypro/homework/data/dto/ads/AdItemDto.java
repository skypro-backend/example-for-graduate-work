package ru.skypro.homework.data.dto.ads;

import lombok.Data;

@Data
public class AdItemDto {
    private Integer pk;
    private String title;
    private Integer price;
    private String image;
    private Long author;
}
