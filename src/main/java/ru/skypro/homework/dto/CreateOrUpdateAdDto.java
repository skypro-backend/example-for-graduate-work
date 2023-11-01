package ru.skypro.homework.dto;

import lombok.Data;

/**
 * CreateOrUpdateAdDto is the Data Transfer Object used to create and update an ad
 * @author radyushinaalena and AlexBoko
 */
@Data
public class CreateOrUpdateAdDto {
    private String title;
    private int price;
    private String description;
}
