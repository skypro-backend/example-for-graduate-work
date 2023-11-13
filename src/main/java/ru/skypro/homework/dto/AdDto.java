package ru.skypro.homework.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdDto {
    private int author;
    private String image;
    private int pk;
    private int price;
    private String title;
}
