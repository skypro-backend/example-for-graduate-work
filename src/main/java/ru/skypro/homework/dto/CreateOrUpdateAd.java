package ru.skypro.homework.dto;

import lombok.Data;

/**
 * @author Michail Z. (GH: HeimTN)
 */
@Data
public class CreateOrUpdateAd {
    private String title;
    private int price;
    private String description;
}
