package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
// Объявление
public class AdDto {

    private Integer authorId;
    private String image;
    private Integer pkId;
    private Integer price;
    private String title;

}
