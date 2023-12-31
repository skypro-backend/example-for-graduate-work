package ru.skypro.homework.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class CreateOrUpdateAdDTO {

    private String title;
    private int price;
    private String description;

    public CreateOrUpdateAdDTO(String title, int price, String description) {
        this.title = title;
        this.price = price;
        this.description = description;
    }
}
