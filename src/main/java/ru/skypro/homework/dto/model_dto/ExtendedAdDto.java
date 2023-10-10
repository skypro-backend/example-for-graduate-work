package ru.skypro.homework.dto.model_dto;

import lombok.Data;

/**
 * Класс DTO для передачи полной информации об авторе объявления
 */
@Data
public class ExtendedAdDto {

    private Integer pk;
    private String authorFirstName;
    private String authorLastName;
    private String email;
    private String image;
    private String phone;
    private Integer price;
    private String title;
    private String description;
}
