package ru.skypro.homework.dto;

public class CreateOrUpdateAdDTO {
    private int price; // min 0; max 10000000
    private String description; //minLengs 8 ; max 64
    private String title;  //minLength: 4 ; maxLength: 32
}
