package ru.skypro.homework.dto.model_dto;

import lombok.Data;

/**
 * Класс DTO для передачи информации при создании или обновлении объявления
 */
@Data
public class CreateOrUpdateAdDto {

      private String title;  //заголовок объявления
      private String description; // описание объявления
      private Integer price; //цена объявления
}
