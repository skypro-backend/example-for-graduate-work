package ru.skypro.homework.dto;


import lombok.Data;

/**
 * @author Michail Z. (GH: HeimTN)
 */
@Data
public class Advertisement {
    private long author;
    private String image;
    private int pk;
    private int price;
    private String title;
    private String description;
}
