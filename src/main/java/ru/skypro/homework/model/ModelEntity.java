package ru.skypro.homework.model;


import lombok.Data;

@Data
public class ModelEntity {
    private PhotoEntity photo;
    private String filePath; //путь на ПК
    private String image; //URL для контроллера
}
