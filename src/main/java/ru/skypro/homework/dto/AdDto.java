package ru.skypro.homework.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class AdDto {

    private String image;
    private int author;
    private int pk;
    private int price;
    private String title;
}
