package ru.skypro.homework.model;

import lombok.Data;

@Data
public class AdModel {
    private int author;
    private String image;
    private int pk;
    private int price;
    private String title;

    public AdModel(int author, String image, int pk, int price, String title){
        this.author = author;
        this.image = image;
        this.pk = pk;
        this.price = price;
        this.title = title;
    }
}
