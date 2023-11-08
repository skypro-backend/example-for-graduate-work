package ru.skypro.homework.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdDto {

    private String image;
    private int author;
    private int pk;
    private int price;
    private String title;
}
