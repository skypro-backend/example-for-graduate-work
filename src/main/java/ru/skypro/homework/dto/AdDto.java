package ru.skypro.homework.dto;

import lombok.Data;


/**
 * AdDto is the Data Transfer Object used to create and update an ad
 * @author radyushinaalena + AlexBoko
 */
@Data
public class AdDto {
    private int author;
    private String image;
    private int pk;
    private int price;
    private String title;
}
