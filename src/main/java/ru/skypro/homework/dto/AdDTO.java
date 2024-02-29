package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AdDTO {
    private Long pk;
    private String title;
    private Integer price;
    private String image;
    private Long author;
}
