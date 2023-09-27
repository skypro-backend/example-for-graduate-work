package ru.skypro.homework.dto.model_dto;

import lombok.Data;

/**
 * Класс DTO для передачи информации об объявлении
 */
@Data
public class AdDto {
      private Integer pk; // идентификатор объявления
      private Integer author; // идентификатор автора
      private String title; // заголовок объявления
      private Integer price; // цена объявления
      private String image; //ссылка на картинку объявления
}
