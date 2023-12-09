package ru.skypro.homework.dto;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Michail Z. (GH: HeimTN)
 */
@Entity
@Data
public class Advertisement {
    @Id
    private long id;
    private long author;
    private String image;
    private int pk;
    private int price;
    private String title;
    private String description;
}
